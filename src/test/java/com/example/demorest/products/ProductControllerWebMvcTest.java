package com.example.demorest.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerWebMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Test
    public void getProductById() throws Exception {
        // Arrange
        when(productService.getById(1))
                .thenReturn(new ProductResponse( 1, "Stub name 1", 200.50 ));

        MvcResult response = mvc.perform(get("/products/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        byte[] responseByte = response.getResponse().getContentAsByteArray();
        ObjectMapper objectMapper = new ObjectMapper();
        ProductResponse result = objectMapper.readValue(responseByte, ProductResponse.class);
        // Assert
        assertEquals(1, result.getId());
        assertEquals("Stub name 1", result.getName());
    }
}