package com.springdemo.springdatajpahomework.repository;

import com.springdemo.springdatajpahomework.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products,Long> {
}
