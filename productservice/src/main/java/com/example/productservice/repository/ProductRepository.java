package com.example.productservice.repository;

import com.example.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM san_pham WHERE is_deleted = false and trang_thai = 0 ORDER BY id DESC", nativeQuery = true)
    List<Product> findAllProduct();

    @Query(value = "select * from san_pham where id = ? and is_deleted = false", nativeQuery = true)
    Product findByID(Long id);


    @Query(value = "select * from san_pham where is_deleted = false and (ten_san_pham LIKE %?1% or gia LIKE %?1%) and trang_thai = 0;", nativeQuery = true)
    List<Product> findByAll(String input);

    @Query(value = "SELECT * FROM san_pham WHERE is_deleted = false AND ten_san_pham LIKE %?1% and trang_thai = 0", nativeQuery = true)
    List<Product> findByName(String name);

    @Query(value = "SELECT * FROM san_pham WHERE is_deleted = false AND ten_san_pham = ? and trang_thai = 0;", nativeQuery = true)
    Product checkLap(String tenSP);

    @Query(value = "select * from san_pham where loai_san_pham_id = ? and is_deleted = false and trang_thai = 0", nativeQuery = true)
    List<Product> findByLoaiSanPham(long loaiSanPham_id);

    @Query(value = "SELECT * FROM san_pham WHERE gia BETWEEN :gia1 AND :gia2 AND is_deleted = false and trang_thai = 0", nativeQuery = true)
    List<Product> findByGia(@Param("gia1") Float gia1, @Param("gia2") Float gia2);


    Float gia(Float gia);
}
