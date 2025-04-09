package com.example.accountservice.repository;

import com.example.accountservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT * FROM khach_hang WHERE  is_deleted = false ORDER BY id DESC ",nativeQuery = true)
    List<Customer> findAllCustomer();

    @Query(value = "SELECT * FROM khach_hang WHERE id= ? AND  is_deleted = false",nativeQuery = true)
    Customer findByID(Long id);

    @Query(value = "SELECT * FROM khach_hang WHERE ho_ten= ? ",nativeQuery = true)
    Optional<Customer> findByName(String name);

    @Query(value = "SELECT * FROM khach_hang WHERE ho_ten= ?1 OR so_dien_thoai= ?1 OR dia_chi=?1 OR email=?1   ",nativeQuery = true)
    List<Customer> findByAll(String search);

    @Query(value = "select * from khach_hang where is_deleted = false and date(ngay_sinh) = ?", nativeQuery = true)
    List<Customer> findCustomerDate(LocalDate ngaySinh);

    @Query(value = "SELECT * FROM khach_hang WHERE email= ?",nativeQuery = true)
    Customer findByEmail(String email);

    @Query(value = "SELECT * FROM khach_hang WHERE so_dien_thoai= ? ",nativeQuery = true)
    Customer findBySDT(String soDienThoai);
}
