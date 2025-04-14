package com.example.productservice.service;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product>findByAllProduct();

    ResponseEntity<?>addProduct(ProductDto productDto);

    ResponseEntity<Product>updateProduct(ProductDto productDto);

    ResponseEntity<List<Product>>deleteProduct(Long id);

    ResponseEntity<?>searchProductAll(String search);

    ResponseEntity<?>getProductPrice(Float gia1, Float gia2);

    List<Object> productDetail(long id_Product);
}
