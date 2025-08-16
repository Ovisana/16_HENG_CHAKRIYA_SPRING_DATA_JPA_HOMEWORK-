package com.springdemo.springdatajpahomework.service;

import com.springdemo.springdatajpahomework.model.Products;
import com.springdemo.springdatajpahomework.model.dto.request.ProductRequest;

import java.util.List;

public interface ProductService {
    Products create(ProductRequest request);

    List<Products> getAll();

    Products updateById(Long id, ProductRequest request);

    void deleteById(Long id);
}
