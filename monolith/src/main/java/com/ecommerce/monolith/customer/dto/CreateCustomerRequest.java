package com.ecommerce.monolith.customer.dto;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateCustomerRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email
    private String email;
    private String phone;
}