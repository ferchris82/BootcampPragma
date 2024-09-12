package com.chrisferdev.hus.domain.api.usecase;

import com.chrisferdev.hus.domain.model.Order;
import com.chrisferdev.hus.domain.model.PaginatedResult;
import com.chrisferdev.hus.domain.spi.output.IOrderPersistencePort;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {

    private final IOrderPersistencePort iOrderPersistencePort;

    public OrderServiceImpl(IOrderPersistencePort iOrderPersistencePort) {
        this.iOrderPersistencePort = iOrderPersistencePort;
    }

    public Order saveOrder(Order order){
        return this.iOrderPersistencePort.saveOrder(order);
    }

    public PaginatedResult<Order> findAll(String sortOrder, int page, int size) {
        return this.iOrderPersistencePort.findAll(sortOrder, page, size);
    }

    public PaginatedResult<Order> findByUserId(Long id, String sortOrder, int page, int size) {
        return this.iOrderPersistencePort.findByUserId(id, sortOrder, page, size);
    }

    public void updateStateById(Long id, String state){
        this.iOrderPersistencePort.updateStateById(id, state);
    }
}
