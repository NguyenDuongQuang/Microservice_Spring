package com.example.productservice.dto;

import com.example.productservice.entity.Category;
import lombok.Data;

import java.util.Date;

@Data
public class ProductDto {
    private Long id;

    private String tenSanPham;

    private Float gia;

    private Integer trangThai;

    private Date ngayTao;

    private String nguoiTao;

    private Category category;

    private Long category_id;

    private String product_image;

    private int soLuong;
}
