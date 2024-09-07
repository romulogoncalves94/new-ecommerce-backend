package com.ecommerce.ecom.services.customer.review;

import com.ecommerce.ecom.dto.OrderedProductsResponseDTO;
import com.ecommerce.ecom.dto.ProductDTO;
import com.ecommerce.ecom.entity.CartItems;
import com.ecommerce.ecom.entity.Order;
import com.ecommerce.ecom.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final OrderRepository orderRepository;

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

}
