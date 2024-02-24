package com.example.microservice.inventoryservice.service.impl;

import java.util.List;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.example.microservice.inventoryservice.dto.InventoryResponse;
import com.example.microservice.inventoryservice.repository.InventoryRepository;
import com.example.microservice.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{

	private final InventoryRepository inventoryRepository;
	
	@Override
	@SneakyThrows
	public List<InventoryResponse> isInStock(List<String> skuCodeList) {
		return inventoryRepository.findBySkuCodeIn(skuCodeList).stream().map(inventory -> InventoryResponse.builder()
				 .skuCode(inventory.getSkuCode())
				 .isInStock(inventory.getQuantity() > 0)
				 .build()).toList();
	}

}
