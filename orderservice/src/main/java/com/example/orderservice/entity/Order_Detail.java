package com.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "hoa_don_chi_tiet")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order_Detail extends Base{

    @Column(name = "don_gia")
    private int donGia;

    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "thanh_tien")
    private int thanhTien;

    @JoinColumn(name = "hoa_don_id",referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

//    @JoinColumn(name = "san_pham_chi_tiet_id",referencedColumnName = "id")
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Product_Detail productDetail;
    //Dùng OpenFegin

    @Column(name = "san_pham_chi_tiet_id")
    private int sanPhamChiTietId;
}
