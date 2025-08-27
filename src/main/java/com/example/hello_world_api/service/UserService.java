package com.example.hello_world_api.service;

import com.example.hello_world_api.dto.RegisterRequest;
import com.example.hello_world_api.dto.UserResponse;
import com.example.hello_world_api.entity.User;
import com.example.hello_world_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder encoder;
    
    public User registerUser(RegisterRequest signUpRequest) {
        User user = new User(
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getFirstname(),
                signUpRequest.getLastname(),
                signUpRequest.getPhoneNumber(),
                signUpRequest.getBirthday()
        );
        
        // Generate unique member ID
        user.setMemberId(generateMemberId());
        // Initialize with default points
        user.setPoints(15420.0); // Default points as shown in screenshot
        
        return userRepository.save(user);
    }
    
    private String generateMemberId() {
        String memberId;
        do {
            // Generate LBK + 6 digit number
            Random random = new Random();
            int number = 100000 + random.nextInt(900000); // 6 digit number
            memberId = "LBK" + String.format("%06d", number);
        } while (userRepository.existsByMemberId(memberId));
        return memberId;
    }
    
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    public UserResponse convertToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getPhoneNumber(),
                user.getBirthday(),
                user.getMemberId(),
                user.getPoints(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
