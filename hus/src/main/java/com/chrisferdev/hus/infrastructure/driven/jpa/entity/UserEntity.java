package com.chrisferdev.hus.infrastructure.driven.jpa.entity;

import com.chrisferdev.hus.domain.model.UserType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private Integer documentId;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String password;

    private UserType userType;
}
