# simple-idus
## idus 과제
---
### version 정보
- spring boot 2.5.5
- java 11
- mysql 8.0.19
---
### 실행방법
1. 빌드 수행  
> ./gradlew build
2. 테스트 skip하고 빌드 수행시
> ./gradlew build -x test
3. 애플리케이션 실행
> ./gradlew bootRun
---
### API 문서
1. 애플리케이션 실행
2. [Swagger UI](http://localhost:8080/swagger-ui/) 확인
---
### Database 정보
- Database name: idus
- username : idus
- password : idus
---
### 테이블 생성 쿼리 문서
1. root 위치의 엔티티_생성.sql 참고
---
### 참고사항
- 시간 기준: 한국/서울
- 로그인은 *session*을 사용, <ins>permanent하지 않으므로 재기동 시, session 상실</ins>
