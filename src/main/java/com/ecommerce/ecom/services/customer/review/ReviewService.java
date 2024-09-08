package com.ecommerce.ecom.services.customer.review;

import com.ecommerce.ecom.dto.OrderedProductsResponseDTO;
import com.ecommerce.ecom.dto.ReviewDTO;

import java.io.IOException;

public interface ReviewService {

    OrderedProductsResponseDTO getOrderedProductsDetailsByOrderId(Long orderId);
    ReviewDTO giveReview(ReviewDTO reviewDTO) throws IOException;

}
