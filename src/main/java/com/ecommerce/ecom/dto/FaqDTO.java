package com.ecommerce.ecom.dto;

import lombok.Data;

@Data
public class FaqDTO {

    private Long id;
    private String question;
    private String answer;
    private Long idProduct;

}
