package com.ecommerce.ecom.services.customer.review;

import com.ecommerce.ecom.dto.OrderedProductsResponseDTO;

public interface ReviewService {

    OrderedProductsResponseDTO getOrderedProductsDetailsByOrderId(Long orderId);

}
