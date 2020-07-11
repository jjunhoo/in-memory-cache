package com.amorepacific.inmemorycache.service;

import com.amorepacific.inmemorycache.domain.CacheCategory;
import com.amorepacific.inmemorycache.domain.CacheProduct;
import com.amorepacific.inmemorycache.mapper.CacheMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The type Cache service.
 */
@Service
public class CacheService {

    @Autowired
    private CacheMapper cacheMapper;

    // 카테고리 Cache 데이터 저장용 Map
    private final Map <String, List<CacheCategory>> categoryGroups = new HashMap<>();
    private final Map <String, List<CacheProduct>> productListByCategoryGroups = new HashMap<>();
    private final Map <String, CacheProduct> productGroups = new HashMap<>();

    /**
     * Gets category cache data.
     * 카테고리 리스트 Cache 데이터 적재
     */
    public void saveCategoryCacheData() {
        Map <String, List<CacheCategory>> map = new HashMap<>();
        // 카테고리 리스트 데이터 DB 조회
        List<CacheCategory> categoryList = cacheMapper.selectCategoryList();
        // 카테고리 리스트 데이터 Cache 적재용 데이터 셋팅
        map.put("categoryList", categoryList);
        // 카테고리 리스트 데이터 Cache 적재
        categoryGroups.clear();
        categoryGroups.putAll(map);
    }

    /**
     * Gets cache category list.
     * 카테고리 리스트 Cache 데이터 조회
     *
     * @return the cache category list
     * @throws Exception the exception
     */
    public List<CacheCategory> getCacheCategoryList() throws Exception {
        final String categoryListkey = "categoryList";

        // 카테고리 Cache 데이터가 적재되어 있지 않은 경우, Cache 재갱신
        if (categoryGroups.isEmpty()) {
            this.saveCategoryCacheData();
        }
        // 카테고리 Cache 데이터에 해당 카테고리번호의 카테고리 데이터가 없는 경우
        if (ObjectUtils.isEmpty(categoryGroups.get(categoryListkey))) {
            // Cache Miss 시, DB 조회
            List<CacheCategory> categoryList = cacheMapper.selectCategoryList();
            // DB 조회값이 있으면 DB 조회 값 return
            if (!ObjectUtils.isEmpty(categoryList)) {
                // Cache 갱신
                this.setAllCacheData();
                return categoryList;
            }
            // TODO : else - return "해당 데이터가 없습니다"
        }
        return categoryGroups.get(categoryListkey);
    }

    /**
     * Save product list by category cache data.
     * 카테고리에 속한 상품 리스트 Cache 데이터 적재
     */
    public void saveProductListByCategoryCacheData() {
        // 카테고리에 속한 상품 리스트 데이터 DB 조회
        List<CacheProduct> productList = cacheMapper.selectProductListForCategory();
        // 카테고리에 속한 상품 리스트 데이터 Cache 적재용 데이터 셋팅
        Map <String, List<CacheProduct>> map = productList.stream().collect(Collectors.groupingBy(CacheProduct::getCategoryNo));
        // 카테고리 리스트 데이터 Cache 적재
        productListByCategoryGroups.clear();
        productListByCategoryGroups.putAll(map);
    }

    /**
     * Gets cache product list by category.
     * 카테고리에 속한 상품 리스트 Cache 데이터 조회
     *
     * @param categoryNo the category no
     * @return the cache product list by category
     * @throws Exception the exception
     */
    public List<CacheProduct> getCacheProductListByCategory(String categoryNo) throws Exception {
        // 카테고리에 속한 상품 리스트 Cache 데이터가 적재되어 있지 않은 경우, Cache 재갱신
        if (productListByCategoryGroups.isEmpty()) {
            this.saveProductListByCategoryCacheData();
        }
        // 카테고리에 속한 상품 리스트 Cache 데이터에 해당 카테고리번호의 카테고리에 속한 상품 리스트 데이터가 없는 경우
        if (ObjectUtils.isEmpty(productListByCategoryGroups.get(categoryNo))) {
            // Cache Miss 시, DB 조회
            List<CacheProduct> productList = cacheMapper.selectProductListByCategory(categoryNo);
            // DB 조회값이 있으면 DB 조회 값 return
            if (!ObjectUtils.isEmpty(productList)) {
                // Cache 갱신
                this.setAllCacheData();
                return productList;
            }
            // TODO : else - return "해당 데이터가 없습니다"
        }
        return productListByCategoryGroups.get(categoryNo);
    }

    /**
     * Save product cache data.
     * 상품 리스트 Cache 데이터 적재
     */
    public void saveProductCacheData() {
        Map <String, CacheProduct> map = new HashMap<>();
        // 상품 리스트 데이터 DB 조회
        List<CacheProduct> productList = cacheMapper.selectProductList();
        // 상품 리스트 데이터 Cache 적재용 데이터 셋팅
        for (CacheProduct cacheProduct : productList) {
            map.put(String.valueOf(cacheProduct.getProductNo()), cacheProduct);
        }
        // 카테고리 리스트 데이터 Cache 적재
        productGroups.clear();
        productGroups.putAll(map);
    }

    /**
     * Gets cache product.
     * 개별 상품 Cache 데이터 조회
     *
     * @param productNo the product no
     * @return the cache product
     * @throws Exception the exception
     */
    public CacheProduct getCacheProduct(String productNo) throws Exception {
        // 상품 Cache 데이터가 적재되어 있지 않은 경우
        if (productGroups.isEmpty()) {
            this.saveProductCacheData();
        }
        // 상품 Cache 데이터에 해당 상품번호의 상품 데이터가 없는 경우, Cache 재갱신
        if (ObjectUtils.isEmpty(productGroups.get(productNo))) {
            // Cache Miss 시, DB 조회
            CacheProduct product = cacheMapper.selectProduct(Long.valueOf(productNo));
            // DB 조회값이 있으면 DB 조회 값 return
            if (!ObjectUtils.isEmpty(product)) {
                // Cache 갱신
                this.setAllCacheData();
                return product;
            }
            // TODO : else - return "해당 데이터가 없습니다"
        }
        return productGroups.get(productNo);
    }

    /**
     * Sets all cache data.
     * 전체 Cache 데이터 갱신
     */
    public void setAllCacheData() {
        this.saveCategoryCacheData();
        this.saveProductCacheData();
        this.saveProductListByCategoryCacheData();
    }
}