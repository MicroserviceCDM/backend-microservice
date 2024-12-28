package io.github.cdm.productservice.dto;

import io.github.cdm.productservice.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateShopRequest {
    private String name;
    private BigDecimal price;
    private String description;
    private String image_url;
    private String type;
    @Enumerated(EnumType.STRING)
    private Status status;
}
