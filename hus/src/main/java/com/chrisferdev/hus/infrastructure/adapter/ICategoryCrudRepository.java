package com.chrisferdev.hus.infrastructure.adapter;

import com.chrisferdev.hus.infrastructure.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// Interfaz que define los métodos que se implementan automáticamente por JPA
public interface ICategoryCrudRepository extends JpaRepository<CategoryEntity, Integer> {
    boolean existsByName(String name);
    Page<CategoryEntity> findAllByOrderByNameAsc(Pageable pageable);
    Page<CategoryEntity> findAllByOrderByNameDesc(Pageable pageable);
}
