package com.example.demorest.products;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository repository;

    @Test
    @DisplayName("Success case with id=1")
    public void getById() {
        // Arrange
        Product product1 = new Product("Product 1", 100);
        when(repository.findById(1))
                .thenReturn(Optional.of(product1));
        // Act
        ProductResponse response = productService.getById(1);
        // Assert
        assertEquals(1, response.getId());
        assertEquals("Product 1", response.getName());
    }

    @Test
    @DisplayName("Product not found case with id=1 should throw exception ProductNotFoundException")
    public void getById_not_found() {
        // Arrange
        when(repository.findById(1))
                .thenReturn(Optional.empty());
        // Act
        try {
            productService.getById(1);
            // Fail
            fail("Must throw exception");
        } catch (ProductNotFoundException e) {
            assertEquals("Product not found with id=1", e.getMessage());
        }
    }

    @Test
    @DisplayName("[jUnit5] Product not found case with id=1 should throw exception ProductNotFoundException")
    public void getById_not_found_with_junit5() {
        // Arrange
        when(repository.findById(1))
                .thenReturn(Optional.empty());
        // Act
        Exception e = assertThrows(ProductNotFoundException.class,
                ()-> {
            productService.getById(1);
        });
        assertEquals("Product not found with id=1", e.getMessage());
    }
}