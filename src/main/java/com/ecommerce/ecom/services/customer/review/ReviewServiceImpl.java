package com.ecommerce.ecom.services.customer.review;

import com.ecommerce.ecom.dto.*;
import com.ecommerce.ecom.entity.*;
import com.ecommerce.ecom.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    public OrderedProductsResponseDTO getOrderedProductsDetailsByOrderId(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        OrderedProductsResponseDTO orderedProductsResponseDTO = new OrderedProductsResponseDTO();

        if (optionalOrder.isPresent()) {
            orderedProductsResponseDTO.setOrderAmount(optionalOrder.get().getAmount());

            List<ProductDTO> productDTOList = new ArrayList<>();

            for (CartItems cartItems : optionalOrder.get().getCartItems()) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setId(cartItems.getProduct().getId());
                productDTO.setName(cartItems.getProduct().getName());
                productDTO.setPrice(cartItems.getPrice());
                productDTO.setQuantity(cartItems.getQuantity());
                productDTO.setByteImg(cartItems.getProduct().getImg());

                productDTOList.add(productDTO);
            }

            orderedProductsResponseDTO.setProductDTOList(productDTOList);
        }

        return orderedProductsResponseDTO;
    }

    public ReviewDTO giveReview(ReviewDTO reviewDTO) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(reviewDTO.getProductId());
        Optional<User> optionalUser = userRepository.findById(reviewDTO.getUserId());

        if (optionalProduct.isPresent() && optionalUser.isPresent()) {
            Review review = new Review();
            review.setRating(reviewDTO.getRating());
            review.setDescription(reviewDTO.getDescription());
            review.setProduct(optionalProduct.get());
            review.setUser(optionalUser.get());
            review.setImg(reviewDTO.getImg().getBytes());

            return reviewRepository.save(review).getDTO();
        }
        return null;
    }

}
