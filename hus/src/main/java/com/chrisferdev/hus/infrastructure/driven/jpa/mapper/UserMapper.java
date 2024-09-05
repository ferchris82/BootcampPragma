package com.chrisferdev.hus.infrastructure.driven.jpa.mapper;

import com.chrisferdev.hus.configuration.security.request.UserRequest;
import com.chrisferdev.hus.infrastructure.driven.jpa.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "documentId", target = "documentId")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "userType", target = "userType")
    UserRequest toUserRequest(UserEntity userEntity);

    Iterable<UserRequest> toUserRequestList(Iterable<UserEntity> userEntities);

    @InheritInverseConfiguration
    UserEntity toUserEntity(UserRequest userRequest);
}
