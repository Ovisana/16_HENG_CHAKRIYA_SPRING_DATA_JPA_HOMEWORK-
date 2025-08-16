package com.springdemo.springdatajpahomework.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderItemId implements Serializable {
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "order_id")
    private Long orderId;
}
