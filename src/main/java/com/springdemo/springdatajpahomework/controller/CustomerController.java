package com.springdemo.springdatajpahomework.controller;

import com.springdemo.springdatajpahomework.enums.CustomerProperties;
import com.springdemo.springdatajpahomework.enums.ProductProperties;
import com.springdemo.springdatajpahomework.model.Customers;
import com.springdemo.springdatajpahomework.model.dto.request.CustomerRequest;
import com.springdemo.springdatajpahomework.model.dto.response.ApiResponse;
import com.springdemo.springdatajpahomework.model.dto.response.BaseResponse;
import com.springdemo.springdatajpahomework.model.dto.response.CustomerResponse;
import com.springdemo.springdatajpahomework.model.dto.response.ListResponse;
import com.springdemo.springdatajpahomework.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@RequestBody @Valid CustomerRequest request){
        return BaseResponse.success(customerService.create(request) , "Created customer successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ListResponse<CustomerResponse>>> getAllCustomer(@RequestParam(defaultValue = "1") @Positive Integer page,
                                                                                      @RequestParam(defaultValue = "10") Integer size,
                                                                                      @RequestParam CustomerProperties customerProperties,
                                                                                      @RequestParam Sort.Direction direction){
        return BaseResponse.success(customerService.getAll(page,size,customerProperties,direction),"Get All customers successfully");
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> searchCustomerById(@RequestParam @Min(1) Long id){
        return BaseResponse.success(customerService.searchCustomerById(id),"Search customer successfully");
    }

    @PutMapping("/{customer-id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomerById(@RequestParam @Min(1) Long id, @RequestBody @Valid  CustomerRequest request){
        return BaseResponse.success(customerService.update(id,request),"Update customer successfully");
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomerById(@RequestParam @Min(1) Long id){
        customerService.delete(id);
        return BaseResponse.success(null,"Deleted customer successfully");
    }
}
