package com.amorepacific.inmemorycache.repository;

import com.amorepacific.inmemorycache.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // 카테고리명 수정 > Repository
    @Modifying
    @Query(value="UPDATE Category category SET category.categoryName = :categoryName WHERE category.categoryNo = :categoryNo", nativeQuery=false)
    void updateCategoryName(@Param("categoryNo") Long categoryNo, @Param("categoryName") String categoryName);
}
