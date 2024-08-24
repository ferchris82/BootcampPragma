package com.chrisferdev.hus.domain.port;

import com.chrisferdev.hus.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductRepository {

    Product saveProduct (Product product);
}
