package com.amorepacific.inmemorycache.service;

import com.amorepacific.inmemorycache.domain.CacheProduct;
import com.amorepacific.inmemorycache.mapper.CacheMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Cache service.
 */
@Service
public class CacheService {

    @Autowired
    private CacheMapper cacheMapper;

    /**
     * Gets product list by category.
     * 카테고리에 속한 상품 조회 > Cache
     *
     * @param categoryNo the category no
     * @return the product list by category
     */
    public List<CacheProduct> getProductListByCategory(Long categoryNo) {
        return cacheMapper.selectProductListByCategory(categoryNo);
    }


    /**
     * Gets product.
     * 상품 조회 > Cache
     * @param productNo the product no
     * @return the product
     */
    public CacheProduct getProduct(Long productNo) {
        return cacheMapper.selectProduct(productNo);
    }
}
