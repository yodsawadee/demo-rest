package com.example.demorest.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository repository;

    @Test
    public void getProductById() {
        // Arrange
        Product product1 = new Product("Product 1", 100);
        repository.save(product1);

        // Act
        ProductResponse result = restTemplate.getForObject("/products/1", ProductResponse.class);
        assertEquals(1, result.getId());
        assertEquals("Product 1", result.getName());
    }

}