package com.ecommerce.ecom.controller.customer;

import com.ecommerce.ecom.dto.OrderedProductsResponseDTO;
import com.ecommerce.ecom.services.customer.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderedProductsResponseDTO> getOrderedProductsDetailsByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(reviewService.getOrderedProductsDetailsByOrderId(orderId));
    }

}
