package io.github.cdm.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "energy")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Energy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    private String image_url;
    @Enumerated(EnumType.STRING)
    private Status status;
}
