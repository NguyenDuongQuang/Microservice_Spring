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



    private Long id_SPCT;



    //Dùng để add to cart bên phía customer


    private Long product_id;

    private int soLuongDaChon;

    private int donGia;

    private int soLuong;

    private int tongTien;

    private Long id_bill;

    private Integer soLuongHienCo;

    private Long image_id;

    private String image_product;

    private String email_user;
}
