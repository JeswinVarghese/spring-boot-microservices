package com.example.microservice.orderservice.services;

import com.example.microservice.orderservice.dto.OrderRequest;

public interface OrderService {

	public String placeOrder(OrderRequest orderRequest);
}
