package com.springdemo.springdatajpahomework.service;

import com.springdemo.springdatajpahomework.enums.ProductProperties;
import com.springdemo.springdatajpahomework.model.dto.request.ProductRequest;
import com.springdemo.springdatajpahomework.model.dto.response.ListResponse;
import com.springdemo.springdatajpahomework.model.dto.response.ProductResponse;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);

    ListResponse<ProductResponse> getAll(@Positive Integer page, Integer size, ProductProperties productProperties, Sort.Direction direction);

    ProductResponse updateById(Long id, ProductRequest request);

    void deleteById(Long id);

    ProductResponse searchProductById(Long productId);
}
