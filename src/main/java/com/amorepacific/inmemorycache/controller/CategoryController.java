package com.amorepacific.inmemorycache.controller;

import com.amorepacific.inmemorycache.domain.Category;
import com.amorepacific.inmemorycache.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Category controller.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    /**
     * The Category service.
     */
    @Autowired
    CategoryService categoryService;

    /**
     * Update category name.
     * 카테고리명 수정
     * @throws Exception the exception
     */
    @GetMapping("/putCategoryName/{categoryNo}/{categoryName}")
    public void updateCategoryName(@PathVariable Long categoryNo, @PathVariable String categoryName) throws Exception {
        categoryService.updateCategoryName(categoryNo, categoryName);
    }
}
