package com.example.productservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AutoCloseable.class)
public abstract class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "update_by")
    private String updatedBy;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
