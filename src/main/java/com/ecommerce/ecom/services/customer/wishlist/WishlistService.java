package com.ecommerce.ecom.services.customer.wishlist;

import com.ecommerce.ecom.dto.WishlistDTO;

import java.util.List;

public interface WishlistService {

    WishlistDTO addProductToWishlist(WishlistDTO wishlistDTO);
    List<WishlistDTO> getWishListByUserId(Long userId);

}
