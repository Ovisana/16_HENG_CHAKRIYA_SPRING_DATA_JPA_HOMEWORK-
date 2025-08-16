package com.springdemo.springdatajpahomework.service.serviceImp;

import com.springdemo.springdatajpahomework.model.Products;
import com.springdemo.springdatajpahomework.model.dto.request.ProductRequest;
import com.springdemo.springdatajpahomework.repository.ProductRepository;
import com.springdemo.springdatajpahomework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Products create(ProductRequest request) {
        Products products = new Products();
        products.setName(request.getName());
        products.setUnitPrice(request.getUnitPrice());
        products.setDescription(request.getDescription());
        return productRepository.save(products);
    }

    @Override
    public List<Products> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Products updateById(Long id, ProductRequest request) {
        Products products = productRepository.findById(id).orElseThrow();
        products.setName(request.getName());
        products.setDescription(request.getDescription());
        products.setUnitPrice(request.getUnitPrice());
        return productRepository.save(products);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
