package com.ecommerce.ecom.services.admin.adminproduct;

import com.ecommerce.ecom.dto.ProductDTO;
import com.ecommerce.ecom.entity.Category;
import com.ecommerce.ecom.entity.Product;
import com.ecommerce.ecom.repository.CategoryRepository;
import com.ecommerce.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final CategoryRepository categoryRepository;

    public ProductDTO addProduct(ProductDTO productDTO) throws IOException {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImg(productDTO.getImg().getBytes());

        Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow();
        product.setCategory(category);
        return productRepository.save(product).getDto();
    }

    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDTO> getAllProductByName(String name) {
        List<Product> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id) {
        Optional<Product> idProduct = productRepository.findById(id);

        if (idProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }

        return false;
    }

}
