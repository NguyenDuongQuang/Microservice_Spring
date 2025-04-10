package com.example.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "san_pham")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Product extends Base {
    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "mota")
    private String mota;

    @Column(name = "gia")
    private Float gia;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @JoinColumn(name = "loai_san_pham_id",referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
