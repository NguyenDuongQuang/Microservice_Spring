package com.example.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "san_pham_chi_tiet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Product_Detail extends Base{
    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "trang_thai")
    private boolean trangThai;

    @Column(name = "so_luong_tam")
    private Integer soLuongTam;

    @JoinColumn(name = "san_pham_id",referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
}
