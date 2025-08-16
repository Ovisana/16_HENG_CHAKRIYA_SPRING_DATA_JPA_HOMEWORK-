package com.springdemo.springdatajpahomework.service.serviceImp;

import com.springdemo.springdatajpahomework.enums.CustomerProperties;
import com.springdemo.springdatajpahomework.enums.ProductProperties;
import com.springdemo.springdatajpahomework.exception.NotFoundException;
import com.springdemo.springdatajpahomework.model.CustomerAccounts;
import com.springdemo.springdatajpahomework.model.Customers;
import com.springdemo.springdatajpahomework.model.Products;
import com.springdemo.springdatajpahomework.model.dto.request.CustomerRequest;
import com.springdemo.springdatajpahomework.model.dto.response.CustomerResponse;
import com.springdemo.springdatajpahomework.model.dto.response.ListResponse;
import com.springdemo.springdatajpahomework.model.dto.response.PaginationResponse;
import com.springdemo.springdatajpahomework.model.dto.response.ProductResponse;
import com.springdemo.springdatajpahomework.repository.CustomerRepository;
import com.springdemo.springdatajpahomework.service.CustomerService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ListResponse<CustomerResponse> getAll(@Positive Integer page, Integer size, CustomerProperties customerProperties, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page-1,size,Sort.by(direction,customerProperties.getName()));
        Page<Customers> pageOfProduct = customerRepository.findAll(pageable);

        PaginationResponse pagination = PaginationResponse.builder()
                .currentPage(pageOfProduct.getNumber()+1)
                .pageSize(pageOfProduct.getSize())
                .totalElements(pageOfProduct.getTotalElements())
                .totalPages(pageOfProduct.getTotalPages())
                .build();
        List<Customers> customers = pageOfProduct.getContent();
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (Customers customer : customers){
            customerResponses.add(customer.customerResponse());
        }
        return ListResponse.<CustomerResponse>builder()
                .pagination(pagination)
                .items(customerResponses)
                .build();
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customers customers = customerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Customer Not Found")
        );
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
        Customers customers = customerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Customer Not Found")
        );
        customerRepository.delete(customers);
        return null;
    }

    @Override
    public CustomerResponse searchCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("customer id not found")
        ).customerResponse();
    }
}
