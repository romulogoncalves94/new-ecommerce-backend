package com.ecommerce.ecom.entity;

import com.ecommerce.ecom.dto.ReviewDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long rating;

    @Lob
    private String description;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    public ReviewDTO getDTO() {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(id);
        reviewDTO.setRating(rating);
        reviewDTO.setDescription(description);
        reviewDTO.setReturnedImg(img);
        reviewDTO.setUserId(user.getId());
        reviewDTO.setProductId(product.getId());
        reviewDTO.setUsername(user.getName());

        return reviewDTO;
    }

}
