package com.springdemo.springdatajpahomework.model.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotBlank(message = "Name cannot be Empty")
    @Size(message = "Name cannot be longer than 50 characters",max = 50)
    private String name;
    @NotNull(message = "Price cannot be empty")
    @DecimalMin(message = "Price cannot be 0.0",value = "0.0")
    private BigDecimal unitPrice;
    @NotBlank(message = "Description cannot be Empty")
    private String description;
}
