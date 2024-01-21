package com.kedicommerce.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kedicommerce.productservice.dto.ProductRequest;
import com.kedicommerce.productservice.dto.ProductResponse;
import com.kedicommerce.productservice.model.Product;
import com.kedicommerce.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	/**
	 * the mongoDBContainer no-args constructor is deprecated
	 */
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired //used to inject the bean (Instantiated object of a class)
	private MockMvc mockMvc;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	private ObjectMapper objectMapper;
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateProduct() throws Exception {

		ProductRequest productRequest = getProductRequest();
		/**
		 * content method takes a string, so we have to convert the product request to a string
		 */
		String productRequestString  = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString)

		).andExpect(status().isCreated());

		/**
		 * now check if the data has been entered into the dB
		 */
		//Assertions.assertEquals(1, productRepository.findAll().size());
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("kedi")
				.description("kedi")
				.price(BigDecimal.valueOf(150))
				.build();
	}

	/**
	 * integration test for get product
	 */

	@Test
	void getProductShouldReturnValue() throws Exception {

		//insert some data
		productRepository.insert(Product.builder()
						.price(BigDecimal.valueOf(440))
						.name("kedi")
						.description("kedi")
				.build());
		//shouldCreateProduct();

		ProductResponse productResponse = getProductResponse(productRepository);

		String productResponseString = objectMapper.writeValueAsString(productResponse);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
				.content(productResponseString)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());

	}

	private ProductResponse getProductResponse(ProductRepository productRepository) throws Exception {

		return ProductResponse.builder()
				.id(productRepository.findAll().get(0).getId())
				.name(productRepository.findAll().get(0).getName())
				.price(productRepository.findAll().get(0).getPrice())
				.description(productRepository.findAll().get(0).getDescription())
				.build();
	}
}
