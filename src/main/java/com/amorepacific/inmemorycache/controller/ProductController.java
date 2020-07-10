package com.amorepacific.inmemorycache.controller;

import com.amorepacific.inmemorycache.domain.Product;
import com.amorepacific.inmemorycache.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * Gets product list.
     * 상품 리스트 조회
     * @return the product list
     * @throws Exception the exception
     */
    @GetMapping("/productList")
    public @ResponseBody List<Product> getProductList() throws Exception {
        System.out.println("init controller - getProductList");
        return productService.getProductList();
    }

    /**
     * Gets product.
     * 상품 조회
     * @param productNo the product no
     * @return the product
     * @throws Exception the exception
     */
    @GetMapping("/product/{productNo}")
    public @ResponseBody Product getProduct(@PathVariable Long productNo) throws Exception {
        System.out.println("init controller - getProduct");
        return productService.getProduct(productNo);
    }

    /**
     * Update product name.
     * 상품명 수정
     * @param productNo   the product no
     * @param productName the product name
     * @throws Exception the exception
     */
    // TODO : 테스트 편의상 GetMapping 사용, Return 값 고민
    @GetMapping("/updateProductName/{productNo}/{productName}")
    public void updateProductName(@PathVariable Long productNo, @PathVariable String productName) throws Exception {
        System.out.println("init controller - updateProductName");
        productService.updateProductName(productNo, productName);
    }

    /**
     * Update product price.
     * 상품 가격 수정
     * @param productNo    the product no
     * @param productPrice the product price
     * @throws Exception the exception
     */
    // TODO : 테스트 편의상 GetMapping 사용, Return 값 고민
    @GetMapping("/updateProductPrice/{productNo}/{productPrice}")
    public void updateProductPrice(@PathVariable Long productNo, @PathVariable Long productPrice) throws Exception {
        System.out.println("init controller - updateProductPrice");
        productService.updateProductPrice(productNo, productPrice);
    }
}
