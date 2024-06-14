package com.ecommerce.ecom.entity;

import com.ecommerce.ecom.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String password;
    private UserRole role;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

}
