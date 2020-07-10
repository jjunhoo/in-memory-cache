package com.amorepacific.inmemorycache.service;

import com.amorepacific.inmemorycache.domain.Product;
import com.amorepacific.inmemorycache.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Product service.
 */
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * Gets product list.
     * 상품 리스트 조회
     * @return the product list
     */
    public List<Product> getProductList() {
        System.out.println("init service - getProductList");
        return productMapper.selectProductList();
    }

    /**
     * Gets product.
     * 상품 조회
     * @param productNo the product no
     * @return the product
     */
    public Product getProduct(Long productNo) {
        System.out.println("init service - getProduct");
        return productMapper.selectProduct(productNo);
    }

    /**
     * Update product name.
     * 상품명 수정
     * @param productNo   the product no
     * @param productName the product name
     */
    @Transactional
    public void updateProductName(Long productNo, String productName) {
        System.out.println("init service - updateProductName");
        productMapper.updateProductName(productNo, productName);

        // TODO : Transaction 성공 시 Cache 갱신
    }

    /**
     * Update product price.
     * 상품 가격 수정
     * @param productNo    the product no
     * @param productPrice the product price
     */
    @Transactional
    public void updateProductPrice(Long productNo, Long productPrice) {
        System.out.println("init service - updateProductPrice");
        productMapper.updateProductPrice(productNo, productPrice);

        // TODO : Transaction 성공 시 Cache 갱신
    }

}
