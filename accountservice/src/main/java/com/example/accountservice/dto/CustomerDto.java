package com.example.accountservice.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;

    private String diaChi;

    private String email;

    private String hoTen;

    private String matKhau;

    private Date ngaySinh;

    private String soDienThoai;
}
