package com.amorepacific.inmemorycache.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
