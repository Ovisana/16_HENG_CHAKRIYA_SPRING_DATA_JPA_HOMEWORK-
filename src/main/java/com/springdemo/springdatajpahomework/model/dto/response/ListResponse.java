package com.springdemo.springdatajpahomework.model.dto.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse<T> {
    private List<T> items;
    private PaginationResponse pagination ;
}
