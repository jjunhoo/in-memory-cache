package com.amorepacific.inmemorycache.controller;

import com.amorepacific.inmemorycache.common.BusinessLogicException;
import com.amorepacific.inmemorycache.common.CommonError;
import com.amorepacific.inmemorycache.domain.CacheCategory;
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

    /**
     * The Cache service.
     */
    @Autowired
    CacheService cacheService;

    /**
     * Gets product list by category.
     * 특정 카테고리에 속한 상품 조회 > Cache
     *
     * @param categoryNo the category no
     * @return the product list by category
     */
    @GetMapping("/productListByCategory/{categoryNo}")
    public @ResponseBody List<CacheProduct> getProductListByCategory(@PathVariable String categoryNo) {
        return cacheService.getCacheProductListByCategory(categoryNo);
    }

    /**
     * Gets product.
     * 개별 상품 조회 > Cache
     *
     * @param productNo the product no
     * @return the product
     * @throws Exception the exception
     */
    @GetMapping("/product/{productNo}")
    public @ResponseBody CacheProduct getProduct(@PathVariable String productNo) throws Exception {
        return cacheService.getCacheProduct(productNo);
    }

    /**
     * Gets category list.
     * 카테고리 리스트 조회 > Cache
     *
     * @return the category list
     * @throws Exception the exception
     */
    @GetMapping("/categoryList")
    public @ResponseBody List<CacheCategory> getCacheCategoryList() throws Exception {
        return cacheService.getCacheCategoryList();
    }

    /**
     * Update category name.
     * 카테고리명 수정
     *
     * @param categoryNo   the category no
     * @param categoryName the category name
     */
    @GetMapping("/putCategoryName/{categoryNo}/{categoryName}")
    public void updateCategoryName(@PathVariable Long categoryNo, @PathVariable String categoryName) {
        cacheService.updateCategoryName(categoryNo, categoryName);
    }

    /**
     * Update product name.
     * 상품명 수정
     *
     * @param productNo   the product no
     * @param productName the product name
     */
    @GetMapping("/putProductName/{productNo}/{productName}")
    public void updateProductName(@PathVariable Long productNo, @PathVariable String productName) {
        cacheService.updateProductName(productNo, productName);
    }

    /**
     * Update product price.
     * 상품 가격 수정
     *
     * @param productNo    the product no
     * @param productPrice the product price
     */
    @GetMapping("/putProductPrice/{productNo}/{productPrice}")
    public void updateProductPrice(@PathVariable Long productNo, @PathVariable Long productPrice) {
        cacheService.updateProductPrice(productNo, productPrice);
    }

    /**
     * Insert product info.
     * 상품 정보 등록
     *
     * @param productNo    the product no
     * @param brandName    the brand name
     * @param productName  the product name
     * @param productPrice the product price
     * @param categoryNo   the category no
     */
    @GetMapping("/setProductInfo/{productNo}/{brandName}/{productName}/{productPrice}/{categoryNo}")
    public void insertProductInfo(@PathVariable Long productNo,
                                  @PathVariable String brandName,
                                  @PathVariable String productName,
                                  @PathVariable Long productPrice,
                                  @PathVariable Long categoryNo){
        cacheService.insertProductInfo(productNo, brandName, productName, productPrice, categoryNo);
    }

    /**
     * Error msg common error.
     * 공통 Error 처리 클래스
     *
     * @param e the e
     * @return the common error
     */
    @ExceptionHandler(BusinessLogicException.class)
    public @ResponseBody CommonError errorMsg(BusinessLogicException e) {
        CommonError commonError = new CommonError();
        commonError.setErrorCode("422");
        commonError.setErrorMsg("해당 데이터가 없습니다.");
        return commonError;
    }
}
