package com.springdemo.springdatajpahomework.model.dto.response;

import com.springdemo.springdatajpahomework.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long orderId;
    @Builder.Default
    private LocalDateTime orderDate = LocalDateTime.now();
    private BigDecimal totalAmount;
    private Status status;
    private CustomerResponse customerResponse;
    private List<ProductResponse> productResponse;
}
