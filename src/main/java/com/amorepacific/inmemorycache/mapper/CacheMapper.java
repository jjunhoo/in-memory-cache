package com.amorepacific.inmemorycache.mapper;

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
     * 카테고리에 속한 상품 조회 > Cache
     *
     * @param categoryNo the category no
     * @return the product list by category
     */
    List<CacheProduct> selectProductListByCategory(Long categoryNo);

    /**
     * Select product cache product.
     * 상품 조회 > Cache
     * @param productNo the product no
     * @return the cache product
     */
    CacheProduct selectProduct(Long productNo);
}
