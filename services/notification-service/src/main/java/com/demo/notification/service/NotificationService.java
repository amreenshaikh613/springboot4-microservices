package com.demo.notification.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.mail.MailException;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.mail.javamail.MimeMessagePreparator;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.converter.MessageConverter;
//import org.springframework.stereotype.Service;
//
//import com.demo.order.event.OrderPlacedEvent;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.cloud.spring.pubsub.support.converter.SimplePubSubMessageConverter;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class NotificationService {
//	@Autowired
//	private final JavaMailSender javaMailSender=null;
//
//	private Logger logger = LoggerFactory.getLogger(NotificationService.class);
//
//	//	@KafkaListener(topics = "order-placed")
//	@Bean
//	@ServiceActivator(inputChannel = "notification-service.orderChannel")
//	public void listen(Message<String> message) throws Exception {
//
//		String json = message.getPayload();
//
//		ObjectMapper mapper = new ObjectMapper();
//		OrderPlacedEvent orderPlacedEvent = mapper.readValue(json, OrderPlacedEvent.class);
//		logger.info("Got Message from order-placed topic {}", orderPlacedEvent);
//		MimeMessagePreparator messagePreparator = mimeMessage -> {
//			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
//			messageHelper.setFrom("springshop@email.com");
//			messageHelper.setTo("amreen23287@gmail.com");
//			messageHelper.setSubject(String.format("Your Order with OrderNumber %s is placed successfully", orderPlacedEvent.getOrderNumber()));
//			messageHelper.setText(String.format("""
//					Hi,
//

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.demo.order.event.OrderPlacedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;

import jakarta.annotation.PostConstruct;

@Service
public class NotificationService {

	private final PubSubTemplate pubSubTemplate;

	@Autowired
	private final JavaMailSender javaMailSender=null;

	private Logger logger = LoggerFactory.getLogger(NotificationService.class);

	public NotificationService(PubSubTemplate pubSubTemplate) {
		this.pubSubTemplate = pubSubTemplate;
	}

	@PostConstruct
	public void startSubscriber() {
		pubSubTemplate.subscribe("order-events-sub", this::handleDetailedMessage);
	}

	private void handleDetailedMessage(BasicAcknowledgeablePubsubMessage message) {
		String payload = message.getPubsubMessage().getData().toStringUtf8();
		String type = message.getPubsubMessage()
				.getAttributesOrDefault("type", "unknown");

		ObjectMapper mapper = new ObjectMapper();
		OrderPlacedEvent orderPlacedEvent;
		try {
			orderPlacedEvent = mapper.readValue(payload, OrderPlacedEvent.class);

			logger.info("Got Message from order-placed topic {}", orderPlacedEvent);
			MimeMessagePreparator messagePreparator = mimeMessage -> {
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
				messageHelper.setFrom("springshop@email.com");
				messageHelper.setTo("amreen23287@gmail.com");
				messageHelper.setSubject(String.format("Your Order with OrderNumber %s is placed successfully", orderPlacedEvent.getOrderNumber()));
				messageHelper.setText(String.format("""
						Hi,
						Your order with order number %s is now placed successfully.

						Best Regards
						Spring Shop
						""",
						"Amreen Shaikh",
						orderPlacedEvent.getOrderNumber()));
			};
			javaMailSender.send(messagePreparator);
			logger.info("Order Notifcation email sent!!");
		} catch (MailException e) {
			logger.error("Exception occurred when sending mail", e);
			throw new RuntimeException("Exception occurred when sending mail to springshop@email.com", e);
		}
		catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
