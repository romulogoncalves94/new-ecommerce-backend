package com.ecommerce.ecom.dto;

import lombok.Data;

@Data
public class AddProductInCartDTO {

    private Long userId;
    private Long productId;

}
