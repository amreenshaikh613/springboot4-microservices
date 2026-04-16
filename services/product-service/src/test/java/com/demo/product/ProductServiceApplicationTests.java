package com.demo.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import io.restassured.RestAssured;
import lombok.NonNull;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {
	
	private static @NonNull String din = "mongo:7.0.5";
	
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer(din );
	
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp()
	{
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}
	
	static
	{
		
	}
	
	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
				  "name" : "iPhone 17",
				  "description" : "Smartphone",
				  "price" : 1200
				}
				
				""";
		RestAssured.given().contentType("application/json")
		.body(requestBody)
		.when().post("/api/product/create")
		.then().statusCode(201);
		
	}

}
