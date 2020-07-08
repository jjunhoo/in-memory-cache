package com.amorepacific.inmemorycache.controller;

import com.amorepacific.inmemorycache.domain.Category;
import com.amorepacific.inmemorycache.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    // 카테고리 리스트 조회 > Controller
    @GetMapping("/categoryList")
    public @ResponseBody List<Category> getCategoryList() throws Exception {
        System.out.println("init controller - getCategoryList");
        return categoryService.getCategoryList();
    }

    // TODO : 테스트 편의상 GetMapping 사용, Return 값 고민
    // 상품명 수정 > Controller
    @GetMapping("/updateCategoryName/{categoryNo}/{categoryName}")
    public void updateCategoryName(@PathVariable Long categoryNo, @PathVariable String categoryName) throws Exception {
        System.out.println("init controller - updateCategoryName");
        categoryService.updateCategoryName(categoryNo, categoryName);
    }
}
