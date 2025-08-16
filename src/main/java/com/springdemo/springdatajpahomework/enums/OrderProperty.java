package com.springdemo.springdatajpahomework.enums;

import lombok.Getter;

@Getter
public enum OrderProperty {
    ID("orderId"),ORDER_DATE("orderDate"),TOTAL_AMOUNT("totalAmount");
    private final String name;

    OrderProperty(String name) {
        this.name = name;
    }
}
