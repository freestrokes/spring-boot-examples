# spring-boot-examples
spring-boot-examples

## Dev Environment Run

```shell
# Docker Run
cd dev
docker compose up -d
```
```shell
# Application Run
./gradlew clean bootRun --args='--spring.profiles.active=dev --debug-jvm'
```

## Swagger API Docs
```shell
{PROTOCOL}://{APP_IP}:{APP_PORT}/swagger-ui/index.html
```

## Application Properties
`src/main/resources/config` 폴더 하위의 프로파일 별 yaml 파일 구분.
* `application.yml`: 기본 환경 설정
* `application-dev.yml`: dev 환경 설정
* `application-stg.yml`: stage 환경 설정
* `application-prod.yml`: prod 환경 설정

### Env Variables
| Prefix                                     | Key                      | Description                              |
|--------------------------------------------|--------------------------|------------------------------------------|
| server                                     | port                     | 서버 포트 넘버                                 |
| spring.config.activate                     | on-profile               | 활성 프로파일 설정                               |
| spring.jpa                                 | open-in-view             | OSIV(Open Session In View) 활성화 플래그       |
| spring.jpa                                 | on-profile               | 활성 프로파일 설정                               |
| spring.jpa                                 | database                 | JPA 데이터베이스 설정                            |
| spring.jpa                                 | database-platform        | JPA 데이터베이스 플랫폼 설정                        |
| spring.jpa.hibernate                       | ddl-auto                 | JPA 데이터베이스 초기화 전략 설정                     |
| spring.jpa.hibernate.naming                | physical-strategy        | JPA 엔티티 물리적 명시 전략 설정                     |
| spring.jpa.hibernate.naming                | implicit-strategy        | JPA 엔티티 암시적(논리적) 명시 전략 설정                |
| spring.jpa.properties.hibernate            | show_sql                 | 하이버네이트 SQL 로깅 활성화 플래그                    |
| spring.jpa.properties.hibernate            | format_sql               | 하이버네이트 SQL 포맷팅 활성화 플래그                   |
| spring.jpa.properties.hibernate            | use_sql_comments         | 하이버네이트 SQL 주석 활성화 플래그                    |
| spring.jpa.properties.hibernate            | default_batch_fetch_size | 지연 로딩 발생 시 조회 할 프록시 객체 수 설정 (in Query)   |
| spring.datasource                          | url                      | 데이터베이스 URL 설정                            |
| spring.datasource                          | username                 | 데이터베이스 username 설정                       |
| spring.datasource                          | password                 | 데이터베이스 password 설정                       |
| spring.security.jwt.token                  | secret-key               | JWT 서명에 사용할 Secret Key                   |
| spring.security.jwt.token                  | access-token-expiration  | JWT Access Token 만료 설정                   |
| spring.security.jwt.token                  | refresh-token-expiration | JWT Refrech Token 만료 설정                  |
