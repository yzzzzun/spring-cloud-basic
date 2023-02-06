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
  
