package com.springdemo.springdatajpahomework.enums;

import lombok.Getter;

@Getter
public enum CustomerProperties {
    ID("customerId"),NAME("name");
    private final String name;

    CustomerProperties(String name) {
        this.name = name;
    }
}
