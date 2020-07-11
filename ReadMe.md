__[ 프로젝트 실행 방법 ]__
1. Maven - clean
2. Maven - install
3. 프로젝트 실행 
    - Springboot 기동 시 resources > schema.sql, data.sql 파일을 통해 자동 테이블 생성 및 Dummy 데이터 생성
    - Dummy 데이터를 기반으로 최초 Cache 데이터 생성

__[ End-Point 사용 방법 ]__
- 브라우저를 통해 간단한 테스트가 가능하도록 모든 End-Point는 GET 방식으로 작성
- 브라우저 URL 입력창에 하단 End-Point 리스트 입력
- Input Parameter 용어 정의
    - productNo : 상품 번호 (예: 1)
    - productName : 상품명 (예: 테스트상품명)
    - productPrice : 상품 가격 (예 :10000)
    - categoryNo : 카테고리 번호 (예 :1)
    - categoryName : 카테고리명 (예: 테스트카테고리명)

__[ End-Point 리스트 ]__
- 카테고리 리스트 조회 (Cache)
    - http://localhost:8080/cache/categoryList

- 개별 상품 조회 (Cache)
    - http://localhost:8080/cache/product/{productNo}

- 특정 카테고리에 속한 상품 조회 (Cache)
    - http://localhost:8080/cache/productListByCategory/{categoryNo}

- 상품명 수정 (DB)
    - http://localhost:8080/product/putProductName/{productNo}/{productName}

- 상품 가격 수정 (DB)
    - http://localhost:8080/product/putProductPrice/{productNo}/{productPrice}

- 카테고리명 수정 (DB)
    - http://localhost:8080/category/putCategoryName/{categoryNo}/{categoryName}
