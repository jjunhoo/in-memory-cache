package com.amorepacific.inmemorycache.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    // 상품번호
    @Id
    private long productNo;
    // 상품명
    private String productName;
    // 브랜드명
    private String brandName;
    // 상품가격
    private long productPrice;
    // 카테고리 번호
    private long categoryNo;

    public long getProductNo() {
        return productNo;
    }

    public void setProductNo(long productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public long getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(long categoryNo) {
        this.categoryNo = categoryNo;
    }

    @Override
    public String toString() {
        StringBuilder value = new StringBuilder("ProductEntity(");
        value.append("product_no : ");
        value.append(productNo);
        value.append(",product_name : ");
        value.append(productName);
        value.append(",brand_name : ");
        value.append(brandName);
        value.append(",product_price : ");
        value.append(productPrice);
        value.append(",category_no : ");
        value.append(categoryNo);
        value.append(")");
        return value.toString();
    }
}
