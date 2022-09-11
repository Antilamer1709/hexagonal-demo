package com.hexagonal.demo.adapter.rest.order.mapper;

import com.hexagonal.demo.adapter.rest.order.dto.CreateOrderDtoBuilder;
import com.hexagonal.demo.adapter.rest.order.dto.OrderDtoBuilder;
import com.hexagonal.demo.domain.model.order.OrderDomainModelBuilder;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.hexagonal.demo.adapter.rest.order.mapper.OrderRestMapper.ORDER_REST_MAPPER;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = OrderRestMapper.class)
class OrderRestMapperTest {

    @Test
    void shouldMapToDto() {
        val actual = ORDER_REST_MAPPER.toDto(
                new OrderDomainModelBuilder()
                        .defaultOrder()
                        .build()
        );

        assertThat(actual).isEqualTo(new OrderDtoBuilder().defaultOrder().build());
    }

    @Test
    void shouldMapToDtoList() {
        val actual = ORDER_REST_MAPPER.toDtoList(
                new OrderDomainModelBuilder()
                        .defaultOrder()
                        .buildMany(3)
        );

        assertThat(actual).isEqualTo(new OrderDtoBuilder().defaultOrder().buildMany(3));
    }

    @Test
    void shouldMapToDomainModel() {
        val actual = ORDER_REST_MAPPER.toDomainModel(
                new CreateOrderDtoBuilder()
                        .defaultOrder()
                        .build()
        );

        assertThat(actual).isEqualTo(
                new OrderDomainModelBuilder()
                        .withProductId(1)
                        .withAmount(1)
                        .build()
        );
    }
}