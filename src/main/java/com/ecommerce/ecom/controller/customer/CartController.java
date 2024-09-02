package com.ecommerce.ecom.controller.customer;

import com.ecommerce.ecom.dto.AddProductInCarDTO;
import com.ecommerce.ecom.services.customer.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart(AddProductInCarDTO inCarDTO) {
        return cartService.addProductToCart(inCarDTO);
    }
}
