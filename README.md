# Group

  Full name                  | ID      | Email
  ---------------------------|-----------|-----------------------------
  Lê Hoàng Khánh   | 21522205  | 21522205@gm.uit.edu.vn
  Lê Xuân Linh   | 21522286  | 21522286@gm.uit.edu.vn
  Nguyễn Đình Khoa   | 21522227  | 21522227@gm.uit.edu.vn
  Đặng Tiến Đạt | 21521929  | 21521929@gm.uit.edu.vn


# Car Connect
Backend of Car Connect website written in microservice architecture using Spring Boot.

# Technology Stack

- Java
- Spring Boot
- Spring Cloud
- Spring Data
- Spring Security
- zipkin
- Kafka
- Lombok
- JWT
- Eureka
- Websocket

# Database

- MongoDB

# Project Demo
   - Browser: https://backend.tuilakhanh.id.vn

# Requirement
   - Java 17
   - Docker
   - Docker Compose

# Installation and Setup



```bash
# Setup the web app 
git clone github.com/MicroserviceCDM/backend-microservices
cd backend-microservices
docker compose -f docker-compose-infra.yml up -d
./build.sh
```

Access via port: 8761
Chat Service WS via port: 8088
