package com.ecommerce.ecom.controller;

import com.ecommerce.ecom.dto.OrderDTO;
import com.ecommerce.ecom.services.customer.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
public class TrackingController {

    private final CartService cartService;

    @GetMapping("/order/{trackingId}")
    public ResponseEntity<OrderDTO> searchOrderByTrackingID(@PathVariable UUID trackingId) {
        OrderDTO orderDTO = cartService.searchOrderByTrackingID(trackingId);
        if (isNull(orderDTO)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderDTO);
    }

}
