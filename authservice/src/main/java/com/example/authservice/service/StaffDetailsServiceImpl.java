package com.example.authservice.service;

import com.example.authservice.client.CustomerClientDto;
import com.example.authservice.client.StaffClientDto;
import com.example.authservice.dto.CustomerResponse;
import com.example.authservice.dto.StaffResponse;
import com.example.authservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StaffDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerClientDto customerClientDto;

    @Autowired
    private StaffClientDto staffClientDto;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StaffResponse nhanVien = this.staffClientDto.findByEmail(username);
        if (nhanVien != null) {
            UserDto dto = new UserDto();
            dto.setName(nhanVien.getHoTen());
            dto.setEmail(nhanVien.getEmail());
            dto.setMatKhau(nhanVien.getMatKhau());
            dto.setRole(nhanVien.getUserRoles());
            dto.setSoDienThoai(nhanVien.getSoDienThoai());
            return dto;
        }

        CustomerResponse khachHang = this.customerClientDto.findByEmail(username);
        if (khachHang != null) {
            UserDto dto = new UserDto();
            dto.setName(khachHang.getHoTen());
            dto.setEmail(khachHang.getEmail());
            dto.setMatKhau(khachHang.getMatKhau());
            dto.setRole(khachHang.getUserRoles());
            dto.setSoDienThoai(khachHang.getSoDienThoai());
            return dto;
        }

        throw new UsernameNotFoundException("Thông tin đăng nhập không hợp lệ !!");

    }
}
