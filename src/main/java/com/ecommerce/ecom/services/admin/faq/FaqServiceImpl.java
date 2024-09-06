package com.ecommerce.ecom.services.admin.faq;

import com.ecommerce.ecom.dto.FaqDTO;
import com.ecommerce.ecom.entity.Faq;
import com.ecommerce.ecom.entity.Product;
import com.ecommerce.ecom.repository.FaqRepository;
import com.ecommerce.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

    private final FaqRepository faqRepository;
    private final ProductRepository productRepository;

    public FaqDTO postFaq(Long productId, FaqDTO faqDTO) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Faq faq = new Faq();
            faq.setQuestion(faqDTO.getQuestion());
            faq.setAnswer(faqDTO.getAnswer());
            faq.setProduct(optionalProduct.get());

            return faqRepository.save(faq).getFaqDTO();
        }

        return null;
    }

}
