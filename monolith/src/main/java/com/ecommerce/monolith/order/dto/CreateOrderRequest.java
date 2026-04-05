package com.ecommerce.monolith.order.dto;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateOrderRequest {
    @NotNull
    private Long customerId;
    @NotNull
    private Long productId;
    @NotNull @Positive
    private Integer quantity;
}