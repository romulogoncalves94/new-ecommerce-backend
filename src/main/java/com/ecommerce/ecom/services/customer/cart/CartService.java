package com.ecommerce.ecom.services.customer.cart;

import com.ecommerce.ecom.dto.*;
import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCartDTO inCarDTO);
    OrderDTO getCartByUserId(Long userId);
    OrderDTO applyCoupon(Long userId, String code);
    OrderDTO increaseProductQuantity(AddProductInCartDTO product);
    OrderDTO decreaseProductQuantity(AddProductInCartDTO product);
    OrderDTO placeOrder(PlaceOrderDTO placeOrderDTO);

}
