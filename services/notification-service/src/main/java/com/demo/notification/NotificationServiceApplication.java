package com.demo.notification;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import com.google.cloud.spring.pubsub.support.converter.PubSubMessageConverter;
import com.google.cloud.spring.pubsub.support.converter.SimplePubSubMessageConverter;

@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}
	
	@Bean
	public MessageChannel orderChannel() {
	    return new DirectChannel();
	}

//	@Bean
//	public PubSubInboundChannelAdapter orderAdapter(
//	        PubSubTemplate pubSubTemplate,
//	        @Qualifier("orderChannel") MessageChannel channel) {
//
//	    PubSubInboundChannelAdapter adapter =
//	            new PubSubInboundChannelAdapter(pubSubTemplate, "order-events-sub");
//
//	    adapter.setOutputChannel(channel);
//	    return adapter;
//	}
//	
//	@Bean
//	public PubSubMessageConverter messageConverter() {
//	    return new SimplePubSubMessageConverter();
//	}


}
