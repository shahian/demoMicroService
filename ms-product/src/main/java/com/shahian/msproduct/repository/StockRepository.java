package com.shahian.msproduct.repository;

import com.shahian.msproduct.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock,Long> {
    List<Stock> findAllByWarehouseId(Long id);
    List<Stock> findAllByProductId(Long id);
}
