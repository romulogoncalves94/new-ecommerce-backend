package com.ecommerce.ecom.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderedProductsResponseDTO {

    private List<ProductDTO> productDTOList;
    private Long orderAmount;

}
