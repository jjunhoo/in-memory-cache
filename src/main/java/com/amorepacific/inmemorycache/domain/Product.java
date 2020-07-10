package com.amorepacific.inmemorycache.domain;

import lombok.Data;

@Data
public class Product {
    // 상품번호
    private long productNo;
    // 상품명
    private String productName;
    // 브랜드명
    private String brandName;
    // 상품가격
    private long productPrice;
    // 카테고리 번호
    private long categoryNo;
}
