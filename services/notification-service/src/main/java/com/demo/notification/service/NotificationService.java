package com.demo.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.demo.order.event.OrderPlacedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
	@Autowired
	private final JavaMailSender javaMailSender=null;
	
	private Logger logger = LoggerFactory.getLogger(NotificationService.class);

	@KafkaListener(topics = "order-placed")
	public void listen(OrderPlacedEvent orderPlacedEvent){
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
		try {
			javaMailSender.send(messagePreparator);
			logger.info("Order Notifcation email sent!!");
		} catch (MailException e) {
			logger.error("Exception occurred when sending mail", e);
			throw new RuntimeException("Exception occurred when sending mail to springshop@email.com", e);
		}
	}
}
