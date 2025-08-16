package com.springdemo.springdatajpahomework.controller;

import com.springdemo.springdatajpahomework.model.Products;
import com.springdemo.springdatajpahomework.model.dto.request.ProductRequest;
import com.springdemo.springdatajpahomework.model.dto.response.ApiResponse;
import com.springdemo.springdatajpahomework.model.dto.response.BaseResponse;
import com.springdemo.springdatajpahomework.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<Products>> createProduct(@RequestBody ProductRequest request){
        return BaseResponse.success(productService.create(request),"create Products successfully");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Products>>> getAllProduct(){
        return BaseResponse.success(productService.getAll(),"Get all Products successfully");
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Products>> updateProductById(@RequestParam Long id, @RequestBody ProductRequest request){
        return BaseResponse.success(productService.updateById(id,request),"Update Products successfully");
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteProductById(@RequestParam Long id){
        productService.deleteById(id);
        return BaseResponse.success(null,"Delete Products successfully");
    }
}
