package com.example.productservice.repository;

import com.example.productservice.entity.Product_Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product_ImageRepository extends JpaRepository<Product_Image, Long> {
}
