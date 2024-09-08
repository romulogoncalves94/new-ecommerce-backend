package com.ecommerce.ecom.entity;

import com.ecommerce.ecom.dto.WishlistDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    public WishlistDTO getWishDTO() {
        WishlistDTO wishlistDTO = new WishlistDTO();
        wishlistDTO.setUserId(user.getId());
        wishlistDTO.setProductId(product.getId());
        wishlistDTO.setId(id);
        wishlistDTO.setProductName(product.getName());
        wishlistDTO.setProductDescription(product.getDescription());
        wishlistDTO.setReturnedImg(product.getImg());
        wishlistDTO.setPrice(product.getPrice());
        return wishlistDTO;
    }

}
