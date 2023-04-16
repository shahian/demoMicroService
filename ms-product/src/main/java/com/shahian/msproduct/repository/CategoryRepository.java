package com.shahian.msproduct.repository;

import com.shahian.msproduct.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category>findByWarehouseId(Long id);
}
