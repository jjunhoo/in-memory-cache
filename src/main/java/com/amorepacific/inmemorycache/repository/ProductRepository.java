package com.amorepacific.inmemorycache.repository;

import com.amorepacific.inmemorycache.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // 상품명 수정 > Repository
    @Modifying
    @Query(value="UPDATE Product product SET product.productName = :productName WHERE product.productNo = :productNo", nativeQuery=false)
    void updateProductName(@Param("productNo") Long productNo, @Param("productName") String productName);

    // 상품 가격 수정 > Repository
    @Modifying
    @Query(value="UPDATE Product product SET product.productPrice = :productPrice WHERE product.productNo = :productNo", nativeQuery=false)
    void updateProductPrice(@Param("productNo") Long productNo, @Param("productPrice") Long productPrice);
}
