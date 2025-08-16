package com.springdemo.springdatajpahomework.service;

import com.springdemo.springdatajpahomework.enums.OrderProperty;
import com.springdemo.springdatajpahomework.enums.Status;
import com.springdemo.springdatajpahomework.model.Orders;
import com.springdemo.springdatajpahomework.model.dto.request.OrderRequest;
import com.springdemo.springdatajpahomework.model.dto.response.ListResponse;
import com.springdemo.springdatajpahomework.model.dto.response.OrderResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(Long id,List<OrderRequest> request);

    OrderResponse searchById(Long id);

    OrderResponse UpdateOrderStatus(Long orderId, Status status);

    void DeleteOrderById(Long orderId);

    ListResponse<OrderResponse> SearchOrderByCustomerId(Long customerId, @Positive Integer page, Integer size, OrderProperty orderProperty, Sort.Direction direction);
}
