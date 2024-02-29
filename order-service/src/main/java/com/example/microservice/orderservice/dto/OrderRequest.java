package com.example.microservice.orderservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
	
	private List<OrderLineItemsDto> orderLineItems;
	private String name;
	private String emailAddress;

}
