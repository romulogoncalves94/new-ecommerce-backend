package com.ecommerce.ecom.controller.customer;

import com.ecommerce.ecom.dto.WishlistDTO;
import com.ecommerce.ecom.services.customer.wishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/wishlist")
    public ResponseEntity<?> addProductToWishlist(@RequestBody WishlistDTO wishlistDTO) {
        WishlistDTO wishlist = wishlistService.addProductToWishlist(wishlistDTO);
        if (isNull(wishlist)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");

        return ResponseEntity.status(HttpStatus.CREATED).body(wishlist);
    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<List<WishlistDTO>> getWishListByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(wishlistService.getWishListByUserId(userId));
    }
}
