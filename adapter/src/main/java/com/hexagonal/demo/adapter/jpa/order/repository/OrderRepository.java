package com.hexagonal.demo.adapter.jpa.order.repository;

import com.hexagonal.demo.adapter.jpa.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}