package com.example.microservice.productservice.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.microservice.productservice.dto.ProductRequest;
import com.example.microservice.productservice.dto.ProductResponse;
import com.example.microservice.productservice.model.Product;
import com.example.microservice.productservice.repository.ProductRepository;
import com.example.microservice.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;
	
	private final ModelMapper modelMapper;
	
	@Override
	public ProductRequest createProduct(ProductRequest productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		Product savedproduct = productRepository.save(product);
		log.info("product is saved : "+savedproduct);
		return modelMapper.map(savedproduct, ProductRequest.class);
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		List<Product> productList = productRepository.findAll();
		List<ProductResponse> productResponseList = productList.stream().map(product->modelMapper.map(product, ProductResponse.class)).toList();
		return productResponseList;
	}

}
