package com.ecommerce.ecom.services.admin.adminproduct;

import com.ecommerce.ecom.dto.ProductDTO;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {

    ProductDTO addProduct(ProductDTO productDTO) throws IOException;
    List<ProductDTO> getAllProduct();
    List<ProductDTO> getAllProductByName(String name);
    boolean deleteProduct(Long id);
    ProductDTO getProductById(Long productId);
    ProductDTO updateProduct(Long productId, ProductDTO productDTO) throws IOException;

}
