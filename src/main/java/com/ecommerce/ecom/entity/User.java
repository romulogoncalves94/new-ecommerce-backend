package com.ecommerce.ecom.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private UserRole role;

    @Lob
    @Column(columnDefinition = "bytea")
    private byte[] img;

}
