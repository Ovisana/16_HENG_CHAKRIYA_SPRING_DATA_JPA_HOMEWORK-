package com.springdemo.springdatajpahomework.controller;

import com.springdemo.springdatajpahomework.model.Customers;
import com.springdemo.springdatajpahomework.model.dto.request.CustomerRequest;
import com.springdemo.springdatajpahomework.model.dto.response.ApiResponse;
import com.springdemo.springdatajpahomework.model.dto.response.BaseResponse;
import com.springdemo.springdatajpahomework.model.dto.response.CustomerResponse;
import com.springdemo.springdatajpahomework.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@RequestBody CustomerRequest request){
        return BaseResponse.success(customerService.create(request) , "Created customer successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomer(){
        return BaseResponse.success(customerService.getAll(),"Get All customers successfully");
    }

    @PutMapping("/{customer-id}")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomerById(@RequestParam Long id, @RequestBody CustomerRequest request){
        return BaseResponse.success(customerService.update(id,request),"Update customer successfully");
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomerById(@RequestParam Long id){
        customerService.delete(id);
        return BaseResponse.success(null,"Deleted customer successfully");
    }
}
