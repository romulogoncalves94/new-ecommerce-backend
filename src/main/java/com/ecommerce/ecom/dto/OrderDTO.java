package com.ecommerce.ecom.dto;

import com.ecommerce.ecom.enums.OrderStatus;
import lombok.Data;

import java.util.*;

@Data
public class OrderDTO {

    private Long id;
    private String orderDescription;
    private Date date;
    private Long amount;
    private String address;
    private String payment;
    private OrderStatus orderStatus;
    private Long totalAmount;
    private Long discount;
    private UUID trackingId;
    private String username;
    private List<CartItemsDTO> cartItems;

}
