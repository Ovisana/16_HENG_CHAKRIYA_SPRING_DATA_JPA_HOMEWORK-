package com.springdemo.springdatajpahomework.model;

import com.springdemo.springdatajpahomework.model.dto.response.CustomerResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customers {

    @Id
    private Long customerId;
    private String name;
    private String  address;
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private CustomerAccounts customerAccounts;

    @OneToMany(mappedBy = "customers")
    private List<Orders> orders;
    public CustomerResponse customerResponse(){
        return CustomerResponse.builder()
                .customerId(this.customerId)
                .address(this.address)
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .username(this.customerAccounts.getUsername())
                .password(this.customerAccounts.getPassword())
                .isActive(this.customerAccounts.isActive())
                .build();
    }
}
