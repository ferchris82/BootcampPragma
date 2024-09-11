package com.chrisferdev.hus.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private LocalDateTime dateCreated;
    private List<OrderProduct> orderProducts;
    private OrderState orderState;
    private Long userId;

    public BigDecimal getTotalOrderPrice(){
        return this.orderProducts.stream()
                .map(orderProduct -> orderProduct.getTotalItem()).reduce(BigDecimal.ZERO,BigDecimal::add);
    }
}
