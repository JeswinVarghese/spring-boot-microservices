package com.example.microservice.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice.productservice.dto.ProductRequest;
import com.example.microservice.productservice.dto.ProductResponse;
import com.example.microservice.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@PostMapping("/saveProduct")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProductRequest> createProduct(@RequestBody ProductRequest productDto) {
		ProductRequest prodDto = productService.createProduct(productDto);
		return new ResponseEntity<>(prodDto, HttpStatus.OK);
	}
	
	@GetMapping("/getAllProducts")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProducts(){
		return productService.getAllProducts();
	}
		
	
}
