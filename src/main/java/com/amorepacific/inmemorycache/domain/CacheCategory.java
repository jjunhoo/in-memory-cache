package com.amorepacific.inmemorycache.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CacheCategory {
    // 카테고리 번호
    @JsonIgnore
    private Long categoryNo;
    // 카테고리명
    private String categoryName;
    // 상위 카테고리
    @JsonIgnore
    private String parentNo;
    // 카테고리 뎁스
    @JsonIgnore
    private String depth;
}
