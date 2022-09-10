package com.hexagonal.demo.gateway.rest.product;

import com.hexagonal.demo.gateway.rest.product.dto.ProductDetailsDto;
import com.hexagonal.demo.gateway.rest.product.dto.ProductDto;
import com.hexagonal.demo.domain.port.api.product.ProductServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.hexagonal.demo.gateway.rest.product.mapper.ProductRestMapper.PRODUCT_REST_MAPPER;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServicePort productServicePort;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return PRODUCT_REST_MAPPER.toDtoList(productServicePort.getAllProducts());
    }

    @GetMapping("{id}")
    public ProductDetailsDto getProductById(@PathVariable Integer id) {
        return PRODUCT_REST_MAPPER.toProductDetailsDto(productServicePort.getProductById(id));
    }
}