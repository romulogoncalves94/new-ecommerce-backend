package com.ecommerce.ecom.dto;

import lombok.Data;

@Data
public class PlaceOrderDTO {

    private Long userId;
    private String address;
    private String orderDescription;

}
