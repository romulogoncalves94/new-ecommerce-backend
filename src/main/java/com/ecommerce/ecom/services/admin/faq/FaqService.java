package com.ecommerce.ecom.services.admin.faq;

import com.ecommerce.ecom.dto.FaqDTO;

public interface FaqService {

    FaqDTO postFaq(Long productId, FaqDTO faqDTO);

}
