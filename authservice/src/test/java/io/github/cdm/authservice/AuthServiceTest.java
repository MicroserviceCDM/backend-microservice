package io.github.cdm.authservice;

import io.github.cdm.authservice.dto.ChangePasswordRequest;
import io.github.cdm.authservice.dto.RegisterRequest;
import io.github.cdm.authservice.dto.UpdateUserInformationRequest;
import io.github.cdm.authservice.entity.Role;
import io.github.cdm.authservice.entity.User;
import io.github.cdm.authservice.exception.UserNotFoundException;
import io.github.cdm.authservice.repository.UserCredentialRepository;
import io.github.cdm.authservice.repository.UserRepository;
import io.github.cdm.authservice.service.AuthService;
import io.github.cdm.authservice.service.JwtService;
import jakarta.ws.rs.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserCredentialRepository userCredentialRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveUserSuccessfully() {
        RegisterRequest request = new RegisterRequest();
        request.setName("test");
        request.setEmail("test@test.com");
        request.setPassword("password");
        request.setRole(Role.CUSTOMER);
        request.setAddress("address");
        request.setPhone_number("1234567890");
        request.setAvatar("avatar");

        when(userRepository.existsByNameAllIgnoreCase(any())).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(userCredentialRepository.save(any())).thenReturn(null);

        String result = authService.saveUser(request);

        assertEquals("user added to the system", result);
    }

    @Test
    public void saveUserWithExistingUsername() {
        RegisterRequest request = new RegisterRequest();
        request.setName("test");

        when(userRepository.existsByNameAllIgnoreCase(any())).thenReturn(true);

        assertThrows(BadRequestException.class, () -> authService.saveUser(request));
    }

    @Test
    public void generateTokenSuccessfully() {
        String username = "test";
        String token = "token";

        when(jwtService.generateToken(any())).thenReturn(token);

        String result = authService.generateToken(username);

        assertEquals(token, result);
    }

    @Test
    public void getUserByUserNameSuccessfully() {
        String username = "test";
        User user = new User();
        user.setName(username);

        when(userRepository.findByName(any())).thenReturn(Optional.of(user));

        User result = authService.getUserByUserName(username);

        assertEquals(user, result);
    }

    @Test
    public void getUserByUserNameNotFound() {
        String username = "test";

        when(userRepository.findByName(any())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> authService.getUserByUserName(username));
    }

    @Test
    public void updateUserSuccessfully() {
        UpdateUserInformationRequest request = new UpdateUserInformationRequest();
        request.setEmail("new@test.com");
        request.setAddress("new address");
        request.setPhone("0987654321");
        request.setAvatar("new avatar");

        User user = new User();

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(null);

        String result = authService.updateUser(request);

        assertEquals("User updated successfully", result);
    }

    @Test
    public void updateUserNotFound() {
        UpdateUserInformationRequest request = new UpdateUserInformationRequest();

        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> authService.updateUser(request));
    }

    @Test
    public void changePasswordSuccessfully() {
        ChangePasswordRequest request = new ChangePasswordRequest();
        request.setNewPassword("newPassword");

        User user = new User();

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(userRepository.save(any())).thenReturn(null);

        String result = authService.changePassword(request);

        assertEquals("Password changed successfully", result);
    }

    @Test
    public void changePasswordUserNotFound() {
        ChangePasswordRequest request = new ChangePasswordRequest();

        when(userRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> authService.changePassword(request));
    }
}
