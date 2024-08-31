package com.chrisferdev.hus.ports.driven.jpa.repository;


import com.chrisferdev.hus.ports.driven.jpa.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    Page<ProductEntity> findByBrandEntityId(Long brandId, Pageable pageable);

}
