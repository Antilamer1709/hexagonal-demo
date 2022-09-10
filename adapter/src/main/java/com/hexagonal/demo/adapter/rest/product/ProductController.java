package com.hexagonal.demo.adapter.rest.product;

import com.hexagonal.demo.adapter.rest.product.dto.ProductDetailsDto;
import com.hexagonal.demo.adapter.rest.product.dto.ProductDto;
import com.hexagonal.demo.domain.port.api.product.ProductServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.hexagonal.demo.adapter.rest.product.mapper.ProductRestMapper.PRODUCT_REST_MAPPER;

@Tag(name = "product", description = "Product operations")
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServicePort productServicePort;

    @Operation(summary = "Get all available products")
    @GetMapping
    public List<ProductDto> getAllProducts() {
        return PRODUCT_REST_MAPPER.toDtoList(productServicePort.getAllProducts());
    }

    @Operation(summary = "Find the product details by id")
    @GetMapping("{id}")
    public ProductDetailsDto getProductDetails(@Parameter(description = "Id of the product") @PathVariable Integer id) {
        return PRODUCT_REST_MAPPER.toProductDetailsDto(productServicePort.getProductById(id));
    }
}