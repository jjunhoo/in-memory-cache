package com.amorepacific.inmemorycache.service;

import com.amorepacific.inmemorycache.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Product service.
 */
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CacheService cacheService;

    /**
     * Update product name.
     * 상품명 수정
     * @param productNo   the product no
     * @param productName the product name
     */
    @Transactional
    public void updateProductName(Long productNo, String productName) {
        productMapper.updateProductName(productNo, productName);
        // Cache 데이터 갱신
        cacheService.setAllCacheData();
    }

    /**
     * Update product price.
     * 상품 가격 수정
     * @param productNo    the product no
     * @param productPrice the product price
     */
    @Transactional
    public void updateProductPrice(Long productNo, Long productPrice) {
        productMapper.updateProductPrice(productNo, productPrice);
        // Cache 데이터 갱신
        cacheService.setAllCacheData();
    }
}
