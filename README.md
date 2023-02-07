# spring-cloud-basic

---
# 1. Service Discovery

## 1-1. Spring-Cloud-Gateway

### Eureka Discovery Server
- build.gradle
    ```
    implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-server'
    ```
- application.yml
    ```
    server:
      port: 8761
    
    spring:
      application:
        name: discoveryserver
    
    eureka:
      client:
        register-with-eureka: false
        fetch-registry: false
    ```
### Client Service
- build.gradle
    ```
    implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'
    ```
- 포트 지정
  - application.yml
    ```
    server:
      port: 58100
    
    spring:
      application:
        name: user-service
    
    eureka:
      client:
        register-with-eureka: true
        fetch-registry: true
        service-url:
          defaultZone: http://127.0.0.1:8761/eureka
          
    ```
  - boot run 
    ```
    ./gradlew bootRun —args=’—server.port=9001’
    ./gradlew bootRun —args=’—server.port=9002’
    ./gradlew bootRun —args=’—server.port=9003’
    ```
- 랜덤 포트
  - application.yml
    ```
    server:
      port: 0
      
    eureka:
      instance:
        instance-id: ${spring.cloud.client.hostname}:${spring.application.instance_id:${random.value}}
    ```
  - 랜덤 포트로 실행시 각 인스턴스 구분 불가
  - 인스턴스 아이디를 통해 구분해주면 등록된 서비스의 상태와 목록 확인가능
---
# 2. API Gateway
## 2-1 Api Gateway
사용자가 설정한 라우팅 설정에 따라 각 엔드포인트로 클라이언트를 대신해 요청 및 전달하는 프록시 역할
- 인증 및 권한부여
- 서비스 검색 통합
- 응답 캐싱
- 정책 회로 차단 및 QoS 다시시도
- 속도 제한
- 부하 분산
- 로깅, 추적, 상관 관계
- 헤더 쿼리 문자열 및 청구 변환
- IP 허용 목록 추가
---
### Spring Cloud MSA 통신
- RestTemplate
- Feign Client

### Ribbon : Client side Load Balancer
- 서비스 이름(name)으로 호출
- Health Check
- 비동기 처리가 잘 되지않아 사용하지 않는 추세..

### Netflix Zuul
- Spring boot 2.4 이상 maintenance
- 동기방식 서비스

### Spring Cloud Gateway

