package com.ecommerce.ecom.services.customer;

import com.ecommerce.ecom.dto.ProductDTO;
import com.ecommerce.ecom.dto.ProductDetailDTO;

import java.util.List;

public interface CustomerProductService {

    List<ProductDTO> getAllProducts();
    List<ProductDTO> getAllProductByName(String name);
    ProductDetailDTO getProductDetailById(Long productId);

}
