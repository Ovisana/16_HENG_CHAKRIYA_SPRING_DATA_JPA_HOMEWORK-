package com.springdemo.springdatajpahomework.model.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @NotNull(message = "Quantity cannot be empty")
    @Min(message = "Quantity cannot be 0",value = 0)
    @Max(message = "Quantity cannot be upper than 10000",value = 10000)
    private Integer quantity;
    @NotNull(message = "Quantity cannot be empty")
    @Min(message = "Quantity cannot be 0",value = 0)
    @Max(message = "Quantity cannot be upper than 10000",value = 10000)
    private Long productId;
}
