package com.ecommerce.ecom.services.customer.cart;

import com.ecommerce.ecom.dto.AddProductInCarDTO;
import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCarDTO inCarDTO);

}
