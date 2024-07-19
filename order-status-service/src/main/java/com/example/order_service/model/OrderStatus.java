package com.example.order_service.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class OrderStatus {
    private String status;
    private Instant date;
}
