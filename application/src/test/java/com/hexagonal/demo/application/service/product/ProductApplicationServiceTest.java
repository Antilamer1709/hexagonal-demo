package com.hexagonal.demo.application.service.product;

import com.hexagonal.demo.domain.model.product.DiscountDomainModelTestBuilder;
import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import com.hexagonal.demo.domain.model.product.ProductDomainModelTestBuilder;
import com.hexagonal.demo.domain.port.spi.product.DiscountSdkPort;
import com.hexagonal.demo.domain.port.spi.product.ProductJpaPort;
import com.hexagonal.demo.domain.port.spi.product.ProductS3Port;
import com.hexagonal.demo.domain.port.spi.product.WarehouseSdkPort;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductApplicationServiceTest {

    @Mock
    private ProductS3Port productS3Port;

    @Mock
    private ProductJpaPort productJpaPort;

    @Mock
    private DiscountSdkPort discountSdkPort;

    @Mock
    private WarehouseSdkPort warehouseSdkPort;

    @InjectMocks
    private ProductApplicationService underTest;

    @Test
    void shouldGetAllProducts() {
        var warehouseProducts = new ProductDomainModelTestBuilder().warehouseProduct().buildMany(3);
        var jpaProducts = new ProductDomainModelTestBuilder().jpaProduct().buildMany(3);
        var s3Products = new ProductDomainModelTestBuilder().s3Product().buildMany(3);
        var availableIds = warehouseProducts.stream().map(ProductDomainModel::getId).toList();

        when(warehouseSdkPort.getAvailableProducts()).thenReturn(warehouseProducts);
        when(productJpaPort.getAllProductByIds(availableIds)).thenReturn(jpaProducts);
        when(discountSdkPort.getDiscount(any())).thenReturn(null);
        for (var jpaProduct : jpaProducts)
            when(productS3Port.getMainPicture(jpaProduct)).thenReturn(s3Products.get(jpaProduct.getId() - 1).getMainPicture());

        val actual = underTest.getAllProducts();

        assertThat(actual).isEqualTo(
                new ProductDomainModelTestBuilder()
                        .defaultProduct()
                        .withPictureGallery(null)
                        .buildMany(3)
        );

        InOrder inOrder = inOrder(warehouseSdkPort, productJpaPort, productS3Port, discountSdkPort);
        inOrder.verify(warehouseSdkPort).getAvailableProducts();
        inOrder.verify(productJpaPort).getAllProductByIds(availableIds);
        inOrder.verify(productS3Port).getMainPicture(any());
        inOrder.verify(discountSdkPort).getDiscount(any());
    }

    @Test
    void shouldGetProductById() {
        var jpaProduct = new ProductDomainModelTestBuilder().jpaProduct().build();
        var s3Product = new ProductDomainModelTestBuilder().s3Product().build();
        var warehouseProduct = new ProductDomainModelTestBuilder().warehouseProduct().build();
        var discount = new DiscountDomainModelTestBuilder().defaultDiscount().build();

        when(productJpaPort.getProductById(jpaProduct.getId())).thenReturn(jpaProduct);
        when(productS3Port.getPictures(jpaProduct)).thenReturn(s3Product);
        when(warehouseSdkPort.getAvailableAmount(jpaProduct)).thenReturn(warehouseProduct);
        when(discountSdkPort.getDiscount(jpaProduct)).thenReturn(discount);

        val actual = underTest.getProductById(jpaProduct.getId());

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDomainModelTestBuilder()
                                .defaultProduct()
                                .withDiscount(new DiscountDomainModelTestBuilder().defaultDiscount().build())
                                .build()
                );

        InOrder inOrder = inOrder(productJpaPort, productS3Port, discountSdkPort, warehouseSdkPort);
        inOrder.verify(productJpaPort).getProductById(jpaProduct.getId());
        inOrder.verify(productS3Port).getPictures(jpaProduct);
        inOrder.verify(discountSdkPort).getDiscount(jpaProduct);
        inOrder.verify(warehouseSdkPort).getAvailableAmount(jpaProduct);
    }

    @Test
    void shouldRemoveFromWarehouse() {
        Integer amount = 1;
        var product = new ProductDomainModelTestBuilder().warehouseProduct().build();
        doNothing().when(warehouseSdkPort).remove(amount, product);

        underTest.removeFromWarehouse(amount, product);

        InOrder inOrder = inOrder(warehouseSdkPort);
        inOrder.verify(warehouseSdkPort).remove(amount, product);
    }
}
