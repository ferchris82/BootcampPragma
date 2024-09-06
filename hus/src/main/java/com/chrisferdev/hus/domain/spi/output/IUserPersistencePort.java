package com.chrisferdev.hus.domain.spi.output;

import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;

public interface IUserPersistencePort {

    UserRequest saveUser(UserRequest userRequest);
}
