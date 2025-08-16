package com.springdemo.springdatajpahomework.service;

import com.springdemo.springdatajpahomework.model.Customers;
import com.springdemo.springdatajpahomework.model.dto.request.CustomerRequest;
import com.springdemo.springdatajpahomework.model.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(CustomerRequest request);

    List<CustomerResponse> getAll();

    CustomerResponse update(Long id,CustomerRequest request);

    CustomerResponse delete(Long id);
}
