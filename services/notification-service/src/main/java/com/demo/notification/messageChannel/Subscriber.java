package com.demo.notification.messageChannel;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.inbound.PubSubInboundChannelAdapter;

public class Subscriber {
	@Bean
	public MessageChannel orderChannel() {
	    return new DirectChannel();
	}

	@Bean
	public PubSubInboundChannelAdapter orderAdapter(
	        PubSubTemplate pubSubTemplate,
	        @Qualifier("orderChannel") MessageChannel channel) {

	    PubSubInboundChannelAdapter adapter =
	            new PubSubInboundChannelAdapter(pubSubTemplate, "order-events-sub");

	    adapter.setOutputChannel(channel);
	    return adapter;
	}

}
