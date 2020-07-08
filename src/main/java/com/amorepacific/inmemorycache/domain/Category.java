package com.amorepacific.inmemorycache.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {
    // 카테고리 번호
    @Id
    private Long categoryNo;
    // 카테고리명
    private String categoryName;
    // 상위 카테고리
    private String parentNo;
    // 카테고리 뎁스
    private String depth;

    public Long getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(Long categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder("CategoryEntity(");
        value.append("category_no : ");
        value.append(categoryNo);
        value.append(",category_name : ");
        value.append(categoryName);
        value.append(",parent_no : ");
        value.append(parentNo);
        value.append(",depth : ");
        value.append(depth);
        value.append(")");
        return value.toString();
    }
}
