package com.hexagonal.demo.adapter.rest.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexagonal.demo.adapter.rest.order.dto.CreateOrderDtoBuilder;
import com.hexagonal.demo.adapter.rest.order.dto.OrderDtoBuilder;
import com.hexagonal.demo.domain.model.order.OrderDomainModelBuilder;
import com.hexagonal.demo.domain.port.api.order.OrderServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(value = SpringExtension.class)
@WebMvcTest(controllers = OrderController.class)
@ContextConfiguration(classes = {OrderController.class})
class OrderControllerIntegrationTest {

    private static final String UNDER_TEST = "/orders";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderServicePort orderServicePort;

    @Test
    void shouldFindAllOrders() throws Exception {
        when(orderServicePort.getAllOrders()).thenReturn(
                new OrderDomainModelBuilder().defaultOrder().buildMany(3)
        );

        mockMvc.perform(get(UNDER_TEST))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(
                        new OrderDtoBuilder().defaultOrder().buildMany(3)
                )));
    }

    @Test
    void shouldCreateOrder() throws Exception {
        when(orderServicePort.createOrder(
                new OrderDomainModelBuilder()
                        .withProductId(1)
                        .withAmount(1)
                        .build())
        ).thenReturn(
                new OrderDomainModelBuilder()
                        .defaultOrder()
                        .build()
        );

        mockMvc.perform(post(UNDER_TEST)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new CreateOrderDtoBuilder().defaultOrder().build()
                        )))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(
                        new OrderDtoBuilder().defaultOrder().build()
                )));
    }
}