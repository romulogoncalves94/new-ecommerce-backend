package com.ecommerce.ecom.controller.customer;

import com.ecommerce.ecom.dto.OrderedProductsResponseDTO;
import com.ecommerce.ecom.dto.ReviewDTO;
import com.ecommerce.ecom.services.customer.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderedProductsResponseDTO> getOrderedProductsDetailsByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok(reviewService.getOrderedProductsDetailsByOrderId(orderId));
    }

    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@ModelAttribute ReviewDTO reviewDTO) throws IOException {
        ReviewDTO reviewDTO1 = reviewService.giveReview(reviewDTO);

        if (Objects.isNull(reviewDTO1)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewDTO1);
    }

}
