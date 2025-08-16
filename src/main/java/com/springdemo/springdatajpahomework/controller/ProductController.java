package com.springdemo.springdatajpahomework.controller;

import com.springdemo.springdatajpahomework.enums.ProductProperties;
import com.springdemo.springdatajpahomework.model.Products;
import com.springdemo.springdatajpahomework.model.dto.request.ProductRequest;
import com.springdemo.springdatajpahomework.model.dto.response.ApiResponse;
import com.springdemo.springdatajpahomework.model.dto.response.BaseResponse;
import com.springdemo.springdatajpahomework.model.dto.response.ListResponse;
import com.springdemo.springdatajpahomework.model.dto.response.ProductResponse;
import com.springdemo.springdatajpahomework.repository.ProductRepository;
import com.springdemo.springdatajpahomework.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@RequestBody @Valid ProductRequest request){
        return BaseResponse.success(productService.create(request),"create Products successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ListResponse<ProductResponse>>> getAllProduct(@RequestParam(defaultValue = "1") @Positive Integer page,
                                                                                    @RequestParam(defaultValue = "10") Integer size,
                                                                                    @RequestParam ProductProperties productProperties,
                                                                                    @RequestParam Sort.Direction direction){
        return BaseResponse.success(productService.getAll(page,size,productProperties,direction),"Get all Products successfully");
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ApiResponse<ProductResponse>> searchProductById(@PathVariable("product-id") Long productId){
        return BaseResponse.success(productService.searchProductById(productId),"Search Products successfully");
    }

    @PutMapping
    public ResponseEntity<ApiResponse<ProductResponse>> updateProductById(@RequestParam @Min(1) Long id, @RequestBody @Valid ProductRequest request){
        return BaseResponse.success(productService.updateById(id,request),"Update Products successfully");
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteProductById(@RequestParam @Min(1) Long id){
        productService.deleteById(id);
        return BaseResponse.success(null,"Delete Products successfully");
    }
}
