package com.example.productservice.repository;

import com.example.productservice.entity.Product_Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface Product_DetailRepository extends JpaRepository<Product_Detail, Long> {
    @Query(value = "select spct from Product_Detail spct where spct.product.id=:idsp and spct.isDeleted=false")
    List<Product_Detail> findSpctByIdSp(@Param("idsp") Long idsp);

    @Query(value = "SELECT * FROM san_pham_chi_tiet WHERE is_deleted = false ORDER BY id DESC", nativeQuery = true)
    List<Product_Detail> findAll();

    @Query(value = "select * from san_pham_chi_tiet where id = ? and is_deleted = false", nativeQuery = true)
    Product_Detail findByID(Long id);

    @Query(value = "select * from san_pham_chi_tiet spct\n" +
            "        join san_pham sp on spct.san_pham_id = sp.id\n" +
            "        join loai_san_pham lsp on sp.loai_san_pham_id = lsp.id\n" +
            "        where sp.is_deleted = false and (ten_san_pham = ?1 or gia = ?1 or loai_san_pham = ?1);", nativeQuery = true)
    List<Product_Detail> findByAll(String input);

    @Query(value = "SELECT * FROM san_pham_chi_tiet WHERE is_deleted = false AND DATE(started_date) = ?", nativeQuery = true)
    List<Product_Detail> findByDate(LocalDate ngayTao);

    @Query(value = "select * from san_pham_chi_tiet where name = ?", nativeQuery = true)
    Optional<Product_Detail> findByName(String name);

    @Query(value = "select * from san_pham_chi_tiet where san_pham_id = ? and is_deleted = false", nativeQuery = true)
    List<Product_Detail> findByProductID(Long id);

    @Query(value = "select * from san_pham_chi_tiet where id = ? and is_deleted = false", nativeQuery = true)
    List<Product_Detail> findByProduct(Long id);

    @Query(value = "select * from san_pham_chi_tiet spct where spct.san_pham_id = ? and spct.is_deleted = false", nativeQuery = true)
    List<Product_Detail> getProductD(@Param("san_pham_id") long san_pham_id);

    @Query(value = "SELECT * FROM san_pham_chi_tiet WHERE is_deleted = false AND san_pham_id = ?", nativeQuery = true)
    List<Product_Detail> findProductDetails();

    @Query(value = "select tenAnh from hinh_anh where id_product = ? and anh_mac_dinh = true", nativeQuery = true)
    String getAnhMacDinh(long sanPham_id);

    @Query(value = "select so_luong from san_pham_chi_tiet where  san_pham_id = ?", nativeQuery = true)
    Integer getSoLuongHienCp(long san_pham_id);

    @Query(value = "select * from san_pham_chi_tiet where san_pham_id = ? and is_deleted = false", nativeQuery = true)
    Product_Detail getSanPhamChiTiet( long san_pham_id);

    @Modifying
    @Query(value = "delete from san_pham_chi_tiet where id = ?", nativeQuery = true)
    void deleteByIDSPT(long id);

}
