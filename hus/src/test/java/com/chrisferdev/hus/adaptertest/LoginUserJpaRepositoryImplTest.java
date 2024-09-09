package com.chrisferdev.hus.adaptertest;

import com.chrisferdev.hus.infrastructure.driven.jpa.adapter.LoginUserJpaRepositoryImpl;
import com.chrisferdev.hus.infrastructure.driven.jpa.entity.UserEntity;
import com.chrisferdev.hus.infrastructure.driven.jpa.mapper.UserMapper;
import com.chrisferdev.hus.infrastructure.driven.jpa.repository.ILoginJpaRepository;
import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginUserJpaRepositoryImplTest {
     private ILoginJpaRepository iLoginJpaRepository;
     private UserMapper userMapper;
     private LoginUserJpaRepositoryImpl loginUserJpaRepositoryImpl;

     @BeforeEach
     void setUp() {
         iLoginJpaRepository = mock(ILoginJpaRepository.class);
         userMapper = mock(UserMapper.class);
         loginUserJpaRepositoryImpl = new LoginUserJpaRepositoryImpl(iLoginJpaRepository, userMapper);
     }

     @Test
     void findByEmail_userExists() {

         String email = "test@example.com";
         UserEntity userEntity = new UserEntity();
         UserRequest userRequest = new UserRequest();

         // Configurar comportamiento de los mocks
         when(iLoginJpaRepository.findByEmail(email)).thenReturn(Optional.of(userEntity));
         when(userMapper.toUserRequest(userEntity)).thenReturn(userRequest);

         // Ejecutar el método a probar
         UserRequest result = loginUserJpaRepositoryImpl.findByEmail(email);

         // Verificar el resultado
         assertNotNull(result);
         assertEquals(userRequest, result);

         // Verificar interacciones con los mocks
         verify(iLoginJpaRepository).findByEmail(email);
         verify(userMapper).toUserRequest(userEntity);
     }

     @Test
     void findByEmail_userNotFound() {
         // Preparar datos de prueba
         String email = "nonexistent@example.com";

         // Configurar comportamiento de los mocks
         when(iLoginJpaRepository.findByEmail(email)).thenReturn(Optional.empty());

         // Ejecutar el método a probar
         UserRequest result = loginUserJpaRepositoryImpl.findByEmail(email);

         // Verificar el resultado
         assertNull(result);

         // Verificar interacciones con los mocks
         verify(iLoginJpaRepository).findByEmail(email);
         verify(userMapper, never()).toUserRequest(any(UserEntity.class));
     }
 }
