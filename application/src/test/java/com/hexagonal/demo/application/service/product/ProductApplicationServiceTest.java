package com.hexagonal.demo.application.service.product;

import com.hexagonal.demo.domain.model.product.DiscountDomainModelBuilder;
import com.hexagonal.demo.domain.model.product.ProductDomainModel;
import com.hexagonal.demo.domain.model.product.ProductDomainModelBuilder;
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
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

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
        var warehouseProducts = new ProductDomainModelBuilder().warehouseProduct().buildMany(3);
        var jpaProducts = new ProductDomainModelBuilder().jpaProduct().buildMany(3);
        var s3Products = new ProductDomainModelBuilder().s3Product().buildMany(3);
        var availableIds = warehouseProducts.stream().map(ProductDomainModel::getId).toList();

        when(warehouseSdkPort.getAvailableProducts()).thenReturn(warehouseProducts);
        when(productJpaPort.getAllProductByIds(availableIds)).thenReturn(jpaProducts);
        when(discountSdkPort.getDiscount(any())).thenReturn(null);
        for (var jpaProduct : jpaProducts)
            when(productS3Port.getMainPicture(jpaProduct)).thenReturn(s3Products.get(jpaProduct.getId() - 1).getMainPicture());

        val actual = underTest.getAllProducts();

        assertThat(actual).isEqualTo(
                new ProductDomainModelBuilder()
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
        var jpaProduct = new ProductDomainModelBuilder().jpaProduct().build();
        var s3Product = new ProductDomainModelBuilder().s3Product().build();
        var warehouseProduct = new ProductDomainModelBuilder().warehouseProduct().build();
        var discount = new DiscountDomainModelBuilder().defaultDiscount().build();

        when(productJpaPort.getProductById(jpaProduct.getId())).thenReturn(jpaProduct);
        when(productS3Port.getPictures(jpaProduct)).thenReturn(s3Product);
        when(warehouseSdkPort.getAvailableAmount(jpaProduct)).thenReturn(warehouseProduct);
        when(discountSdkPort.getDiscount(jpaProduct)).thenReturn(discount);

        val actual = underTest.getProductById(jpaProduct.getId());

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(
                        new ProductDomainModelBuilder()
                                .defaultProduct()
                                .withDiscount(new DiscountDomainModelBuilder().defaultDiscount().build())
                                .build()
                );

        InOrder inOrder = inOrder(productJpaPort, productS3Port, discountSdkPort, warehouseSdkPort);
        inOrder.verify(productJpaPort).getProductById(jpaProduct.getId());
        inOrder.verify(productS3Port).getPictures(jpaProduct);
        inOrder.verify(discountSdkPort).getDiscount(jpaProduct);
        inOrder.verify(warehouseSdkPort).getAvailableAmount(jpaProduct);
    }
}
