package com.example.accountservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_role")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userRoleId;

    @JoinColumn(name = "khach_hang_id",referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private  Staff staff;

    @JoinColumn(name = "staff_id",referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private  Customer customer;

    @JoinColumn(name = "role_role_id",referencedColumnName = "role_id")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private  Role role;
}
