package com.springdemo.springdatajpahomework.service;

import com.springdemo.springdatajpahomework.model.Orders;
import com.springdemo.springdatajpahomework.model.dto.request.OrderRequest;

import java.util.List;

public interface OrderService {
    Orders createOrder(Long id,List<OrderRequest> request);
}
