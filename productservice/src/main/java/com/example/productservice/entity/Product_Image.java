package com.example.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "hinh_anh")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Product_Image extends Base {
    @Column(name = "name")
    private String tenAnh;

    @Column(name = "anh_mac_dinh")
    private boolean anhMacDinh;

    @JoinColumn(name = "id_product",referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
}
