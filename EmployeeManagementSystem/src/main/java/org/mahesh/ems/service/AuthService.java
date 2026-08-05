package org.mahesh.ems.service;

import org.mahesh.ems.dto.ChangePasswordRequest;
import org.mahesh.ems.dto.request.LoginRequest;
import org.mahesh.ems.dto.request.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);
    String login(LoginRequest request);
    void changePassword(String email, ChangePasswordRequest request);
    void forgotPassword(String email);

    void verifyOtp(String email, String otp);

    void resetPassword(
            String email,
            String otp,
            String newPassword
    );

}
