package com.amorepacific.inmemorycache.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CacheProduct {
    // 상품번호
    @JsonIgnore
    private Long productNo;
    // 카테고리 번호
    @JsonIgnore
    private String categoryNo;
    // 브랜드명
    @JsonIgnore
    private String brandName;
    // 상품명
    private String productName;
    // 카테고리명
    private String categoryName;
    // 상품가격
    private Long productPrice;
}
