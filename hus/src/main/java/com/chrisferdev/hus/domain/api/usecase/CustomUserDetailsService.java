package com.chrisferdev.hus.domain.api.usecase;

import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private LoginUserServiceImpl loginUserService;

    public CustomUserDetailsService(LoginUserServiceImpl loginUserService) {
        this.loginUserService = loginUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRequest userRequest = loginUserService.findByEmail(username);
        return org.springframework.security.core.userdetails.User.builder()
                .username(userRequest.getEmail())
                .password(userRequest.getPassword()).roles(userRequest.getUserType().name()).build();
    }
}
