package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.entity.Product_Detail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Product_DetailService {
    ResponseEntity<Product_Detail> editProductSetaiD(Product_Detail productDetail);

    ResponseEntity<List<Product_Detail>> deleteProductDetails(Long id);

    List<Product_Detail> searchAllProductDetails(String search);

    ResponseEntity<Product_Detail> editSLProductDetail(Product_Detail productDetail);

}
