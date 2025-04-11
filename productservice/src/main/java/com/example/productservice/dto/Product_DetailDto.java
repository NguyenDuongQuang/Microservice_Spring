package com.example.productservice.dto;

import com.example.productservice.entity.Product;
import lombok.Data;

@Data
public class Product_DetailDto {
    private Long id;

    private Integer soLuong;

    private boolean status;

    private Product product;

    private Long productId;
}
