package com.demo.order.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.demo.order.client.InventoryClient;

@Configuration
public class RestClientConfig {

	@Value("${inventory.service.url}")
	private String inventoryServiceUrl;

	@Bean
	public InventoryClient inventoryClient() 
	{
		RestClient client = RestClient.builder().baseUrl(inventoryServiceUrl).
				requestFactory(getClientRequestFactory())
				.build();
		RestClientAdapter adapter = RestClientAdapter.create(client);
		HttpServiceProxyFactory hhtpsf = HttpServiceProxyFactory.builderFor(adapter).build();

		return hhtpsf.createClient(InventoryClient.class);
	}	

	private ClientHttpRequestFactory getClientRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

		// Set connection timeout
		factory.setConnectTimeout(Duration.ofSeconds(3));
		// Set read timeout
		factory.setReadTimeout(Duration.ofSeconds(3));

		return factory;
	}

}
