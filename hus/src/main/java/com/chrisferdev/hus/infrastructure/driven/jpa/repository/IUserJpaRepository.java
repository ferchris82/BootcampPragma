package com.chrisferdev.hus.infrastructure.driven.jpa.repository;

import com.chrisferdev.hus.infrastructure.driven.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserJpaRepository extends JpaRepository<UserEntity, Long> {

}
