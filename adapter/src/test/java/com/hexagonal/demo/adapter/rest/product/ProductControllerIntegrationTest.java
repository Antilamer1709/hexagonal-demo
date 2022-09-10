package com.hexagonal.demo.adapter.rest.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexagonal.demo.adapter.rest.product.dto.ProductDtoBuilder;
import com.hexagonal.demo.domain.model.product.ProductDomainModelBuilder;
import com.hexagonal.demo.domain.port.api.product.ProductServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(value = SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
@ContextConfiguration(classes = {ProductController.class})
class ProductControllerIntegrationTest {

    private static final String UNDER_TEST = "/products";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductServicePort productServicePort;

    @Test
    void shouldFindAllProducts() throws Exception {
        when(productServicePort.getAllProducts()).thenReturn(
                new ProductDomainModelBuilder().defaultProduct().buildMany(3)
        );

        mockMvc.perform(get(UNDER_TEST))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(
                        new ProductDtoBuilder().defaultProduct().buildMany(3)
                )));
    }
}