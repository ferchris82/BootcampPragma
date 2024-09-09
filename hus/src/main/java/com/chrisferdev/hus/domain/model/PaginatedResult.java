package com.chrisferdev.hus.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResult<T>{

    private List<T> items;
    private int number;
    private int size;
    private long totalElements;

    public int getTotal() {
        return (int) totalElements;
    }

    public void setTotal(int total) {
        this.totalElements = total;
    }
}
