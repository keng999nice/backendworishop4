package com.example.hello_world_api.service;

import com.example.hello_world_api.dto.RegisterRequest;
import com.example.hello_world_api.dto.UserResponse;
import com.example.hello_world_api.entity.User;
import com.example.hello_world_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        
        return userRepository.save(user);
    }
    
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    
    public UserResponse convertToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getPhoneNumber(),
                user.getBirthday(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
