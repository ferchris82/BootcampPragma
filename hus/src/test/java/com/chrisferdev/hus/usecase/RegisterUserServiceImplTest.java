package com.chrisferdev.hus.usecase;

import com.chrisferdev.hus.configuration.exception.BirthDathException;
import com.chrisferdev.hus.configuration.exception.DocumentException;
import com.chrisferdev.hus.configuration.exception.EmailException;
import com.chrisferdev.hus.configuration.exception.PhoneException;
import com.chrisferdev.hus.configuration.exception.exceptionhandler.ExceptionResponse;
import com.chrisferdev.hus.domain.api.usecase.RegisterUserServiceImpl;
import com.chrisferdev.hus.domain.model.UserType;
import com.chrisferdev.hus.domain.spi.output.IRegisterUserPersistencePort;
import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterUserServiceImplTest {
     private IRegisterUserPersistencePort iRegisterUserPersistencePort;
     private BCryptPasswordEncoder passwordEncoder;
     private RegisterUserServiceImpl registerUserService;

     @BeforeEach
     void setUp() {
         iRegisterUserPersistencePort = mock(IRegisterUserPersistencePort.class);
         passwordEncoder = mock(BCryptPasswordEncoder.class);
         registerUserService = new RegisterUserServiceImpl(iRegisterUserPersistencePort, passwordEncoder);
     }

     @Test
     void saveUser_validUserRequest_success() {
         UserRequest userRequest = new UserRequest();
         userRequest.setEmail("test@example.com");
         userRequest.setPhoneNumber("+1234567890");
         userRequest.setDocumentId("123456");
         userRequest.setBirthDate(LocalDate.of(2000, Month.JANUARY, 1));
         userRequest.setPassword("password");


         when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
         when(iRegisterUserPersistencePort.saveUser(userRequest)).thenReturn(userRequest);

         UserRequest result = registerUserService.saveUser(userRequest);

         assertNotNull(result);
         assertEquals(UserType.WAREHOUSE_ASSISTANT, result.getUserType());
         assertEquals("encodedPassword", result.getPassword());
         verify(iRegisterUserPersistencePort).saveUser(userRequest);
     }

 }
