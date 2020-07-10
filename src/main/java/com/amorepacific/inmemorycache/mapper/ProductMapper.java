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
