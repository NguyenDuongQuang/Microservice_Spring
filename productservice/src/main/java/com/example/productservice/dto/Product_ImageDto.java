package com.example.productservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class Product_ImageDto {
    private Long id_image;

    private Long id_spct;

    private Long id_product;

    private List<String> tenAnh;
}
