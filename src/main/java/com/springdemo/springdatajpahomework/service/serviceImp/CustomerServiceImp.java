package com.springdemo.springdatajpahomework.service.serviceImp;

import com.springdemo.springdatajpahomework.model.CustomerAccounts;
import com.springdemo.springdatajpahomework.model.Customers;
import com.springdemo.springdatajpahomework.model.dto.request.CustomerRequest;
import com.springdemo.springdatajpahomework.model.dto.response.CustomerResponse;
import com.springdemo.springdatajpahomework.repository.CustomerRepository;
import com.springdemo.springdatajpahomework.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse create(CustomerRequest request) {
        CustomerAccounts account = CustomerAccounts.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .isActive(true)
                .build();
        Customers customers = Customers.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .customerAccounts(account)
                .build();
        return customerRepository.save(customers).customerResponse();
    }

    @Override
    public List<CustomerResponse> getAll() {
        List<Customers> listCustomer = customerRepository.findAll();
        List<CustomerResponse> newList = new ArrayList<>();
        for (Customers customer : listCustomer){
            CustomerResponse response = new CustomerResponse();

            response.setCustomerId(customer.getCustomerId());
            response.setName(customer.getName());
            response.setPhoneNumber(customer.getPhoneNumber());
            response.setAddress(customer.getAddress());
            response.setPassword(customer.getCustomerAccounts().getPassword());
            response.setUsername(customer.getCustomerAccounts().getUsername());
            response.setActive(customer.getCustomerAccounts().isActive());
            newList.add(response);
            customer.customerResponse();
        }
        return newList;
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customers customers = customerRepository.findById(id).orElseThrow();

        customers.setName(request.getName());
        customers.setAddress(request.getAddress());
        customers.setPhoneNumber(request.getPhoneNumber());
        CustomerAccounts accounts = new CustomerAccounts();
        accounts.setUsername(request.getUsername());
        accounts.setPassword(request.getPassword());
        customers.setCustomerAccounts(accounts);

        return customerRepository.save(customers).customerResponse();
    }

    @Override
    public CustomerResponse delete(Long id) {
        Customers customers = customerRepository.findById(id).orElseThrow();
        customerRepository.delete(customers);
        return null;
    }
}
