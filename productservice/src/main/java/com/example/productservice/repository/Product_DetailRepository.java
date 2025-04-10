package com.example.productservice.repository;

import com.example.productservice.entity.Product_Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product_DetailRepository extends JpaRepository<Product_Detail, Long> {
}
