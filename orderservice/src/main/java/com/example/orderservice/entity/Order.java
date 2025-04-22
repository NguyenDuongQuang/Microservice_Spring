package com.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "hoa_don")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order extends Base{

    @Column(name = "ma_hoa_don", columnDefinition = "nvarchar(256) null")
    private String maHoaDon;

    @Column(name = "ghi_chu", columnDefinition = "nvarchar(50) null")
    private String ghiChu;

    @Column(name = "nguoi_nhan", columnDefinition = "nvarchar(256) null")
    private String nguoiNhan;

    @Column(name = "email_nguoi_nhan", columnDefinition = "nvarchar(256) null")
    private String emailNguoiNhan;

    @Column(name = "sdtnguoi_nhan", columnDefinition = "nvarchar(50) null")
    private String SDTNguoiNhan;

    @Column(name = "dia_chi_giao_hang", columnDefinition = "nvarchar(256) null")
    private String diaChiGiaoHang;

    @Column(name = "tien_ship", columnDefinition = "int null")
    private int tienShip;

    @Column(name = "tien_giam", columnDefinition = "int null")
    private int tienGiam;

    @Column(name = "tong_tien_don_hang", columnDefinition = "int null")
    private int tongTienDonHang;

    @Column(name = "tong_tien_hoa_don", columnDefinition = "int null")
    private int tongTienHoaDon;

    @Column(name = "loai_hoa_don", columnDefinition = "int null")
    private int loaiHoaDon;

    @Column(name = "trang_thai_id")
    private int trangThaiId;

    @Column(name = "khach_hang_id")
    private int khachHangId;


    @Column(name = "nhan_vien_id")
    private int nhanVienId;

//    @ManyToOne
//    @JoinColumn(name = "trang_thai_id", referencedColumnName = "id")
//    private TrangThai trangThai;
//
//    @ManyToOne
//    @JoinColumn(name = "khach_hang_id", referencedColumnName = "id")
//    private KhachHang khachHang;
//
//    @ManyToOne
//    @JoinColumn(name = "nhan_vien_id", referencedColumnName = "id")
//    private NhanVien nhanVien;

    //Dùng OpenFegin
}
