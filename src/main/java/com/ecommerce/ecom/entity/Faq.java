package com.ecommerce.ecom.entity;

import com.ecommerce.ecom.dto.FaqDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    public FaqDTO getFaqDTO() {
        FaqDTO faqDTO = new FaqDTO();
        faqDTO.setId(id);
        faqDTO.setQuestion(question);
        faqDTO.setAnswer(answer);
        faqDTO.setIdProduct(product.getId());
        return faqDTO;
    }
}
