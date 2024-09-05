package com.chrisferdev.hus.domain.api.usecase;

import com.chrisferdev.hus.configuration.security.request.UserRequest;
import com.chrisferdev.hus.domain.spi.output.IUserPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private final IUserPersistencePort iUserPersistencePort;

    public UserServiceImpl(IUserPersistencePort iUserPersistencePort) {
        this.iUserPersistencePort = iUserPersistencePort;
    }

    public UserRequest saveUser(UserRequest userRequest){
        return iUserPersistencePort.saveUser(userRequest);
    }
}
