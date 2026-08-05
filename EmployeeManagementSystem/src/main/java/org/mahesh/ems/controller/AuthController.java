package org.mahesh.ems.controller;

import jakarta.validation.Valid;
import org.mahesh.ems.dto.ChangePasswordRequest;
import org.mahesh.ems.dto.ForgotPasswordRequest;
import org.mahesh.ems.dto.ResetPasswordRequest;
import org.mahesh.ems.dto.VerifyOtpRequest;
import org.mahesh.ems.dto.request.LoginRequest;
import org.mahesh.ems.dto.request.RegisterRequest;
import org.mahesh.ems.dto.response.AuthResponse;
import org.mahesh.ems.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {

        return new ResponseEntity<>(
                authService.register(request),
                HttpStatus.CREATED
        );
    }
    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {

        if (authentication == null) {
            return ResponseEntity.status(401).body("Authentication is NULL");
        }

        return ResponseEntity.ok(authentication.getAuthorities());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {
        System.out.println("LOGIN CONTROLLER HIT");
        String token = authService.login(request);

        return ResponseEntity.ok(new AuthResponse(token));
    }
    @PutMapping("/change-password")
//    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<String> changePassword(
            Authentication authentication,
            @Valid @RequestBody ChangePasswordRequest request) {

        authService.changePassword(
                authentication.getName(),
                request);

        return ResponseEntity.ok(
                "Password changed successfully");
    }
    @PostMapping("/forgot-password")
    public String forgotPassword(
            @RequestBody ForgotPasswordRequest request) {

        authService.forgotPassword(request.getEmail());

        return "OTP Sent Successfully";
    }
    @PostMapping("/verify-otp")
    public String verifyOtp(
            @RequestBody VerifyOtpRequest request) {

        authService.verifyOtp(
                request.getEmail(),
                request.getOtp());

        return "OTP Verified";
    }
    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestBody ResetPasswordRequest request) {

        authService.resetPassword(
                request.getEmail(),
                request.getOtp(),
                request.getNewPassword());

        return "Password Changed Successfully";
    }
}