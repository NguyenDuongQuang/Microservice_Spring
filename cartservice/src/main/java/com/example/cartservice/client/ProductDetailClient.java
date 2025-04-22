package com.example.cartservice.client;

import com.example.cartservice.dto.ProductDetailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productservice")
public interface ProductDetailClient {

    @GetMapping("/products/{id}")
    ProductDetailDto getProductById(@PathVariable("id") Long id);
}
