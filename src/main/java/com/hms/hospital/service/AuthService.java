package com.hms.hospital.service;

import com.hms.hospital.DTO.LoginRequestDTO;
import com.hms.hospital.DTO.LoginResponseDTO;
import com.hms.hospital.entity.User;
import com.hms.hospital.repository.UserRepository;
import com.hms.hospital.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponseDTO login(LoginRequestDTO request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if (optionalUser.isEmpty()) {
            System.out.println("User not found: " + request.getUsername());
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();
        System.out.println("User found: " + user.getUsername());

        if (!user.getPassword().equals(request.getPassword())) {
            System.out.println("Invalid password for user: " + user.getUsername());
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());
        return new LoginResponseDTO(token, user.getRole().name());
    }
}
