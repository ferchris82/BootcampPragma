package com.chrisferdev.hus.infrastructure.driven.jpa.adapter;

import com.chrisferdev.hus.configuration.security.request.UserRequest;
import com.chrisferdev.hus.domain.spi.output.IUserPersistencePort;
import com.chrisferdev.hus.infrastructure.driven.jpa.mapper.UserMapper;
import com.chrisferdev.hus.infrastructure.driven.jpa.repository.IUserRequestJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRequestJpaRepositoryImpl implements IUserPersistencePort {

    private final IUserRequestJpaRepository iUserRequestJpaRepository;
    private final UserMapper userMapper;

    public UserRequestJpaRepositoryImpl(IUserRequestJpaRepository iUserRequestJpaRepository, UserMapper userMapper) {
        this.iUserRequestJpaRepository = iUserRequestJpaRepository;
        this.userMapper = userMapper;
    }

    public UserRequest saveUser(UserRequest userRequest){
        return userMapper.toUserRequest(iUserRequestJpaRepository.save(userMapper.toUserEntity(userRequest)));
    }
}
