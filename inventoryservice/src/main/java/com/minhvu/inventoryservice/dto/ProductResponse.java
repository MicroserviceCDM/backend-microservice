package com.minhvu.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String image_url;
    private String name;
    private String description;
    private BigDecimal price;
    private String type;
    private String status;
    private Integer quantity;
}
