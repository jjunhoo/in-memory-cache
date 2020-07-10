package com.amorepacific.inmemorycache.controller;

import com.amorepacific.inmemorycache.domain.CacheProduct;
import com.amorepacific.inmemorycache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Cache controller.
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    // TODO : 아래 데이터들이 Cache 되어야 함 > HashMap 구조 .. ?
    //              => eviction 정책 필요 ! - LRU, FIFO...
    /**
     * The Cache service.
     */
    @Autowired
    CacheService cacheService;

    /**
     * Gets product list by category.
     * 카테고리에 속한 상품 조회 > Cache
     *
     * @param categoryNo the category no
     * @return the product list by category
     * @throws Exception the exception
     */
    @GetMapping("/productListByCategory/{categoryNo}")
    public @ResponseBody List<CacheProduct> getProductListByCategory(@PathVariable Long categoryNo) throws Exception {
        System.out.println("init controller - getProductListByCategory");
        return cacheService.getProductListByCategory(categoryNo);
    }

    /**
     * Gets product.
     * 상품 조회 > Cache
     * @param productNo the product no
     * @return the product
     * @throws Exception the exception
     */
    @GetMapping("/product/{productNo}")
    public @ResponseBody CacheProduct getProduct(@PathVariable Long productNo) throws Exception {
        System.out.println("init controller - getProduct");
        return cacheService.getProduct(productNo);
    }
}
