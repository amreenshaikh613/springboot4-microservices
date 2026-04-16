package com.demo.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.product.controller.dto.ProductRequest;
import com.demo.product.dto.ProductResponse;
import com.demo.product.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private final ProductService productService = null;

	@PostMapping("/create")	
	@ResponseStatus(value = HttpStatus.CREATED)
	public ProductResponse createProduct(@RequestBody ProductRequest productRequest)
	{
		return productService.createProduct(productRequest);
	}	

	@GetMapping("/get")
	@ResponseStatus(value = HttpStatus.OK)	
	public List<ProductResponse> getAllProducts()
	{
		return productService.getAllProducts();
	}
}
