package com.springdemo.springdatajpahomework.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
    private Long customerId;
    private String name;
    private String address;
    private String phoneNumber;
    private String username;
    private String password;
    private boolean isActive;

}
