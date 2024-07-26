package com.ecommerce.ecom.services.admin.category;

import com.ecommerce.ecom.dto.CategoryDTO;
import com.ecommerce.ecom.entity.Category;

public interface CategoryService {

    Category createCategory(CategoryDTO categoryDTO);

}
