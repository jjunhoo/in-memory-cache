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
     * @throws Exception the exception
     */
    @GetMapping("/productListByCategory/{categoryNo}")
    public @ResponseBody List<CacheProduct> getProductListByCategory(@PathVariable String categoryNo) throws Exception {
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
