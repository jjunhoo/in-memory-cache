package com.amorepacific.inmemorycache;

import com.amorepacific.inmemorycache.service.CacheService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan(basePackages = {"com.amorepacific.inmemorycache.mapper.*"})
public class InMemoryCacheApplication {

    @Autowired
    CacheService cacheService;

    public static void main(String[] args) {
        SpringApplication.run(InMemoryCacheApplication.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(InMemoryCacheApplication.class);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
        sessionFactory.setMapperLocations(res);
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        return sqlSessionTemplate;
    }

    @Bean
    InitializingBean initializingDataCaching() {
        return () -> {
            // 카테고리 데이터 Caching
            cacheService.saveCategoryCacheData();
            // 상품 데이터 Caching
            cacheService.saveProductCacheData();
            // 카테고리에 속한 상품 리스트 데이터 Caching
            cacheService.saveProductListByCategoryCacheData();
        };
    }
}
