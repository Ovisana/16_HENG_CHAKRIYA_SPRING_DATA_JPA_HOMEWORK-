package com.springdemo.springdatajpahomework.service.serviceImp;

import com.springdemo.springdatajpahomework.enums.ProductProperties;
import com.springdemo.springdatajpahomework.exception.NotFoundException;
import com.springdemo.springdatajpahomework.model.Products;
import com.springdemo.springdatajpahomework.model.dto.request.ProductRequest;
import com.springdemo.springdatajpahomework.model.dto.response.ListResponse;
import com.springdemo.springdatajpahomework.model.dto.response.PaginationResponse;
import com.springdemo.springdatajpahomework.model.dto.response.ProductResponse;
import com.springdemo.springdatajpahomework.repository.ProductRepository;
import com.springdemo.springdatajpahomework.service.ProductService;
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
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse create(ProductRequest request) {
        Products products = new Products();
        products.setName(request.getName());
        products.setUnitPrice(request.getUnitPrice());
        products.setDescription(request.getDescription());
        return productRepository.save(products).toResponse();
    }

    @Override
    public ListResponse<ProductResponse> getAll(@Positive Integer page, Integer size, ProductProperties productProperties, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page-1,size,Sort.by(direction,productProperties.getName()));
        Page<Products> pageOfProduct = productRepository.findAll(pageable);

        PaginationResponse pagination = PaginationResponse.builder()
                .currentPage(pageOfProduct.getNumber()+1)
                .pageSize(pageOfProduct.getSize())
                .totalElements(pageOfProduct.getTotalElements())
                .totalPages(pageOfProduct.getTotalPages())
                .build();
        List<Products> products = pageOfProduct.getContent();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Products product : products){
            productResponses.add(product.toResponse());
        }
        return ListResponse.<ProductResponse>builder()
                .pagination(pagination)
                .items(productResponses)
                .build();
    }

    @Override
    public ProductResponse updateById(Long id, ProductRequest request) {
        Products products = productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product Not Found")
        );
        products.setName(request.getName());
        products.setDescription(request.getDescription());
        products.setUnitPrice(request.getUnitPrice());
        return productRepository.save(products).toResponse();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Product Not Found")
        );
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse searchProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("Product Not Found")
        ).toResponse();
    }
}
