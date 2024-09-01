package com.chrisferdev.hus.ports.driven.jpa.repository;


import com.chrisferdev.hus.ports.driven.jpa.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    boolean existsByName(String name);
    Page<ProductEntity> findProductByName(String  name, Pageable pageable);
    Page<ProductEntity> findByBrandEntityId(Long brandId, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p JOIN p.categoryEntities c WHERE c.id IN :categoryIds")
    Page<ProductEntity> findByCategoryIds(@Param("categoryIds") List<Long> categoryIds, Pageable pageable);
}
