package com.chrisferdev.hus.usecase;

import com.chrisferdev.hus.domain.api.usecase.LoginUserServiceImpl;
import com.chrisferdev.hus.domain.spi.output.ILoginUserPersistencePort;
import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginUserServiceImplTest {
    private ILoginUserPersistencePort iLoginUserPersistencePort;
    private LoginUserServiceImpl loginUserService;

    @BeforeEach
    void setUp() {
        iLoginUserPersistencePort = mock(ILoginUserPersistencePort.class);
        loginUserService = new LoginUserServiceImpl(iLoginUserPersistencePort);
    }

    @Test
    void findByEmail_userExists_success() {

        String email = "user@example.com";
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail(email);

        // Configurar comportamiento de mocks
        when(iLoginUserPersistencePort.findByEmail(email)).thenReturn(userRequest);

        // Ejecutar el método a probar
        UserRequest result = loginUserService.findByEmail(email);

        // Verificar el resultado
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(iLoginUserPersistencePort).findByEmail(email);
    }

}
