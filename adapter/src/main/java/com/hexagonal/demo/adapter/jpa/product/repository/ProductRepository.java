package com.hexagonal.demo.adapter.jpa.product.repository;

import com.hexagonal.demo.adapter.jpa.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}