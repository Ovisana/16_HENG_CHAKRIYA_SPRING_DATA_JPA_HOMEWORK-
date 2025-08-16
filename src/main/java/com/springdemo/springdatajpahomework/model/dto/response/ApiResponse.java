package com.springdemo.springdatajpahomework.model.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private String message;
    private T payload;
    private HttpStatus status;
    @Builder.Default
    private LocalDateTime instant = LocalDateTime.now();
}
