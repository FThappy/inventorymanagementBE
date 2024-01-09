package com.example.mockbe.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryProductCount {
    private String categoryName;
    private long productCount;

    public CategoryProductCount(String categoryName, long productCount) {
        this.categoryName = categoryName;
        this.productCount = productCount;
    }
}
