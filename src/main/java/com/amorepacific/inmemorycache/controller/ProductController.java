package com.amorepacific.inmemorycache.controller;

import com.amorepacific.inmemorycache.domain.Product;
import com.amorepacific.inmemorycache.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    // 상품 리스트 조회 > Controller
    @GetMapping("/productList")
    public @ResponseBody List<Product> getProductList() throws Exception {
        System.out.println("init controller - getProductList");
        return productService.getProductList();
    }

    // 상품 조회 > Controller
    @GetMapping("/product/{productNo}")
    public @ResponseBody Optional<Product> getProduct(@PathVariable Long productNo) throws Exception {
        System.out.println("init controller - getProduct");
        // TODO : 카테고리 테이블 JOIN 해서 -> 카테고리명으로 뽑아야됨
        return productService.getProduct(productNo);
    }

    // TODO : 테스트 편의상 GetMapping 사용, Return 값 고민
    // 상품명 수정 > Controller
    @GetMapping("/updateProductName/{productNo}/{productName}")
    public void updateProductName(@PathVariable Long productNo, @PathVariable String productName) throws Exception {
        System.out.println("init controller - updateProductName");
        productService.updateProductName(productNo, productName);
    }

    // TODO : 테스트 편의상 GetMapping 사용, Return 값 고민
    // 상품 가격 수정 > Controller
    @GetMapping("/updateProductPrice/{productNo}/{productPrice}")
    public void updateProductPrice(@PathVariable Long productNo, @PathVariable Long productPrice) throws Exception {
        System.out.println("init controller - updateProductPrice");
        productService.updateProductPrice(productNo, productPrice);
    }
}
