package com.springdemo.springdatajpahomework.service.serviceImp;

import com.springdemo.springdatajpahomework.enums.Status;
import com.springdemo.springdatajpahomework.model.Customers;
import com.springdemo.springdatajpahomework.model.OrderItems;
import com.springdemo.springdatajpahomework.model.Orders;
import com.springdemo.springdatajpahomework.model.Products;
import com.springdemo.springdatajpahomework.model.dto.request.OrderRequest;
import com.springdemo.springdatajpahomework.repository.CustomerRepository;
import com.springdemo.springdatajpahomework.repository.OrderRepository;
import com.springdemo.springdatajpahomework.repository.ProductRepository;
import com.springdemo.springdatajpahomework.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    @Override
    public Orders createOrder(Long id,List<OrderRequest> requests) {
        Customers customers = customerRepository.findById(id).orElseThrow();
        Orders orders = Orders.builder()
                .status(Status.PENDING)
                .orderDate(LocalDateTime.now())
                .customers(customers)
                .orderItems(new ArrayList<>())
                .build();

        BigDecimal totalAmount = BigDecimal.ZERO;
        for(OrderRequest request : requests){
            Products products = productRepository.findById(request.getProductId()).orElseThrow();
            BigDecimal amount = products.getUnitPrice().multiply(BigDecimal.valueOf(request.getQuantity()));
            totalAmount = totalAmount.add(amount);

            OrderItems orderItems = OrderItems.builder()
                    .products(products)
                    .quantity(request.getQuantity())
                    .build();
            orderItems.setOrders(orders);
            orders.getOrderItems().add(orderItems);
        }
        orders.setTotalAmount(totalAmount);
        return orderRepository.save(orders);
    }
}
