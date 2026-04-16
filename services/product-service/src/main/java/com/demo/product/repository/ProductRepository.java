package com.demo.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.product.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>
{

}
