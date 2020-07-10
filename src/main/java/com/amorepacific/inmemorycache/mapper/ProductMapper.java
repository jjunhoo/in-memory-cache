package com.amorepacific.inmemorycache.mapper;

import com.amorepacific.inmemorycache.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Product mapper.
 */
@Repository
@Mapper
public interface ProductMapper {
    /**
     * Select product list list.
     * 상품 리스트 조회
     *
     * @return the list
     */
    List<Product> selectProductList();

    /**
     * Select product product.
     * 상품 조회
     *
     * @param productNo the product no
     * @return the product
     */
    Product selectProduct(@Param("productNo") Long productNo);

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
     * @param productNo    the product no
     * @param productPrice the product price
     */
    void updateProductPrice(Long productNo, Long productPrice);
}
