package io.github.cdm.energyservice.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnergyDto {
    private String name;
    private String description;
    private String image_url;
    private String price;
    private Boolean available;
}
