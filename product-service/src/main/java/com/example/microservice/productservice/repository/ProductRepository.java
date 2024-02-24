package com.example.microservice.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.microservice.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}
