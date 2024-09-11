package com.chrisferdev.hus.domain.spi.output;

import com.chrisferdev.hus.domain.model.Order;
import com.chrisferdev.hus.domain.model.PaginatedResult;

public interface IOrderPersistencePort {
    Order saveOrder(Order order);
    Order findById(Long id);
    PaginatedResult<Order> findAll(String sortOrder, int page, int size);
    PaginatedResult<Order> findByUserId(Long id, String sortOrder, int page, int size);
    void updateStateById(Long id, String state);

}
