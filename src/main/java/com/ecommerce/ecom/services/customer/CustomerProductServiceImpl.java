package com.ecommerce.ecom.services.customer;

import com.ecommerce.ecom.dto.ProductDTO;
import com.ecommerce.ecom.dto.ProductDetailDTO;
import com.ecommerce.ecom.entity.*;
import com.ecommerce.ecom.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final FaqRepository faqRepository;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto).toList();
    }

    public List<ProductDTO> getAllProductByName(String name) {
        List<Product> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).toList();
    }

    public ProductDetailDTO getProductDetailById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            List<Faq> faqList = faqRepository.findAllByProductId(productId);
            List<Review> reviewList = reviewRepository.findAllByProductId(productId);

            ProductDetailDTO productDetailDTO = new ProductDetailDTO();
            productDetailDTO.setProductDTO(optionalProduct.get().getDto());
            productDetailDTO.setFaqDTOList(faqList.stream().map(Faq::getFaqDTO).toList());
            productDetailDTO.setReviewDTOList(reviewList.stream().map(Review::getDTO).toList());

            return productDetailDTO;
        }


        return null;
    }
}
