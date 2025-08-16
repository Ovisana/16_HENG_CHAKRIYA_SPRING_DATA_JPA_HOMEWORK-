package com.springdemo.springdatajpahomework.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    private String name;
    private String address;
    private String phoneNumber;
    private String username;
    private String password;
}
