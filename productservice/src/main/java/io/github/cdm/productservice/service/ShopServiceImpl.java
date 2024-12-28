package io.github.cdm.productservice.service;

import io.github.cdm.productservice.dto.CreateShopRequest;
import io.github.cdm.productservice.dto.UpdateShopRequest;
import io.github.cdm.productservice.model.Shop;
import io.github.cdm.productservice.repository.ShopRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    public Page<Shop> findAll(Pageable pageable) {
        return shopRepository.findAll(pageable);
    }

    @Override
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    @Override
    public Shop getProductById(String id) {
        return shopRepository.findById(id).orElse(null);
    }

    @Override
    public Shop createProduct(CreateShopRequest createShopRequest) {
        Shop shop = Shop.builder()
                .name(createShopRequest.getName())
                .price(createShopRequest.getPrice())
                .description(createShopRequest.getDescription())
                .image_url(createShopRequest.getImage_url())
                .type(createShopRequest.getType())
                .status(createShopRequest.getStatus())
                .build();
        return shopRepository.save(shop);
    }

    @Override
    public Shop updateProduct(UpdateShopRequest updateShopRequest) {
        Shop shop = shopRepository.findById(updateShopRequest.getId()).orElse(null);
        if (shop != null) {
            shop.setName(updateShopRequest.getName());
            shop.setPrice(updateShopRequest.getPrice());
            shop.setDescription(updateShopRequest.getDescription());
            shop.setImage_url(updateShopRequest.getImage_url());
            shop.setStatus(updateShopRequest.getStatus());
            shop.setType(updateShopRequest.getType());
            return shopRepository.save(shop);
        }
        throw new RuntimeException("Product not found");
    }

    @Override
    public void deleteProduct(String id) {
        shopRepository.deleteById(id);
    }
    @Override
    public List<Shop> findShopsByNameOrderedByPriceDesc(String name, boolean isAsc) {
        Sort sort = Sort.by("price");
        if (isAsc) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }
        return shopRepository.findDistinctByNameAllIgnoreCaseOrderByPriceAsc(name, sort);
    }

    @Override
    public List<Shop> findProductByTypeIgnoreCase(String type) {
        return shopRepository.findDistinctByTypeAllIgnoreCase(type);
    }

    @Override
    public List<Shop> findShopByNameContains(String name) {
        return shopRepository.findByNameContains(name);
    }
}
