package com.minhvu.productservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "car")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Car {

    @Id
    private String id;
    private String model;
    private String orgPrice;
    private String disPrice;
    private String perMonthPrice;
    private String trim;
    private String odo;
    private String range;
    private String topSpeed;
    private String timeToReach;
    private String tech;
    private List<String> keyFeatures;
    private String gift;
    private int count;
    private String imgSrc;
    @Enumerated(EnumType.STRING)
    private Status status;
}