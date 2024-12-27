package com.minhvu.inventoryservice.service;

import com.minhvu.inventoryservice.dto.InventoryRequest;
import com.minhvu.inventoryservice.dto.InventoryResponse;
import com.minhvu.inventoryservice.dto.ProductResponse;
import com.minhvu.inventoryservice.exception.ProductServiceCustomException;
import com.minhvu.inventoryservice.external.client.ProductService;
import com.minhvu.inventoryservice.model.Inventory;
import com.minhvu.inventoryservice.repository.InventoryRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductService productService;

//    @Transactional(readOnly = true)
//    @SneakyThrows
//    public List<InventoryResponse> isInStock(List<String> skuCode) {
//        log.info("Checking Inventory");
//        return inventoryRepository.findByProductIdIn(skuCode).stream()
//                .map(inventory ->
//                        InventoryResponse.builder()
//                                .skuCode(inventory.getProductId())
//                                .isInStock(inventory.getQuantity() > 0)
//                                .build()
//                ).toList();
//    }


//    @Override
//    public List<Inventory> findAll(int page, int pageSize) {
//        Pageable pageable = PageRequest.of(page, pageSize);
//        Page<Inventory> inventories = inventoryRepository.findAll(pageable);
//        return inventories.toList();
//    }

    @Override
    public Page<InventoryResponse> findAll(Pageable pageable) {
        Page<Inventory> inventories = inventoryRepository.findAll(pageable);
        List<ProductResponse> productResponseList = getProductResponses(inventories);
        List<InventoryResponse> inventoryResponses = inventories.stream()
                .map(inventory -> {
                    List<ProductResponse> matchedProducts = productResponseList.stream()
                            .filter(productResponse -> productResponse.getId().equals(inventory.getProductId()))
                            .collect(Collectors.toList());
                    return InventoryResponse.builder()
                            .products(matchedProducts)
                            .build();
                })
                .filter(inventoryResponse -> !inventoryResponse.getProducts().isEmpty())
                .collect(Collectors.toList());
        return new PageImpl<>(inventoryResponses, pageable, inventoryResponses.size());
    }

    @NonNull
    private List<ProductResponse> getProductResponses(Page<Inventory> inventories) {
        List<ProductResponse> productResponseList = productService.getProducts();
        if (productResponseList.isEmpty()) {
            throw new ProductServiceCustomException("Product not found", "PRODUCT_NOT_FOUND");
        }
        productResponseList.forEach(productResponse -> {
            inventories.forEach(inventory -> {
                if (productResponse.getId().equals(inventory.getProductId())) {
                    productResponse.setQuantity(inventory.getQuantity());
                }
            });
        });
        return productResponseList;
    }

    @Override
    public String create(InventoryRequest inventory) {
        Inventory newInventory = Inventory.builder()
                .productId(inventory.getProductId())
                .quantity(inventory.getQuantity())
                .build();
        inventoryRepository.save(newInventory);
        return "Create success";
    }

    @Override
    public Inventory update(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public String delete(Inventory inventory) {
        inventoryRepository.delete(inventory);
        return "Delete success";
    }

    @Override
    public Inventory findById(String id) {
        return inventoryRepository.findByProductId(id).orElseThrow();
    }

    @Override
    public Optional<Inventory> findByProductIdContainsAllIgnoreCase(String skuCode) {
        return inventoryRepository.findByProductIdContainsAllIgnoreCase(skuCode);
    }

    @Override
    public List<ProductResponse> getProducts() {
        return productService.getProducts();
    }

    @Override
    public void reduceQuantity(String productId, long quantity) {

        log.info("Reduce Quantity {} for Id: {}", quantity, productId);

        Inventory inventory
                = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ProductServiceCustomException(
                        "Product with given Id not found",
                        "PRODUCT_NOT_FOUND"
                ));

        if (inventory.getQuantity() < quantity) {
            throw new ProductServiceCustomException(
                    "Product does not have sufficient Quantity",
                    "INSUFFICIENT_QUANTITY"
            );
        }

        inventory.setQuantity((int) (inventory.getQuantity() - quantity));
        inventoryRepository.save(inventory);
        log.info("Product Quantity updated Successfully");
    }

    @Override
    public List<Inventory> findAllInventory() {
        return inventoryRepository.findAll();
    }
}
