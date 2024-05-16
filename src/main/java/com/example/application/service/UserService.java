package com.example.application.service;

import com.example.application.data.User;
import com.example.application.repository.UserRepository;
import com.example.application.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SecurityService securityService;

    @Autowired
    public UserService(UserRepository userRepository, SecurityService securityService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public int getUserId() {
        UserDetails userDetails = securityService.getAuthenticatedUser();
        User user = getUserByEmail(userDetails.getUsername());
        return user.getId();
    }
}