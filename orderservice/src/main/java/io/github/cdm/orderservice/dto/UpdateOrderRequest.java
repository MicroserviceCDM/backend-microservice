package io.github.cdm.orderservice.dto;

import lombok.Data;

@Data
public class UpdateOrderRequest {
    private String id;
    private String shippingStatus;
    private String paymentStatus;
}
