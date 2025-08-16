package com.springdemo.springdatajpahomework.service;

import com.springdemo.springdatajpahomework.enums.CustomerProperties;
import com.springdemo.springdatajpahomework.enums.ProductProperties;
import com.springdemo.springdatajpahomework.model.Customers;
import com.springdemo.springdatajpahomework.model.dto.request.CustomerRequest;
import com.springdemo.springdatajpahomework.model.dto.response.CustomerResponse;
import com.springdemo.springdatajpahomework.model.dto.response.ListResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(CustomerRequest request);

    ListResponse<CustomerResponse> getAll(@Positive Integer page, Integer size, CustomerProperties customerProperties, Sort.Direction direction);

    CustomerResponse update(Long id,CustomerRequest request);

    CustomerResponse delete(Long id);

    CustomerResponse searchCustomerById(Long id);
}
