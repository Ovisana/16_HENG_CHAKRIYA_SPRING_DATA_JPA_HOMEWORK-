package com.springdemo.springdatajpahomework.model.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseResponse {

    public static <T> ResponseEntity<ApiResponse<T>> success(T payload,String message){
        ApiResponse<T> response = ApiResponse.<T>builder()
                .message(message)
                .payload(payload)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
