package com.example.productservice.repository;

import com.example.productservice.entity.Product_Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Product_ImageRepository extends JpaRepository<Product_Image, Long> {
    @Query(value = "SELECT * FROM hinh_anh WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<Product_Image> findAllImage();

    @Query(value = "select * from hinh_anh where id = ? and is_deleted = false", nativeQuery = true)
    Product_Image findByID(Long id);

    @Query(value = "select * from hinh_anh where id_product = ? and is_deleted = false", nativeQuery = true)
    List<Product_Image> findByIDProduct(long id_sanPham);

    @Query(value = "select name from hinh_anh where id_product = ? and anh_mac_dinh = true limit 1", nativeQuery = true)
    String getTenAnhSanPham_HienThiDanhSach(long sanPham_id);
}
