package com.springdemo.springdatajpahomework.enums;

import lombok.Getter;

@Getter
public enum ProductProperties {
    ID("productId"),NAME("name"),UNIT_PRICE("unitPrice");
    private final String name;

    ProductProperties(String name) {
        this.name = name;
    }
}
