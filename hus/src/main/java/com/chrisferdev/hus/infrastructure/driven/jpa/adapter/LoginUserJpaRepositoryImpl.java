package com.chrisferdev.hus.infrastructure.driven.jpa.adapter;

import com.chrisferdev.hus.domain.spi.output.ILoginUserPersistencePort;
import com.chrisferdev.hus.infrastructure.driven.jpa.mapper.UserMapper;
import com.chrisferdev.hus.infrastructure.driven.jpa.repository.IUserJpaRepository;
import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;

public class LoginUserJpaRepositoryImpl implements ILoginUserPersistencePort {
    private final IUserJpaRepository iUserJpaRepository;
    private final UserMapper userMapper;

    public LoginUserJpaRepositoryImpl(IUserJpaRepository iUserJpaRepository, UserMapper userMapper) {
        this.iUserJpaRepository = iUserJpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserRequest findByEmail(String email) {
        return iUserJpaRepository.findByEmail(email)
                .map(userMapper::toUserRequest)
                .orElse(null);
    }
}
