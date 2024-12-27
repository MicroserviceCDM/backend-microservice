package com.minhvu.authservice.service;

import com.minhvu.authservice.dto.ChangePasswordRequest;
import com.minhvu.authservice.dto.RegisterRequest;
import com.minhvu.authservice.dto.UpdateUserInformationRequest;
import com.minhvu.authservice.entity.User;
import com.minhvu.authservice.exception.UserNotFoundException;
import com.minhvu.authservice.exception.response.UserErrorResponse;
import com.minhvu.authservice.repository.UserCredentialRepository;
import com.minhvu.authservice.repository.UserRepository;
import jakarta.ws.rs.BadRequestException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@Service
@Data
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String saveUser(RegisterRequest credential) {
        if (userRepository.existsByNameAllIgnoreCase(credential.getName())) {
            throw new BadRequestException("Username is already taken!");
        }
        var user = User.builder()
                .name(credential.getName())
                .email(credential.getEmail())
                .password(passwordEncoder.encode(credential.getPassword()))
                .role(credential.getRole())
                .address(credential.getAddress())
                .phone_number(credential.getPhone_number())
                .avatar(credential.getAvatar())
                .build();
        repository.save(user);
        return "user added to the system";
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public User getUserByUserName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with email %s not found", name)));
    }

    public Optional<User> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByName(username);
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public String updateUser(UpdateUserInformationRequest userDto) {
        Optional<User> user = userRepository.findById(userDto.getId());
        if (user.isPresent()) {
            user.get().setName(userDto.getName());
            user.get().setEmail(userDto.getEmail());
            user.get().setAddress(userDto.getAddress());
            user.get().setPhone_number(userDto.getPhone());
            user.get().setAvatar(userDto.getAvatar());
            userRepository.save(user.get());
            return "User updated successfully";
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exception) {
        UserErrorResponse userErrorResponse = new UserErrorResponse();
        userErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        userErrorResponse.setMessage(exception.getMessage());
        userErrorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(userErrorResponse, HttpStatus.NOT_FOUND);
    }

    public void updateUser(User user, String newPassword) {

        user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);

    }

    public String changePassword(ChangePasswordRequest request) {
        Optional<User> user = userRepository.findById(request.getId());
        if (user.isPresent()) {
            user.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user.get());
            return "Password changed successfully";
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
