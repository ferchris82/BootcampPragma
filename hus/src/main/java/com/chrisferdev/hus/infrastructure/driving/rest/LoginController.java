package com.chrisferdev.hus.infrastructure.driving.rest;

import com.chrisferdev.hus.configuration.security.jwt.JWTGeneratorService;
import com.chrisferdev.hus.infrastructure.driving.dto.record.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
@Slf4j
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTGeneratorService jwtGeneratorService;


    public LoginController(AuthenticationManager authenticationManager, JWTGeneratorService jwtGeneratorService) {
        this.authenticationManager = authenticationManager;
        this.jwtGeneratorService = jwtGeneratorService;
    }

    @Operation(
            summary = "Autenticación de usuario",
            description = "Permite a los usuarios autenticarse en el sistema. Al autenticar con éxito, se genera un token JWT que se debe incluir en las solicitudes subsecuentes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticación exitosa y token generado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas",
                    content = @Content(mediaType = "application/json"))
    })

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( userDTO.username(), userDTO.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("Rol de user: {}", SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities().stream()
                .findFirst().get().toString());

        String token = jwtGeneratorService.getToken(userDTO.username());

        return new ResponseEntity<>("Usuario Logueado satisfactoriamente: "+ token, HttpStatus.OK);
    }
}
