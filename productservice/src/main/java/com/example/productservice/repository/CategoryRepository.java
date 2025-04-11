package com.example.productservice.repository;

import com.example.productservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT * FROM loai_san_pham WHERE is_deleted = false ORDER BY id DESC",nativeQuery = true)
    List<Category>findByCategoryAll();

    @Query(value = "SELECT * FROM loai_san_pham WHERE id = ? AND is_deleted = false",nativeQuery = true)
    Category findByID(Long id);

    @Query(value = "SELECT * FROM loai_san_pham WHERE loai_san_pham = ?",nativeQuery = true)
    Category findByName(String loaiSanPham);
}
