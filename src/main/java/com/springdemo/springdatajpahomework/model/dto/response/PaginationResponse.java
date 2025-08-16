package com.springdemo.springdatajpahomework.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationResponse {
    private Long totalElements;
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPages;
}
