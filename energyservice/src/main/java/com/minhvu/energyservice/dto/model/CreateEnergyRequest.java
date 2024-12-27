package com.minhvu.energyservice.dto.model;

import lombok.Data;

@Data
public class CreateEnergyRequest {
    private String name;
    private String description;
    private String image_url;
    private String price;
    private Boolean available;
}
