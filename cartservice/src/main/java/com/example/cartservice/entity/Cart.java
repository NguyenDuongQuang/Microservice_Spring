package com.example.cartservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "gio_hang")
public class Cart extends Base implements Serializable {

    //OpenFegin
    @Column(name = "khach_hang_id")
    private Long khachHangID;

    @Column(name = "trang_thai", columnDefinition = "int null")
    private int trangThai;
}
