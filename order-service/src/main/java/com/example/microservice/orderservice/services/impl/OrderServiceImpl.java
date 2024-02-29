package com.example.microservice.orderservice.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.example.microservice.orderservice.event.OrderPlacedEvent;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.microservice.orderservice.dto.InventoryResponse;
import com.example.microservice.orderservice.dto.OrderRequest;
import com.example.microservice.orderservice.model.Order;
import com.example.microservice.orderservice.model.OrderLineItems;
import com.example.microservice.orderservice.repository.OrderRepository;
import com.example.microservice.orderservice.services.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	private final ModelMapper modelMapper;

	private final WebClient.Builder webClientBuilder;

	private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

	@Override
	public String placeOrder(OrderRequest orderRequest) {
		Order order = modelMapper.map(orderRequest, Order.class);
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItems().stream()
					.map(orderItem -> modelMapper.map(orderItem, OrderLineItems.class))
					.toList();
		order.setOrderLineItems(orderLineItems);
		
		List<String> skuCodeList = orderLineItems.stream().map(OrderLineItems::getSkuCode).toList();
		InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
				.uri("http://inventory-service/api/inventory",
						uriBuilder -> uriBuilder.queryParam("skuCodeList", skuCodeList).build())
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();
		boolean isInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::getIsInStock);
		if(isInStock) {
			orderRepository.save(order);
			kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber(),order.getName(),order.getEmailAddress()));
			return "Order placed successfully";
		}
		else {
			throw new IllegalArgumentException("Product is not in stock, Please try again later!");
		}

	}

}
