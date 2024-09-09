package com.chrisferdev.hus.adaptertest;

import com.chrisferdev.hus.infrastructure.driven.jpa.adapter.RegisterUserJpaRepositoryImpl;
import com.chrisferdev.hus.infrastructure.driven.jpa.entity.UserEntity;
import com.chrisferdev.hus.infrastructure.driven.jpa.mapper.UserMapper;
import com.chrisferdev.hus.infrastructure.driven.jpa.repository.IUserJpaRepository;
import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RegisterUserJpaRepositoryImplTest {

     private IUserJpaRepository iUserJpaRepository;
     private UserMapper userMapper;
     private RegisterUserJpaRepositoryImpl registerUserJpaRepositoryImpl;

     @BeforeEach
     void setUp() {
         iUserJpaRepository = mock(IUserJpaRepository.class);
         userMapper = mock(UserMapper.class);
         registerUserJpaRepositoryImpl = new RegisterUserJpaRepositoryImpl(iUserJpaRepository, userMapper);
     }

     @Test
     void saveUser_successful() {

         UserRequest userRequest = new UserRequest();
         UserEntity userEntity = new UserEntity();
         UserRequest savedUserRequest = new UserRequest();

         // Configurar comportamiento de los mocks
         when(userMapper.toUserEntity(userRequest)).thenReturn(userEntity);
         when(iUserJpaRepository.save(userEntity)).thenReturn(userEntity);
         when(userMapper.toUserRequest(userEntity)).thenReturn(savedUserRequest);

         // Ejecutar el método a probar
         UserRequest result = registerUserJpaRepositoryImpl.saveUser(userRequest);

         // Verificar el resultado
         assertNotNull(result);
         assertEquals(savedUserRequest, result);

         // Verificar interacciones con los mocks
         verify(userMapper).toUserEntity(userRequest);
         verify(iUserJpaRepository).save(userEntity);
         verify(userMapper).toUserRequest(userEntity);
     }
 }

