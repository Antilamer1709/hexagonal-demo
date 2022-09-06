package com.hexagonal.demo.adapter.rest.product;

import com.hexagonal.demo.adapter.rest.product.dto.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return new ArrayList<>();
    }

    @GetMapping("{id}")
    public ProductDto getProductById(@PathVariable String id) {
        return new ProductDto();
    }
}