package com.springdemo.springdatajpahomework.repository;

import com.springdemo.springdatajpahomework.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers,Long> {
}
