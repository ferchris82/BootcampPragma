package com.chrisferdev.hus.infrastructure.driving.rest;

import com.chrisferdev.hus.infrastructure.driving.dto.request.UserRequest;
import com.chrisferdev.hus.domain.api.usecase.RegisterUserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
public class RegisterUserController {
    private final RegisterUserServiceImpl registerUserServiceImpl;

    public RegisterUserController(RegisterUserServiceImpl registerUserServiceImpl) {
        this.registerUserServiceImpl = registerUserServiceImpl;
    }

    @Operation(
            summary = "Registrar un nuevo usuario",
            description = "Permite registrar un nuevo usuario en el sistema. El usuario debe proporcionar los datos necesarios para completar el registro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRequest.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida, por ejemplo, datos faltantes o incorrectos",
                    content = @Content(mediaType = "application/json"))
    })

    @PostMapping("/register")
    public ResponseEntity<UserRequest> save(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(registerUserServiceImpl.saveUser(userRequest), HttpStatus.CREATED);
    }
}
