package com.minhvu.inventoryservice.external.client;

import com.minhvu.inventoryservice.dto.ProductResponse;
import com.minhvu.inventoryservice.exception.CustomException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE/api/v1/products")
public interface ProductService {

    @GetMapping("/getAllShopWithoutPagination")
    List<ProductResponse> getProducts();

}
