package com.example.authservice.config;

import com.example.authservice.service.StaffDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private StaffDetailsServiceImpl staffDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        SessionManagementConfigurer<HttpSecurity> httpSecuritySessionManagementConfigurer = http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/login",
                        "/payment/create",
                        "/payment/return",
                        "/khachHang/register",
                        "/sanPham/taoSanPham",
                        "/loaiSanPham/danhSach",
                        "/sanPham/danhSach",
                        "/customer/cart/addToCart",
                        "/customer/sanPham/danhSach",
                        "/customer/sanPham/loc/gia",
                        "/customer/sanPham/timKiemTheoTen/{search}",
                        "/customer/sanPham/loc/mau_sac",
                        "/customer/sanPham/loc/kich_co",
                        "/customer/sanPham/loc/gia",
                        "/customer/sanPham/loc/loai_san_pham",
                        "/customer/sanPham/loc/chat_lieu",
                        "/customer/sanPham/loc/nha_san_xuat",
                        "/customer/sanPham/getSanPham/id={id}",
                        "/customer/sanPham/getAnhSanPham/{id}",
                        "/customer/sanPham/api/getSize/{id}",
                        "/customer/sanPham/api/getColor/{id}",
                        "/customer/sanPham/api/getSoLuong",
                        "/api/banHang/online/checkOut",
                        "/api/banHang/online/getHoaDon/{id}",
                        "/api/banHang/online/getHoaDonChiTiet/{id}",
                        "/api/banHang/online/check-out",
                        "/api/banHang/online/add/khuyenMai",
                        "/api/banHang/online/datHang",
                        "/api/muaNgay/check-out",
                        "/api/muaNgay/getHoaDon/{id}",
                        "/api/muaNgay/getHoaDonChiTiet/{id}",
                        "/api/muaNgay/check-out",
                        "/api/muaNgay/add/khuyenMai",
                        "/api/muaNgay/datHang",
                        "/payment/MuaNgay/create",
                        "/payment/MuaNgay/return",
                        "/customer/sanPham/getAnhMacDinhSanPham/{id}",
                        "/customer/sanPham/getAnhByMauSac",
                        "/api/password/get-keys",
                        "/api/password/check-keys",
                        "/api/password/fogot-password",
                        "/customer/changePass",
                        "/hoaDon/khachHang/donHang/danhSach"
                ).permitAll()
                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(staffDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
