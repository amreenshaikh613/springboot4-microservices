package com.demo.product.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.product.controller.dto.ProductRequest;
import com.demo.product.dto.ProductResponse;
import com.demo.product.model.Product;
import com.demo.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	@Autowired
	private final ProductRepository productRepository = null;
	
	public ProductResponse createProduct(ProductRequest productRequest )
	{
		Product product = new Product(productRequest.id(), productRequest.name(), productRequest.description(),
				productRequest.price());
		Product save = productRepository.save(product);
		System.out.println("Product saved");
		return new ProductResponse(save.getId(), save.getName(), save.getDescription(), save.getPrice());
	}

	
	public List<ProductResponse> getAllProducts()
	{
		return productRepository.findAll().stream().map(product-> 
		new ProductResponse(product.getId(), product.getName(), 
				product.getDescription(), product.getPrice())).toList();
	}
}
