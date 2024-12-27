package com.minhvu.orderservice.dto;

import lombok.Data;

@Data
public class UpdateOrderRequest {
    private String id;
    private String shippingStatus;
    private String paymentStatus;
}
