package com.chrisferdev.hus.infrastructure.adapter;

import com.chrisferdev.hus.infrastructure.entity.BrandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandCrudRepository extends JpaRepository<BrandEntity, Long> {
    boolean existsByName(String name);
    Page<BrandEntity>findAllByOrderByNameAsc(Pageable pageable);
    Page<BrandEntity>findAllByOrderByNameDesc(Pageable pageable);
}
