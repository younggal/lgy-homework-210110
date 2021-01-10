## lgy-homework-210110
### 과제 프로젝트 :: API 개발 (Spring Boot, Gradle + Spring Security + JPA)

#### DB Create SQL 
- 소스 코드 내, docs 디렉토리 하위에 mysql_create_sql.sql 파일 포함하였습니다.

#### Swagger UI : http://localhost:8088/swagger-ui.html
- Security 적용이 되어있어, login api로 jwt token 발급 후 상단 'Authorize'에 토큰을 입력 후 API 호출 가능합니다.
- login 하기 이전에 회원가입 후 가입한 '이메일'과 '비밀번호'로 로그인 가능합니다.
- login은 Swagger에서 할 수 없으므로, 별도 API 테스트 프로그램을 이용해야 합니다. (/api/login)
- body > raw  
```json
{
    "email": "가입 이메일",
    "password": "비밀번호"
}
```
- token을 이용한 로그인이므로, 로그아웃 api는 구현하지 않았습니다. (클라이언트 단에서 토큰 삭제 필요)
