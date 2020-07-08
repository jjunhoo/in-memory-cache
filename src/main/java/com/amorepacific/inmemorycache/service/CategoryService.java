package com.amorepacific.inmemorycache.service;

import com.amorepacific.inmemorycache.domain.Category;
import com.amorepacific.inmemorycache.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    // 카테고리 리스트 조회 > Service
    public List<Category> getCategoryList() {
        System.out.println("init service - getCategoryList");
        return categoryRepository.findAll();
    }

    // 상품명 수정 > Service
    @Transactional
    public void updateCategoryName(Long categoryNo, String categoryName) {
        System.out.println("init service - updateCategoryName");
        categoryRepository.updateCategoryName(categoryNo, categoryName);

        // TODO : Transaction 성공 시 Cache 갱신
    }

}
