package com.chrisferdev.hus.infrastructure.driven.jpa.repository;

import com.chrisferdev.hus.infrastructure.driven.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRequestJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity>findByEmail(String email);
}
