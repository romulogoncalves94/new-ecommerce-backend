package com.ecommerce.ecom.controller.customer;

import com.ecommerce.ecom.dto.*;
import com.ecommerce.ecom.exceptions.ValidationException;
import com.ecommerce.ecom.services.customer.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDTO inCarDTO) {
        return cartService.addProductToCart(inCarDTO);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable("userId") Long userId) {
        OrderDTO orderDTO = cartService.getCartByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
    }

    @GetMapping("/coupon/{userId}/{code}")
    public ResponseEntity<?> applyCoupon(@PathVariable("userId") Long userId, @PathVariable("code") String code) {
        try {
            OrderDTO orderDTO = cartService.applyCoupon(userId, code);
            return ResponseEntity.ok(orderDTO);
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/addition")
    public ResponseEntity<OrderDTO> increaseProductQuantity(@RequestBody AddProductInCartDTO product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseProductQuantity(product));
    }

    @PostMapping("/deduction")
    public ResponseEntity<OrderDTO> decreaseProductQuantity(@RequestBody AddProductInCartDTO product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseProductQuantity(product));
    }

    @PostMapping("/place-order")
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody PlaceOrderDTO placeOrderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placeOrderDTO));
    }

    @GetMapping("/myOrders/{userId}")
    public ResponseEntity<List<OrderDTO>> getMyPlacedOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getMyPlacedOrders(userId));
    }

}
