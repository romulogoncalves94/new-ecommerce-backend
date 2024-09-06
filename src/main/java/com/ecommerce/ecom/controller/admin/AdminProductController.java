package com.ecommerce.ecom.controller.admin;

import com.ecommerce.ecom.dto.FaqDTO;
import com.ecommerce.ecom.dto.ProductDTO;
import com.ecommerce.ecom.services.admin.adminproduct.AdminProductService;
import com.ecommerce.ecom.services.admin.faq.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;
    private final FaqService faqService;


    @PostMapping("/product")
    public ResponseEntity<ProductDTO> addProduct(@ModelAttribute ProductDTO productDTO) throws IOException {
        ProductDTO productDTO1 = adminProductService.addProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO1);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProduct() {
        List<ProductDTO> productDTOList = adminProductService.getAllProduct();
        return ResponseEntity.ok(productDTOList);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDTO>> getAllProductByName(@PathVariable String name) {
        List<ProductDTO> productDTOList = adminProductService.getAllProductByName(name);
        return ResponseEntity.ok(productDTOList);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        boolean deleted = adminProductService.deleteProduct(productId);

        if (deleted) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/faq/{productId}")
    public ResponseEntity<FaqDTO> postFaq(@PathVariable Long productId, @RequestBody FaqDTO faqDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFaq(productId, faqDTO));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        ProductDTO productDTO = adminProductService.getProductById(productId);

        if (Objects.nonNull(productDTO))
            return ResponseEntity.ok(productDTO);

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @ModelAttribute ProductDTO productDTO) throws IOException {
        ProductDTO updateProduct = adminProductService.updateProduct(productId, productDTO);

        if (Objects.nonNull(updateProduct))
            return ResponseEntity.ok(updateProduct);

        return ResponseEntity.notFound().build();
    }

}
