package com.demo.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.demo.order.client.InventoryClient;
import com.demo.order.dto.OrderRequest;
import com.demo.order.event.OrderPlacedEvent;
import com.demo.order.model.Order;
import com.demo.order.repository.OrderRepository;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

	@Autowired
	private final OrderRepository orderRepository=null;

	@Autowired
	private final InventoryClient inventoryClient = null;
	
	@Autowired
//	private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate = null;
	private PubSubTemplate pubSubTemplate = null;
	
	
	final static Logger logger = LoggerFactory.getLogger(OrderService.class);


	public void placeOrder(OrderRequest orderRequest) 
	{
		if(inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity()) == true)
		{
			Order order = new Order(orderRequest.id(), orderRequest.orderNumber(), orderRequest.skuCode(),
					orderRequest.price(), orderRequest.quantity());
			orderRepository.save(order);

			OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
			orderPlacedEvent.setOrderNumber(orderRequest.orderNumber());
			orderPlacedEvent.setEmail("amreen23287@gmail.com");
//			kafkaTemplate.send("order-placed", orderPlacedEvent);
			pubSubTemplate.publish(
			        "order-events",
			        orderPlacedEvent.toString()
			    );
			logger.info("Order placed event sent successfully");
		}
		else
		{
			throw new RuntimeException("Product with SkuCode : "+orderRequest.skuCode()
			+" is not in stock");
		}
	}

}
