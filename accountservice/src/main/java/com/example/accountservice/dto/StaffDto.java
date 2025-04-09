package com.example.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffDto {
    private Long id;

    private String diaChi;

    private String email;

    private String hoTen;

    private String matKhau;

    private Date ngaySinh;

    private String soDienThoai;

    private int status;

    private Date createdDate;

    private String createdby;

    private Date updatedDate;

    private String updatedby;

    private boolean isDeleted;

    private boolean gioiTinh;
}
