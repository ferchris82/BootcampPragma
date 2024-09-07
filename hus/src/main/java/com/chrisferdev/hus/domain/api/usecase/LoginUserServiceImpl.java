package com.chrisferdev.hus.domain.api.usecase;

import com.chrisferdev.hus.configuration.exception.EmailException;
import com.chrisferdev.hus.configuration.exception.exceptionhandler.ExceptionResponse;
import com.chrisferdev.hus.domain.spi.output.ILoginUserPersistencePort;
import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;
import org.springframework.stereotype.Service;

@Service
public class LoginUserServiceImpl {

    private final ILoginUserPersistencePort iLoginUserPersistencePort;

    public LoginUserServiceImpl(ILoginUserPersistencePort iLoginUserPersistencePort) {
        this.iLoginUserPersistencePort = iLoginUserPersistencePort;
    }

    public UserRequest findByEmail(String email) {
        UserRequest userRequest = iLoginUserPersistencePort.findByEmail(email);
        if (userRequest == null) {
            throw new EmailException(ExceptionResponse.ERROR_EMAIL.getMessage());
        }
        return userRequest;
    }
}
