package com.chrisferdev.hus.infrastructure.adapter;

import com.chrisferdev.hus.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductCrudRepository extends JpaRepository<ProductEntity, Long> {

}
