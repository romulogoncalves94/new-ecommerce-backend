package com.ecommerce.ecom.entity;

import com.ecommerce.ecom.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long price;

    @Lob
    private String description;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    public ProductDTO getDto() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);
        productDTO.setName(name);
        productDTO.setPrice(price);
        productDTO.setDescription(description);
        productDTO.setByteImg(img);
        productDTO.setCategoryId(category.getId());
        productDTO.setCategoryName(category.getName());
        return productDTO;
    }

}
