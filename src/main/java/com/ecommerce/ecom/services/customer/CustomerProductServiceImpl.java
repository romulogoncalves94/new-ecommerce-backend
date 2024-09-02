package com.ecommerce.ecom.services.customer;

import com.ecommerce.ecom.dto.ProductDTO;
import com.ecommerce.ecom.entity.Product;
import com.ecommerce.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {

    @Autowired
    ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto).toList();
    }

    public List<ProductDTO> getAllProductByName(String name) {
        List<Product> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).toList();
    }
}
