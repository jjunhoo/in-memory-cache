package com.amorepacific.inmemorycache.service;

import com.amorepacific.inmemorycache.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Category service.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CacheService cacheService;

    // TODO : Cache Data Eviction Policy

    /**
     * Update category name.
     * 카테고리명 수정
     */
    @Transactional
    public void updateCategoryName(Long categoryNo, String categoryName) {
        categoryMapper.updateCategoryName(categoryNo, categoryName);
        // Cache 데이터 갱신
        cacheService.setAllCacheData();
    }
}
