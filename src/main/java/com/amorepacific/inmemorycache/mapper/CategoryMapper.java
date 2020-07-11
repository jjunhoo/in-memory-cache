package com.amorepacific.inmemorycache.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * The interface Category mapper.
 */
@Repository
@Mapper
public interface CategoryMapper {

    /**
     * Update category name.
     *
     * @param categoryNo   the category no
     * @param categoryName the category name
     */
    void updateCategoryName(Long categoryNo, String categoryName);
}
