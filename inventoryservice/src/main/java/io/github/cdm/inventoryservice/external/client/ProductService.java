package io.github.cdm.inventoryservice.external.client;

import io.github.cdm.inventoryservice.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE/api/v1/products")
public interface ProductService {

    @GetMapping("/getAllShopWithoutPagination")
    List<ProductResponse> getProducts();

}
