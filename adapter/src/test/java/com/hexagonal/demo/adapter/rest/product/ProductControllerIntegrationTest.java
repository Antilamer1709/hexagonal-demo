package com.hexagonal.demo.adapter.rest.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexagonal.demo.adapter.rest.product.dto.DiscountDtoTestBuilder;
import com.hexagonal.demo.adapter.rest.product.dto.ProductDetailsDtoTestBuilder;
import com.hexagonal.demo.adapter.rest.product.dto.ProductDtoTestBuilder;
import com.hexagonal.demo.domain.model.product.DiscountDomainModelTestBuilder;
import com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder;
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
                new ProductDomainModelTestBuilder().defaultProduct().buildMany(3)
        );

        mockMvc.perform(get(UNDER_TEST))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(
                        new ProductDtoTestBuilder().defaultProduct().buildMany(3)
                )));
    }

    @Test
    void shouldFindProductDetailsById() throws Exception {
        var productId = 1;

        when(productServicePort.getProductById(productId)).thenReturn(
                new ProductDomainModelTestBuilder()
                        .defaultProduct()
                        .withDiscount(new DiscountDomainModelTestBuilder().defaultDiscount().build())
                        .build()
        );

        mockMvc.perform(get(UNDER_TEST + "/" + productId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(
                        new ProductDetailsDtoTestBuilder()
                                .defaultProduct()
                                .withDiscount(new DiscountDtoTestBuilder().defaultDiscount().build())
                                .build()
                )));
    }
}