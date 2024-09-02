package com.ecommerce.ecom.services.customer.cart;

import com.ecommerce.ecom.dto.AddProductInCarDTO;
import com.ecommerce.ecom.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCarDTO inCarDTO);
    OrderDTO getCartByUserId(Long userId);

}
