package io.github.cdm.authservice.controller;

import io.github.cdm.authservice.config.CustomUserDetailsService;
import io.github.cdm.authservice.dto.*;
import io.github.cdm.authservice.dto.*;
import io.github.cdm.authservice.event.ChangePasswordEvent;
import io.github.cdm.authservice.mapper.UserMapper;
import io.github.cdm.authservice.entity.User;
import io.github.cdm.authservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private KafkaTemplate<String, ChangePasswordEvent> kafkaTemplate;

    public AuthController(AuthService authService) {
        this.service = authService;
    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody RegisterRequest user) {
        return service.saveUser(user);
    }

    @PostMapping("/login")
    public String getToken(@RequestBody AuthenticationRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        if (authenticate.isAuthenticated()) {
            return service.generateToken(authRequest.getEmail());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }

    @GetMapping("/me")
    public UserDto getCurrentUser2(@RequestParam("name") String name) {
        return userMapper.toUserDto(service.getUserByUserName(name));
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction
    ) {
        Page<User> userPage = service.getAllUsers(PageRequest.of(page, size, Sort.by(direction, sortBy)));
        return ResponseEntity.ok(userPage);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam("email") String email) {

        System.out.println(email);

        String newPassword = generateRandomPassword();
        //        sendPasswordEmail(email, newPassword);

        User user = service.getUserByUserName(email);

        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        service.updateUser(user, newPassword);
        
        kafkaTemplate.send("change-password", new ChangePasswordEvent(email, newPassword));

        return new ResponseEntity<>(newPassword, HttpStatus.OK);
    }



    private String generateRandomPassword() {
        int password = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(password);
    }
    @PostMapping("/updateUser")
    public String updateUser(@RequestBody UpdateUserInformationRequest userDto) {
        return service.updateUser(userDto);
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<String> changePassword(
            @RequestBody ChangePasswordRequest request
    ) {
        return ResponseEntity.ok(service.changePassword(request));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        service.deleteUser(id);
        return ResponseEntity.ok("Delete User successfully");
    }
}
