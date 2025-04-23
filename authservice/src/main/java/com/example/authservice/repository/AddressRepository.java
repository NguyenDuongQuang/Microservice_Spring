package com.example.authservice.repository;

import com.example.authservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "select * from dia_chi where khach_hang_id = ?", nativeQuery = true)
    List<Address> findByCustomerID(long id);

    @Query(value = "select * from dia_chi where khach_hang_id = ? and id = ?", nativeQuery = true)
    Address findbyCustomerAndID(long khachHang_id, long id);

    @Query(value = "select * from dia_chi where khach_hang_id = ? and dia_chi_mac_dinh = true", nativeQuery = true)
    Address getDiaChi(long khach_hang_id);
}
