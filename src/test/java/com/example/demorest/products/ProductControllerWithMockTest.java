package com.example.demorest.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductControllerWithMockTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private OtherService otherService;

    @MockBean
    private ProductRepository repository;

    @Test
    public void getProductById() {
        // Arrange
        Product product1 = new Product("Product 1", 100);
        when(repository.findById(1))
                .thenReturn(Optional.of(product1));
        // Act
        ProductResponse result = restTemplate.getForObject("/products/1", ProductResponse.class);
        // Assert
        assertEquals(1, result.getId());
        assertEquals("Product 1", result.getName());
    }


}