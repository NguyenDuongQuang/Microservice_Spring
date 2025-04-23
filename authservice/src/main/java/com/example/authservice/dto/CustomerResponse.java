package com.example.authservice.dto;

import com.example.authservice.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Long id;
    private String email;
    private String hoTen;
    private String matKhau;
    private Set<UserRole> userRoles;
    private String soDienThoai;

}
