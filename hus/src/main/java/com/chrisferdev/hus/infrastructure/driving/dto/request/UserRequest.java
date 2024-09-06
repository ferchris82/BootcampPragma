package com.chrisferdev.hus.infrastructure.driving.dto.request;


import com.chrisferdev.hus.domain.model.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Long id;

    @NotBlank(message = "The name is required.")
    private String firstname;

    @NotBlank(message = "The lastname is required.")
    private String lastname;

    @NotBlank(message = "The document is required.")
    private Integer documentId;

    @NotBlank(message = "The phone is required.")
    private String phoneNumber;

    @NotNull(message = "The birthdate is required.")
    private LocalDate birthDate;

    @NotBlank(message = "The email is required.")
    private String email;

    @NotBlank(message = "The password is required.")
    private String password;

    private UserType userType;

}
