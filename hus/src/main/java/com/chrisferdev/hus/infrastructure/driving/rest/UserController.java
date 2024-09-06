package com.chrisferdev.hus.infrastructure.driving.rest;

import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;
import com.chrisferdev.hus.domain.api.usecase.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/register")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping
    public ResponseEntity<UserRequest> save(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userServiceImpl.saveUser(userRequest), HttpStatus.CREATED);
    }
}
