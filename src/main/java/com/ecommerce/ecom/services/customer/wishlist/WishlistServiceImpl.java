package com.ecommerce.ecom.services.customer.wishlist;

import com.ecommerce.ecom.dto.WishlistDTO;
import com.ecommerce.ecom.entity.*;
import com.ecommerce.ecom.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final WishlistRepository wishlistRepository;

    public WishlistDTO addProductToWishlist(WishlistDTO wishlistDTO) {
        Optional<Product> optionalProduct = productRepository.findById(wishlistDTO.getProductId());
        Optional<User> optionalUser = userRepository.findById(wishlistDTO.getUserId());

        if (optionalProduct.isPresent() && optionalUser.isPresent()) {
            Wishlist wishlist = new Wishlist();
            wishlist.setProduct(optionalProduct.get());
            wishlist.setUser(optionalUser.get());

            return wishlistRepository.save(wishlist).getWishDTO();
        }

        return null;
    }

    public List<WishlistDTO> getWishListByUserId(Long userId) {
        return wishlistRepository.findAllByUserId(userId).stream()
                .map(Wishlist::getWishDTO)
                .toList();
    }

}
