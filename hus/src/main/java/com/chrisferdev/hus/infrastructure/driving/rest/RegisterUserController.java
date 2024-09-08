package com.chrisferdev.hus.infrastructure.driving.rest;

import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;
import com.chrisferdev.hus.domain.api.usecase.RegisterUserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
public class RegisterUserController {
    private final RegisterUserServiceImpl registerUserServiceImpl;

    public RegisterUserController(RegisterUserServiceImpl registerUserServiceImpl) {
        this.registerUserServiceImpl = registerUserServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRequest> save(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(registerUserServiceImpl.saveUser(userRequest), HttpStatus.CREATED);
    }
}
