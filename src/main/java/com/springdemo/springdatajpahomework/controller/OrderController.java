package com.springdemo.springdatajpahomework.controller;

import com.springdemo.springdatajpahomework.model.Orders;
import com.springdemo.springdatajpahomework.model.dto.request.OrderRequest;
import com.springdemo.springdatajpahomework.model.dto.response.ApiResponse;
import com.springdemo.springdatajpahomework.model.dto.response.BaseResponse;
import com.springdemo.springdatajpahomework.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<Orders>> createOrder(@RequestParam Long id, @RequestBody List<OrderRequest> request){
        return BaseResponse.success(orderService.createOrder(id,request),"Create order successfully");
    }
}
