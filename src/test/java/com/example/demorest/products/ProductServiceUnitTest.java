package com.example.demorest.products;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceUnitTest {

    @Mock
    private OtherService otherService;

    @Mock
    private ProductRepository repository;

    @Test
    public void case_success() {
        // Arrange
        Product product1 = new Product("Product 1", 100);
        when(repository.findById(1))
                .thenReturn(Optional.of(product1));

        ProductService productService = new ProductService(otherService, repository);
        // Act
        ProductResponse response = productService.getById(1);
        // Assert
        assertEquals(1, response.getId());
        assertEquals("Product 1", response.getName());
    }

    @Test
    public void case_not_found() {
        // Arrange
        when(repository.findById(1))
                .thenReturn(Optional.empty());

        ProductService productService = new ProductService(otherService, repository);
        // Act
        Exception e = assertThrows(ProductNotFoundException.class,
                ()-> {
                    productService.getById(1);
                });
        assertEquals("Product not found with id=1", e.getMessage());
    }
}