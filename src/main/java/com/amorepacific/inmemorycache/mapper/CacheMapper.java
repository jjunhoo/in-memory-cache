package com.amorepacific.inmemorycache.mapper;

import com.amorepacific.inmemorycache.domain.CacheCategory;
import com.amorepacific.inmemorycache.domain.CacheProduct;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Cache mapper.
 */
@Repository
@Mapper
public interface CacheMapper {

    /**
     * Gets product list by category.
     * 특정 카테고리에 속한 상품 조회 > Cache
     *
     * @param categoryNo the category no
     * @return the product list by category
     */
    List<CacheProduct> selectProductListByCategory(String categoryNo);

    /**
     * Select product cache product.
     * 개별 상품 조회 > Cache
     *
     * @param productNo the product no
     * @return the cache product
     */
    CacheProduct selectProduct(Long productNo);

    /**
     * Select product list list.
     * 상품 리스트 조회 > Cache
     *
     * @return the list
     */
    List<CacheProduct> selectProductList();

    /**
     * Select categody list cache category.
     * 카테고리 리스트 조회 > Cache
     *
     * @return the cache category
     */
    List<CacheCategory> selectCategoryList();

    /**
     * Update category name.
     *
     * @param categoryNo   the category no
     * @param categoryName the category name
     */
    void updateCategoryName(Long categoryNo, String categoryName);

    /**
     * Update product name.
     * 상품명 수정
     *
     * @param productNo   the product no
     * @param productName the product name
     */
    void updateProductName(Long productNo, String productName);

    /**
     * Update product price.
     * 상품 가격 수정
     *
     * @param productNo    the product no
     * @param productPrice the product price
     */
    void updateProductPrice(Long productNo, Long productPrice);

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
    void insertProductInfo(Long productNo, String brandName, String productName, Long productPrice, Long categoryNo);
}
