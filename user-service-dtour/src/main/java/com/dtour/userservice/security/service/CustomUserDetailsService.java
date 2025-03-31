package com.dtour.userservice.security.service;


import com.dtour.userservice.model.User;
import com.dtour.userservice.repo.UserRepository;
import com.dtour.userservice.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByEmailAddress(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User by email: " + username + " doesn't exist.");
        }

        return new CustomUserDetails(userOptional.get());
    }
}