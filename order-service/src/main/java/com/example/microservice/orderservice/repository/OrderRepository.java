package com.example.microservice.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservice.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
