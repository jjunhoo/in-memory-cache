package com.amorepacific.inmemorycache.domain;

import lombok.Data;

@Data
public class CacheCategory {
    // 카테고리 번호
    private Long categoryNo;
    // 카테고리명
    private String categoryName;
    // 상위 카테고리
    private String parentNo;
    // 카테고리 뎁스
    private String depth;
}
