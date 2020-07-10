package com.amorepacific.inmemorycache.service;

import com.amorepacific.inmemorycache.domain.Category;
import com.amorepacific.inmemorycache.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Category service.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * Gets category list.
     * 카테고리 리스트 조회
     *
     * @return the category list
     */
    public List<Category> getCategoryList() {
        System.out.println("init service - getCategoryList");
        return categoryMapper.selectCategoryList();
    }

    /**
     * Update category name.
     * 카테고리명 수정
     */
    public void updateCategoryName(Long categoryNo, String categoryName) {
        System.out.println("init service - updateCategoryName");
        categoryMapper.updateCategoryName(categoryNo, categoryName);
    }
}
