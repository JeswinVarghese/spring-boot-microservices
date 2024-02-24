package com.example.microservice.inventoryservice.service;

import java.util.List;

import com.example.microservice.inventoryservice.dto.InventoryResponse;

public interface InventoryService {
	List<InventoryResponse> isInStock(List<String> skuCode); 

}
