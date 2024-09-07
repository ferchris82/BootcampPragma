package com.chrisferdev.hus.infrastructure.driven.jpa.adapter;

import com.chrisferdev.hus.domain.spi.output.ILoginUserPersistencePort;
import com.chrisferdev.hus.infrastructure.driven.jpa.mapper.UserMapper;
import com.chrisferdev.hus.infrastructure.driven.jpa.repository.ILoginJpaRepository;
import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;
import org.springframework.stereotype.Repository;

@Repository
public class LoginUserJpaRepositoryImpl implements ILoginUserPersistencePort {
    private final ILoginJpaRepository iLoginJpaRepository;
    private final UserMapper userMapper;

    public LoginUserJpaRepositoryImpl(ILoginJpaRepository iLoginJpaRepository, UserMapper userMapper) {
        this.iLoginJpaRepository = iLoginJpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserRequest findByEmail(String email) {
        return iLoginJpaRepository.findByEmail(email)
                .map(userMapper::toUserRequest)
                .orElse(null);
    }
}
