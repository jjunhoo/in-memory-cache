package com.amorepacific.inmemorycache.controller;

import com.amorepacific.inmemorycache.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The type Product controller.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    /**
     * The Product service.
     */
    @Autowired
    ProductService productService;

    /**
     * Update product name.
     * 상품명 수정
     * @param productNo   the product no
     * @param productName the product name
     * @throws Exception the exception
     */
    @GetMapping("/putProductName/{productNo}/{productName}")
    public void updateProductName(@PathVariable Long productNo, @PathVariable String productName) throws Exception {
        productService.updateProductName(productNo, productName);
    }

    /**
     * Update product price.
     * 상품 가격 수정
     * @param productNo    the product no
     * @param productPrice the product price
     * @throws Exception the exception
     */
    @GetMapping("/putProductPrice/{productNo}/{productPrice}")
    public void updateProductPrice(@PathVariable Long productNo, @PathVariable Long productPrice) throws Exception {
        productService.updateProductPrice(productNo, productPrice);
    }
}
