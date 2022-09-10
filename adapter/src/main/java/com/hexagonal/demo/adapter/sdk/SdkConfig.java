package com.hexagonal.demo.adapter.sdk;

import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

/**
 * This is a dummy config that has to simulate a real SDK
 */
@Configuration
public class SdkConfig {

    @Service
    public static class DiscountService {

        private final Map<Integer, Discount> productMap = Map.ofEntries(
                entry(1, new Discount(1, 15))
        );

        public Discount getProductDiscount(Integer productId) {
            return productMap.get(productId);
        }
    }

    @Service
    public static class WarehouseService {

        private final Map<Integer, Product> productsMap = Map.ofEntries(
                entry(1, new Product(1, 24)),
                entry(2, new Product(2, 1)),
                entry(3, new Product(3, 51))
        );

        public Product getProductAvailability(Integer productId) {
            return productsMap.get(productId);
        }

        public List<Product> getAllAvailableProducts() {
            return productsMap.values().stream()
                    .filter(product -> product.getAvailableInWarehouse() > 0)
                    .toList();
        }

        public synchronized void remove(Integer amount, ProductDomainModel product) {
            var warehouseProduct = productsMap.get(product.getId());

            if (warehouseProduct.getAvailableInWarehouse() < amount) {
                throw new IllegalArgumentException("The amount number is greater than the available products!");
            }

            warehouseProduct.setAvailableInWarehouse(warehouseProduct.getAvailableInWarehouse() - amount);
        }
    }

    @Data
    @AllArgsConstructor
    public static class Discount {

        private Integer productId;
        private Integer discount;
    }

    @Data
    @AllArgsConstructor
    public static class Product {

        private Integer productId;
        private Integer availableInWarehouse;
    }
}