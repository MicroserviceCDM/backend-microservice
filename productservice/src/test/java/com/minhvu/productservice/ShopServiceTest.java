package com.minhvu.productservice;

import com.minhvu.productservice.dto.CreateShopRequest;
import com.minhvu.productservice.dto.UpdateShopRequest;
import com.minhvu.productservice.model.Shop;
import com.minhvu.productservice.repository.ShopRepository;
import com.minhvu.productservice.service.ShopServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ShopServiceTest {

    @InjectMocks
    private ShopServiceImpl shopService;

    @Mock
    private ShopRepository shopRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllReturnsPageOfProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        when(shopRepository.findAll(pageable)).thenReturn(Page.empty());

        Page<Shop> result = shopService.findAll(pageable);

        assertNotNull(result);
    }

    @Test
    public void getProductByIdReturnsNullWhenNotFound() {
        when(shopRepository.findById(any())).thenReturn(Optional.empty());

        Shop result = shopService.getProductById("1");

        assertEquals(null, result);
    }

    @Test
    public void createProductReturnsCreatedProduct() {
        CreateShopRequest request = new CreateShopRequest();
        Shop shop = new Shop();
        when(shopRepository.save(any())).thenReturn(shop);

        Shop result = shopService.createProduct(request);

        assertEquals(shop, result);
    }

    @Test
    public void updateProductReturnsUpdatedProduct() {
        UpdateShopRequest request = new UpdateShopRequest();
        Shop shop = new Shop();
        when(shopRepository.findById(any())).thenReturn(Optional.of(shop));
        when(shopRepository.save(any())).thenReturn(shop);

        Shop result = shopService.updateProduct(request);

        assertEquals(shop, result);
    }

    @Test
    public void findShopsByNameOrderedByPriceDescReturnsListOfProducts() {
        when(shopRepository.findDistinctByNameAllIgnoreCaseOrderByPriceAsc(any(), any())).thenReturn(Collections.singletonList(new Shop()));

        var result = shopService.findShopsByNameOrderedByPriceDesc("name", false);

        assertEquals(1, result.size());
    }

    @Test
    public void findProductByTypeIgnoreCaseReturnsListOfProducts() {
        when(shopRepository.findDistinctByTypeAllIgnoreCase(any())).thenReturn(Collections.singletonList(new Shop()));

        var result = shopService.findProductByTypeIgnoreCase("type");

        assertEquals(1, result.size());
    }

    @Test
    public void findShopByNameContainsReturnsListOfProducts() {
        when(shopRepository.findByNameContains(any())).thenReturn(Collections.singletonList(new Shop()));

        var result = shopService.findShopByNameContains("name");

        assertEquals(1, result.size());
    }
}
