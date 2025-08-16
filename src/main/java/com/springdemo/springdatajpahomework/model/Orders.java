package com.springdemo.springdatajpahomework.model;

import com.springdemo.springdatajpahomework.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    private LocalDateTime orderDate;
    private BigDecimal totalAmount;

    private Status status;
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customers customers;

    @OneToMany(mappedBy = "orders" , cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;
}
