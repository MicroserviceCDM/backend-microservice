package io.github.cdm.productservice.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class UpdateEnergyRequest {
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    private String image_url;
    private String category;
    private String type;
}
