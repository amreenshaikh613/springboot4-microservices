package com.demo.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.inventory.service.InventoryService;

@RestController
@RequestMapping(value="/api/inventory")
public class InventoryController {
	
	@Autowired
	private final InventoryService inventoryService=null;
	
	@GetMapping
	@ResponseStatus(value=HttpStatus.OK)
	
	public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity)
	{
		return inventoryService.isInStock(skuCode, quantity);
	}

}
