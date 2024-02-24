package com.example.microservice.productservice.service;

import java.util.List;

import com.example.microservice.productservice.dto.ProductRequest;
import com.example.microservice.productservice.dto.ProductResponse;

public interface ProductService {
	
	ProductRequest createProduct(ProductRequest productDto);

	List<ProductResponse> getAllProducts();

}
