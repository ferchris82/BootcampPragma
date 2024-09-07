package com.chrisferdev.hus.infrastructure.driven.jpa.adapter;

import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;
import com.chrisferdev.hus.domain.spi.output.IUserPersistencePort;
import com.chrisferdev.hus.infrastructure.driven.jpa.mapper.UserMapper;
import com.chrisferdev.hus.infrastructure.driven.jpa.repository.IUserJpaRepository;
import com.chrisferdev.hus.infrastructure.driving.mapper.UserResponseMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRequestJpaRepositoryImpl implements IUserPersistencePort {

    private final IUserJpaRepository iUserJpaRepository;
    private final UserMapper userMapper;
    private final UserResponseMapper userResponseMapper;

    public UserRequestJpaRepositoryImpl(IUserJpaRepository iUserJpaRepository, UserMapper userMapper, UserResponseMapper userResponseMapper) {
        this.iUserJpaRepository = iUserJpaRepository;
        this.userMapper = userMapper;
        this.userResponseMapper = userResponseMapper;
    }

    public UserRequest saveUser(UserRequest userRequest){
        return userMapper.toUserRequest(iUserJpaRepository.save(userMapper.toUserEntity(userRequest)));
    }

    @Override
    public UserRequest loginUser(UserRequest userRequest) {
        return userResponseMapper.toUserResponse(iUserJpaRepository.);
    }

}
