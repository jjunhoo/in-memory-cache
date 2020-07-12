package com.amorepacific.inmemorycache.service;

import com.amorepacific.inmemorycache.cache.Node;
import com.amorepacific.inmemorycache.cache.LRUCache;
import com.amorepacific.inmemorycache.common.BusinessLogicException;
import com.amorepacific.inmemorycache.domain.CacheCategory;
import com.amorepacific.inmemorycache.domain.CacheProduct;
import com.amorepacific.inmemorycache.mapper.CacheMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The type Cache service.
 */
@Service
public class CacheService {

    // Cache Data Eviction Policy - 메모리 관리 정책
    // LRU - Last Recently Used (가장 오랫동안 사용되지 않은 데이터를 제거)
    // 새로운 값이 추가되었을 때, 자동으로 오래된 데이터를 지워주는 기능을 통해 메모리 관리

    @Autowired
    private CacheMapper cacheMapper;

    // 카테고리 리스트 Cache 데이터
    private final LRUCache categoryCache = new LRUCache();
    // 개별 상품 Cache 데이터 저장용
    private final LRUCache productInfoByProductNoCache = new LRUCache();
    // 카테고리에 속한 상품 리스트 Cache 데이터
    private final LRUCache productInfoByCategoryNoCache = new LRUCache();

    /**
     * Sets all cache data.
     * 전체 Cache 데이터 갱신
     */
    public void setAllCacheData() {
        this.saveCategoryCacheData();
        this.saveProductCacheData();
        this.saveProductListByCategoryCacheData();
    }

    /**
     * Update category name.
     * 카테고리명 수정
     *
     * @param categoryNo   the category no
     * @param categoryName the category name
     */
    @Transactional
    public void updateCategoryName(Long categoryNo, String categoryName) {
        cacheMapper.updateCategoryName(categoryNo, categoryName);
        // Cache 데이터 갱신
        this.setAllCacheData();
    }

    /**
     * Update product name.
     * 상품명 수정
     *
     * @param productNo   the product no
     * @param productName the product name
     */
    @Transactional
    public void updateProductName(Long productNo, String productName) {
        cacheMapper.updateProductName(productNo, productName);
        // Cache 데이터 갱신
        this.setAllCacheData();
    }

    /**
     * Update product price.
     * 상품 가격 수정
     *
     * @param productNo    the product no
     * @param productPrice the product price
     */
    @Transactional
    public void updateProductPrice(Long productNo, Long productPrice) {
        cacheMapper.updateProductPrice(productNo, productPrice);
        // Cache 데이터 갱신
        this.setAllCacheData();
    }


    /**
     * Insert product info.
     * 상품 정보 등록
     *
     * @param productNo    the product no
     * @param brandName    the brand name
     * @param productName  the product name
     * @param productPrice the product price
     * @param categoryNo   the category no
     */
    public void insertProductInfo(Long productNo, String brandName, String productName, Long productPrice, Long categoryNo) {
        // 상품 정보 등록
        cacheMapper.insertProductInfo(productNo, brandName, productName, productPrice, categoryNo);
        // 상품 정보 캐시 등록
        this.setProductCacheData(String.valueOf(productNo));

    }

    /**
     * Sets product cache data.
     * 상품 정보 Cache 추가
     *
     * @param productNo the product no
     */
    public void setProductCacheData(String productNo) {
        // 상품 데이터 DB 조회
        CacheProduct product = cacheMapper.selectProduct(Long.valueOf(productNo));
        // 상품 데이터 Cache 적재용 데이터 셋팅
        productInfoByProductNoCache.put(String.valueOf(product.getProductNo()), product);
    }

    /**
     * Save category all info data.
     * 카테고리 리스트 Cache 데이터 적재
     */
    public void saveCategoryCacheData() {
        // 카테고리 리스트 데이터 DB 조회
        List<CacheCategory> categoryList = cacheMapper.selectCategoryList();
        // 카테고리 리스트 데이터 Cache 적재용 데이터 셋팅
        for (CacheCategory cacheCategory : categoryList) {
            categoryCache.put(String.valueOf(cacheCategory.getCategoryNo()), cacheCategory);
        }
    }

    /**
     * Save product info by product no key data.
     * 상품 번호 key 기반 상품 정보 Cache 데이터 적재
     */
    public void saveProductCacheData() {
        // 상품 리스트 데이터 DB 조회
        List<CacheProduct> productList = cacheMapper.selectProductList();
        // 상품 리스트 데이터 Cache 적재용 데이터 셋팅
        for (CacheProduct cacheProduct : productList) {
            productInfoByProductNoCache.put(String.valueOf(cacheProduct.getProductNo()), cacheProduct);
        }
    }

    /**
     * Save product info by category no key data.
     * 카테고리 번호 key 기반 상품 정보 Cache 데이터 적재
     */
    public void saveProductListByCategoryCacheData() {
        // 상품 리스트 데이터 DB 조회
        List<CacheProduct> productList = cacheMapper.selectProductList();
        // 카테고리 번호를 기준으로 상품 Obejct > Grouping
        Map <String, List<CacheProduct>> map = productList.stream().collect(Collectors.groupingBy(CacheProduct::getCategoryNo));

        List<String> mapKeyList = new ArrayList<>();
        // Key 값(카테고리 번호) 추출
        for(String key : map.keySet()){
            mapKeyList.add(key);
        }
        // 상품 리스트 데이터 Cache 적재용 데이터 셋팅
        for (int i = 0; i < map.size(); i++) {
            productInfoByCategoryNoCache.putList(mapKeyList.get(i), map.get(String.valueOf(i + 1)));
        }
    }

    /**
     * Gets cache product.
     * 개별 상품 Cache 데이터 조회
     *
     * @param productNo the product no
     * @return the cache product info
     */
    public CacheProduct getCacheProduct(String productNo) {
        // 상품 Cache 데이터가 적재되어 있지 않은 경우
        if (productInfoByProductNoCache.size() == 0) {
            // Cache 갱신
            this.setAllCacheData();
        }

        // 상품 Cache 데이터에 해당 상품번호의 상품 데이터가 없는 경우, Cache 재갱신
        if (ObjectUtils.isEmpty(productInfoByProductNoCache.get(productNo))) {
            // Cache Miss 시, DB 조회
            CacheProduct product = cacheMapper.selectProduct(Long.valueOf(productNo));
            // DB 조회값이 있으면 DB 조회 값 return
            if (!ObjectUtils.isEmpty(product)) {
                productInfoByProductNoCache.put(productNo, product);
                return product;
            } else {
                throw new BusinessLogicException();
            }
        }
        Node node = productInfoByProductNoCache.get(productNo);
        return (CacheProduct) node.getData();
    }

    /**
     * Gets cache category list.
     * 카테고리 리스트 Cache 데이터 조회
     *
     * @return the cache category list info
     */
    public List<CacheCategory> getCacheCategoryList() {
        // 카테고리 리스트 Cache 데이터가 적재되어 있지 않은 경우, Cache 재갱신
        if (categoryCache.size() == 0) {
            // Cache 갱신
            this.setAllCacheData();
        }
        // 카테고리 리스트 Cache 데이터가 적재되어 있지 않은 경우, Cache 재갱신 후에도 없는 경우, Business Logic Exception 처리
        if (categoryCache.size() == 0) {
            throw new BusinessLogicException();
        }
        // return 용 데이터 셋팅
        List<CacheCategory> categoryList = new ArrayList<>();
        for (int i = 1; i <= categoryCache.size(); i++) {
            categoryList.add((CacheCategory) categoryCache.get(String.valueOf(i)).getData());
        }
        return categoryList;
    }

    /**
     * Gets cache product list by category.
     * 카테고리에 속한 상품 리스트 Cache 데이터 조회
     *
     * @param categoryNo the category no
     * @return the cache product list by category info
     */
    public List<CacheProduct> getCacheProductListByCategory(String categoryNo) {
        // 카테고리에 속한 상품 리스트 Cache 데이터가 적재되어 있지 않은 경우
        if (productInfoByCategoryNoCache.size() == 0) {
            // Cache 갱신
            this.setAllCacheData();
        }
        // 카테고리에 속한 상품 리스트 Cache 데이터에 해당 카테고리에 속한 상품 리스트 데이터가 없는 경우, Cache 재갱신
        if (ObjectUtils.isEmpty(productInfoByCategoryNoCache.get(categoryNo))) {
            // Cache Miss 시, DB 조회
            List<CacheProduct> productList = cacheMapper.selectProductListByCategory(categoryNo);
            // DB 조회값이 있으면 DB 조회 값 return
            if (!ObjectUtils.isEmpty(productList)) {
                productInfoByCategoryNoCache.putList(categoryNo, productList);
                return productList;
            } else {
                throw new BusinessLogicException();
            }
        }
        return productInfoByCategoryNoCache.get(String.valueOf(categoryNo)).getDataList();
    }
}