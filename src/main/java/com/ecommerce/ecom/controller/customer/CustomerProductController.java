package com.ecommerce.ecom.controller.customer;

import com.ecommerce.ecom.dto.ProductDTO;
import com.ecommerce.ecom.dto.ProductDetailDTO;
import com.ecommerce.ecom.services.customer.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {

    private final CustomerProductService customerProductService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
        List<ProductDTO> productDTOList = customerProductService.getAllProducts();
        return ResponseEntity.ok(productDTOList);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDTO>> getAllProductByName(@PathVariable String name) {
        List<ProductDTO> productDTOList = customerProductService.getAllProductByName(name);
        return ResponseEntity.ok(productDTOList);
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<ProductDetailDTO> getProductDetailById(@PathVariable Long productId) {
        ProductDetailDTO productDetailDTO = customerProductService.getProductDetailById(productId);
        if (isNull(productDetailDTO)) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(productDetailDTO);
    }
}
