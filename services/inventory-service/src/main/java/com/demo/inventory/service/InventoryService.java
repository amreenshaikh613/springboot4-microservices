package com.demo.inventory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.inventory.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService 
{
	@Autowired
	private InventoryRepository inventoryRepository=null;
	
	public boolean isInStock(String skuCode, Integer quantity) 
	{
		return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode,quantity);
		
	}
}
