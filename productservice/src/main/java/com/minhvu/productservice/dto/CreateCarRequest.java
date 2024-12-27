package com.minhvu.productservice.dto;

import com.minhvu.productservice.model.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateCarRequest {
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
