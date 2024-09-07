package com.chrisferdev.hus.domain.spi.output;

import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;

public interface IRegisterUserPersistencePort {

    UserRequest saveUser(UserRequest userRequest);
    UserRequest findByEmail(String email);
}
