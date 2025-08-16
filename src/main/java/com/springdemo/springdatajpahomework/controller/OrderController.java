package com.springdemo.springdatajpahomework.controller;

import com.springdemo.springdatajpahomework.enums.OrderProperty;
import com.springdemo.springdatajpahomework.enums.ProductProperties;
import com.springdemo.springdatajpahomework.enums.Status;
import com.springdemo.springdatajpahomework.model.dto.request.OrderRequest;
import com.springdemo.springdatajpahomework.model.dto.response.ApiResponse;
import com.springdemo.springdatajpahomework.model.dto.response.BaseResponse;
import com.springdemo.springdatajpahomework.model.dto.response.ListResponse;
import com.springdemo.springdatajpahomework.model.dto.response.OrderResponse;
import com.springdemo.springdatajpahomework.service.OrderService;
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
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(@RequestParam @Min(1) Long id, @RequestBody @Valid List<OrderRequest> request){
        return BaseResponse.success(orderService.createOrder(id,request),"Create order successfully");
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<ApiResponse<OrderResponse>> SearchOrderById(@RequestParam @Min(1) Long id){
        return BaseResponse.success(orderService.searchById(id),"Search order successfully");
    }

    @GetMapping("/lists/{customer-id}")
    public ResponseEntity<ApiResponse<ListResponse<OrderResponse>>> SearchOrderByCustomerId(@PathVariable("customer-id") Long customerId,
                                                                                           @RequestParam(defaultValue = "1") @Positive Integer page,
                                                                                           @RequestParam(defaultValue = "10") Integer size,
                                                                                           @RequestParam OrderProperty orderProperty,
                                                                                           @RequestParam Sort.Direction direction){
        return BaseResponse.success(orderService.SearchOrderByCustomerId(customerId,page,size,orderProperty,direction),"Search order successfully");
    }

    @PutMapping("/{order-id}/status")
    public ResponseEntity<ApiResponse<OrderResponse>> UpdateOrderStatus(@PathVariable("order-id") Long orderId, @RequestParam Status status){
        return BaseResponse.success(orderService.UpdateOrderStatus(orderId,status),"Update order successfully");
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity<ApiResponse<Void>> DeleteOrderById(@PathVariable("order-id") Long orderId){
        orderService.DeleteOrderById(orderId);
        return BaseResponse.success(null,"Delete order successfully");
    }
}
