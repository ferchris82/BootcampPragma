package com.chrisferdev.hus.infrastructure.driving.dto.request;


import com.chrisferdev.hus.domain.model.UserType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private static final String DOCUMENT_REGEX = "\\d+";
    private static final String PHONE_REGEX = "\\+?\\d{7,15}";

    private Long id;

    @NotBlank(message = "The name is required.")
    @Size(max = 50, message = "The name must not exceed 50 characters.")
    private String firstname;

    @NotBlank(message = "The lastname is required.")
    @Size(max = 50, message = "The lastname must not exceed 50 characters.")
    private String lastname;

    @NotBlank(message = "The document is required.")
    @Pattern(regexp = "\\d+", message = "The document must only contain digits.")
    private String documentId;

    @NotBlank(message = "The phone is required.")
    @Pattern(regexp = "\\+?\\d{7,15}", message = "The phone number is invalid.")
    private String phoneNumber;

    @NotNull(message = "The birthdate is required.")
    @Past(message = "The birthdate must be in the past.")
    private LocalDate birthDate;

    @NotBlank(message = "The email is required.")
    @Email(message = "The email format is invalid.")
    private String email;

    @NotBlank(message = "The password is required.")
    @Size(min = 8, message = "The password must have at least 8 characters.")
    private String password;

    private UserType userType;

    // Additional validation for the age (e.g., must be at least 18 years old)
    @AssertTrue(message = "User must be at least 18 years old.")
    public boolean isAdult() {
        return birthDate != null && Period.between(birthDate, LocalDate.now()).getYears() >= 18;
    }

}
