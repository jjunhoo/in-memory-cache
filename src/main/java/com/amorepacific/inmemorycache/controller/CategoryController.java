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
     * Gets category list.
     * 카테고리 리스트 조회
     *
     * @return the category list
     * @throws Exception the exception
     */
    @GetMapping("/categoryList")
    public @ResponseBody List<Category> getCategoryList() throws Exception {
        System.out.println("init controller - getCategoryList");
        return categoryService.getCategoryList();
    }

    /**
     * Update category name.
     * 카테고리명 수정
     * @throws Exception the exception
     */
    // TODO : 테스트 편의상 GetMapping 사용, Return 값 고민
    @GetMapping("/updateCategoryName/{categoryNo}/{categoryName}")
    public void updateCategoryName(@PathVariable Long categoryNo, @PathVariable String categoryName) throws Exception {
        System.out.println("init controller - updateCategoryName");
        categoryService.updateCategoryName(categoryNo, categoryName);
    }
}
