package com.springdemo.springdatajpahomework.model;

import com.springdemo.springdatajpahomework.model.dto.response.ProductResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    private String name;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    private String description;

    public ProductResponse toResponse(){
        return ProductResponse.builder()
                .productId(this.productId)
                .description(this.description)
                .name(this.name)
                .unitPrice(this.unitPrice)
                .build();
    }
}
