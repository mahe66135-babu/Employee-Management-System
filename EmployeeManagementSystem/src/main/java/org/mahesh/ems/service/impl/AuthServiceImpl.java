package org.mahesh.ems.service.impl;

import org.mahesh.ems.dto.ChangePasswordRequest;
import org.mahesh.ems.dto.request.LoginRequest;
import org.mahesh.ems.dto.request.RegisterRequest;
import org.mahesh.ems.entity.Otp;
import org.mahesh.ems.entity.Role;
import org.mahesh.ems.entity.User;
import org.mahesh.ems.exception.ResourceNotFoundException;
import org.mahesh.ems.repository.OtpRepository;
import org.mahesh.ems.repository.UserRepository;
import org.mahesh.ems.security.JwtService;
import org.mahesh.ems.service.AuthService;
import org.mahesh.ems.service.EmailService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final OtpRepository otpRepository;
    private final EmailService emailService;


    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService,
                           OtpRepository otpRepository,
                           EmailService emailService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.otpRepository = otpRepository;
        this.emailService = emailService;
    }

    @Override
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }


        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Encrypt password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.EMPLOYEE);

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public String login(LoginRequest request) {
        try {
            System.out.println("=================================");
            System.out.println("EMAIL RECEIVED = [" + request.getEmail() + "]");
            System.out.println("PASSWORD RECEIVED = [" + request.getPassword() + "]");

            System.out.println("ALL USERS IN DB:");
            userRepository.findAll().forEach(u ->
                    System.out.println(u.getId() + " -> " + u.getEmail()));

            System.out.println("=================================");

            System.out.println("STEP 1");

            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            System.out.println("STEP 2");

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            System.out.println("STEP 3");

            String token = jwtService.generateToken(user);

            System.out.println("STEP 4");

            return token;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public void changePassword(String email,
                               ChangePasswordRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(
                request.getCurrentPassword(),
                user.getPassword())) {

            throw new RuntimeException("Current password is incorrect");
        }

        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()));

        userRepository.save(user);
    }
    @Override
    @Transactional
    public void forgotPassword(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        System.out.println("1. User found");

        otpRepository.deleteByEmail(email);

        System.out.println("2. Old OTP deleted");

        String otp = String.valueOf(
                100000 + new java.util.Random().nextInt(900000));

        Otp otpEntity = new Otp();

        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5));

        otpRepository.save(otpEntity);

        System.out.println("3. OTP saved");

//        emailService.sendEmail(
//                email,
//                "EMS Password Reset OTP",
//                "Your OTP is: " + otp +
//                        "\n\nThis OTP is valid for 5 minutes.");
//
//        System.out.println("4. Email sent");
        System.out.println("Generated OTP = " + otp);
// this is for real time email code
// emailService.sendEmail(
//         email,
//         "EMS Password Reset OTP",
//         "Your OTP is: " + otp +
//                 "\n\nThis OTP is valid for 5 minutes.");

        System.out.println("4. Email sending skipped for testing");
    }
    @Override
    public void verifyOtp(String email, String otp) {

        Otp otpEntity = otpRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("OTP not found"));

        if (!otpEntity.getOtp().equals(otp)) {

            throw new RuntimeException("Invalid OTP");

        }

        if (otpEntity.getExpiryTime().isBefore(LocalDateTime.now())) {

            throw new RuntimeException("OTP Expired");

        }

    }
    @Override
    @Transactional
    public void resetPassword(
            String email,
            String otp,
            String newPassword) {

        verifyOtp(email, otp);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setPassword(
                passwordEncoder.encode(newPassword));

        userRepository.save(user);

        otpRepository.deleteByEmail(email);

    }
}