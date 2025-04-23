package com.example.authservice.dto;

import com.example.authservice.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffResponse {
    private Long id;
    private String email;
    private String hoTen;
    private String matKhau;
    private Set<UserRole> userRoles;
    private String soDienThoai;
    private int trangThai;

}
