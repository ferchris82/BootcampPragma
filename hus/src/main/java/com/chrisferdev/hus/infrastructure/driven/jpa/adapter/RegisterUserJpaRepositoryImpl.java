package com.chrisferdev.hus.infrastructure.driven.jpa.adapter;

import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;
import com.chrisferdev.hus.domain.spi.output.IRegisterUserPersistencePort;
import com.chrisferdev.hus.infrastructure.driven.jpa.mapper.UserMapper;
import com.chrisferdev.hus.infrastructure.driven.jpa.repository.IUserJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterUserJpaRepositoryImpl implements IRegisterUserPersistencePort {

    private final IUserJpaRepository iUserJpaRepository;
    private final UserMapper userMapper;

    public RegisterUserJpaRepositoryImpl(IUserJpaRepository iUserJpaRepository, UserMapper userMapper) {
        this.iUserJpaRepository = iUserJpaRepository;
        this.userMapper = userMapper;
    }

    public UserRequest saveUser(UserRequest userRequest){
        return userMapper.toUserRequest(iUserJpaRepository.save(userMapper.toUserEntity(userRequest)));
    }

}
