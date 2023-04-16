package com.shahian.msproduct.repository;

import com.shahian.msproduct.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long id);

    List<Product> findByOrderId(Long id);

}
