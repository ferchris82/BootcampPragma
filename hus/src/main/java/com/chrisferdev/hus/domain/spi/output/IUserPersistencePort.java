package com.chrisferdev.hus.domain.spi.output;

import com.chrisferdev.hus.configuration.security.request.UserRequest;

public interface IUserPersistencePort {

    UserRequest saveUser(UserRequest userRequest);
}
