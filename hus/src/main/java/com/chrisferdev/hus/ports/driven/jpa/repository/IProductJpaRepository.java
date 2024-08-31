package com.chrisferdev.pragmabackend.ports.driven.jpa.repository;

import com.chrisferdev.pragmabackend.ports.driven.jpa.entity.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductJpaRepository extends JpaRepository<ProductEntity, Long> {

}
