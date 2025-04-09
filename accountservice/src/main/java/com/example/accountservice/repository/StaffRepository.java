package com.example.accountservice.repository;

import com.example.accountservice.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    @Query(value = "SELECT * FROM nhan_vien WHERE is_deleted =false ORDER BY id DESC ",nativeQuery = true)
    List<Staff> findAllStaff();

    @Query(value = "SELECT * FROM nhan_vien WHERE id = ? and is_deleted = false",nativeQuery = true)
    Staff findByID(Long id);

    @Query(value = "SELECT * FROM nhan_vien WHERE ho_ten = ?",nativeQuery = true)
    Optional<Staff> findByName(String name);

    @Query(value = "SELECT * FROM nhan_vien WHERE ho_ten = ?1 OR dia_chi= ?1 OR so_dien_thoai= ?1 OR email= ?1",nativeQuery = true)
    List<Staff> findByAll(String search);

    @Query(value = "select * from nhan_vien where is_deleted = false and date(ngay_sinh) = ?", nativeQuery = true)
    List<Staff> findStaffDate(LocalDate ngaySinh);

    @Query(value = "SELECT * FROM nhan_vien WHERE email = ?",nativeQuery = true)
    Staff findByEmail(String email);

    @Query(value = "SELECT * FROM nhan_vien WHERE so_dien_thoai = ?",nativeQuery = true)
    Staff findBySDT(String soDienThoai);

}
