package com.springdemo.springdatajpahomework.service.serviceImp;

import com.springdemo.springdatajpahomework.enums.OrderProperty;
import com.springdemo.springdatajpahomework.enums.Status;
import com.springdemo.springdatajpahomework.exception.NotFoundException;
import com.springdemo.springdatajpahomework.model.Customers;
import com.springdemo.springdatajpahomework.model.OrderItems;
import com.springdemo.springdatajpahomework.model.Orders;
import com.springdemo.springdatajpahomework.model.Products;
import com.springdemo.springdatajpahomework.model.dto.request.OrderRequest;
import com.springdemo.springdatajpahomework.model.dto.response.ListResponse;
import com.springdemo.springdatajpahomework.model.dto.response.OrderResponse;
import com.springdemo.springdatajpahomework.model.dto.response.PaginationResponse;
import com.springdemo.springdatajpahomework.model.dto.response.ProductResponse;
import com.springdemo.springdatajpahomework.repository.CustomerRepository;
import com.springdemo.springdatajpahomework.repository.OrderRepository;
import com.springdemo.springdatajpahomework.repository.ProductRepository;
import com.springdemo.springdatajpahomework.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public OrderResponse createOrder(Long id,List<OrderRequest> requests) {
        Customers customers = customerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Customer Not Found")
        );
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
        return orderRepository.save(orders).toResponse();
    }

    @Override
    public OrderResponse searchById(Long id) {
        Orders orders = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order Not Found")
        );
        return orders.toResponse();

    }

    @Override
    public OrderResponse UpdateOrderStatus(Long orderId, Status status) {
        Orders orders = orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException("Order Not Found")
        );
        orders.setStatus(status);
        return orderRepository.save(orders).toResponse();
    }

    @Override
    public void DeleteOrderById(Long orderId) {
        orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException("Order Not Found")
        );
        orderRepository.deleteById(orderId);
    }

    @Override
    public ListResponse<OrderResponse> SearchOrderByCustomerId(Long customerId, Integer page, Integer size, OrderProperty orderProperty, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page-1,size,Sort.by(direction,orderProperty.getName()));
        Page<Orders> pageOfOrders = orderRepository.findAll(pageable);

        PaginationResponse pagination = PaginationResponse.builder()
                .currentPage(pageOfOrders.getNumber()+1)
                .pageSize(pageOfOrders.getSize())
                .totalElements(pageOfOrders.getTotalElements())
                .totalPages(pageOfOrders.getTotalPages())
                .build();
        List<Orders> orders = pageOfOrders.getContent();
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Orders order : orders){
            if(order.getCustomers().getCustomerId().equals(customerId)){
                orderResponses.add(order.toResponse());
            }

        }
        return ListResponse.<OrderResponse>builder()
                .pagination(pagination)
                .items(orderResponses)
                .build();
    }

}
