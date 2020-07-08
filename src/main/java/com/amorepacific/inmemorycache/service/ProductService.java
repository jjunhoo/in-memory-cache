package com.amorepacific.inmemorycache.service;

import com.amorepacific.inmemorycache.domain.Product;
import com.amorepacific.inmemorycache.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    // 상품 리스트 조회 > Service
    public List<Product> getProductList() {
        System.out.println("init service - getProductList");
        return productRepository.findAll();
    }

    // 상품 조회 > Service
    public Optional<Product> getProduct(Long productNo) {
        System.out.println("init service - getProduct");
        return productRepository.findById(productNo);
    }

    // 상품명 수정 > Service
    @Transactional
    public void updateProductName(Long productNo, String productName) {
        System.out.println("init service - updateProductName");
        productRepository.updateProductName(productNo, productName);

        // TODO : Transaction 성공 시 Cache 갱신
    }

    // 상품 가격 수정 > Service
    @Transactional
    public void updateProductPrice(Long productNo, Long productPrice) {
        System.out.println("init service - updateProductPrice");
        productRepository.updateProductPrice(productNo, productPrice);

        // TODO : Transaction 성공 시 Cache 갱신
    }

}
