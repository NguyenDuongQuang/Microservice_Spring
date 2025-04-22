package com.example.cartservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "gio_hang_chi_tiet")
public class CartDetail extends Base implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gio_hang_id",referencedColumnName = "id")
    private Cart cart;

//    @ManyToOne
//    @JoinColumn(name = "san_pham_chi_tiet_id")
//    private SanPhamChiTiet sanPhamChiTiet;

    //OpenFegin
    @Column(name = "san_pham_chi_tiet_id")
    private Long sanPhamChiTietId;


    @Column(name = "so_luong", columnDefinition = "int null")
    private int soLuong;

    @Column(name = "don_gia", columnDefinition = "int ")
    private int donGia;

    @Column(name = "thanh_tien", columnDefinition = "varchar(50) not null")
    private BigDecimal thanhTien;
}
