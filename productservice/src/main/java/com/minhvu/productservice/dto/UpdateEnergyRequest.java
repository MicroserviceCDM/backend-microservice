package com.minhvu.productservice.dto;

import com.minhvu.productservice.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
