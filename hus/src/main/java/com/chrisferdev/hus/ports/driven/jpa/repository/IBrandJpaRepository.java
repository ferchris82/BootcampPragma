package com.chrisferdev.pragmabackend.ports.driven.jpa.repository;

import com.chrisferdev.pragmabackend.ports.driven.jpa.entity.BrandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandJpaRepository extends JpaRepository<BrandEntity, Long> {
    boolean existsByName(String name);
    Page<BrandEntity> findAllByOrderByNameAsc(Pageable pageable);
    Page<BrandEntity> findAllByOrderByNameDesc(Pageable pageable);
}
