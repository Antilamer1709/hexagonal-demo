package com.hexagonal.demo.adapter.rest.order.mapper;

import com.hexagonal.demo.adapter.rest.order.dto.CreateOrderDtoTestBuilder;
import com.hexagonal.demo.adapter.rest.order.dto.OrderDtoTestBuilder;
import com.hexagonal.demo.domain.model.order.OrderDomainModelTestBuilder;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static com.hexagonal.demo.adapter.rest.order.mapper.OrderRestMapper.ORDER_REST_MAPPER;
import static com.hexagonal.demo.domain.model.order.OrderDomainModelTestBuilder.TEST_FIRST_ORDER_CREATION_DATE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = OrderRestMapper.class)
class OrderRestMapperTest {

    @Test
    void shouldMapToDto() {
        val actual = ORDER_REST_MAPPER.toDto(
                new OrderDomainModelTestBuilder()
                        .defaultOrder()
                        .withCreationDate(LocalDateTime.parse(TEST_FIRST_ORDER_CREATION_DATE))
                        .build()
        );

        assertThat(actual).isEqualTo(
                new OrderDtoTestBuilder()
                        .defaultOrder()
                        .withCreationDate(LocalDateTime.parse(TEST_FIRST_ORDER_CREATION_DATE))
                        .build()
        );
    }

    @Test
    void shouldMapToDtoList() {
        val actual = ORDER_REST_MAPPER.toDtoList(
                new OrderDomainModelTestBuilder()
                        .defaultOrder()
                        .withCreationDate(LocalDateTime.parse(TEST_FIRST_ORDER_CREATION_DATE))
                        .buildMany(3)
        );

        assertThat(actual).isEqualTo(
                new OrderDtoTestBuilder()
                        .defaultOrder()
                        .withCreationDate(LocalDateTime.parse(TEST_FIRST_ORDER_CREATION_DATE))
                        .buildMany(3)
        );
    }

    @Test
    void shouldMapToDomainModel() {
        val actual = ORDER_REST_MAPPER.toDomainModel(
                new CreateOrderDtoTestBuilder()
                        .defaultOrder()
                        .build()
        );

        assertThat(actual).isEqualTo(
                new OrderDomainModelTestBuilder()
                        .withProductId(1)
                        .withAmount(1)
                        .build()
        );
    }
}