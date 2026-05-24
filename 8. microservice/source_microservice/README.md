# Uniqlo Microservice – Hướng dẫn chạy

## Cấu trúc dự án

```
source_microservice/
├── eureka-gateway/         ← Eureka Server + API Gateway  (port 8080)
├── user-service/           ← User, Auth, JWT              (port 8081)
├── product-service/        ← Product, SKU, Images         (port 8082)
├── master-data-service/    ← Category, Color, Size        (port 8083)
├── order-service/          ← Cart, Orders                 (port 8084)
└── frontend/               ← React App                    (port 3000)
```

## Yêu cầu

- Java 17+
- Maven 3.8+
- Node.js 18+
- MySQL 8 đang chạy với database `uniqlo_education`
  (import file: `../source_monolithic/uniqlo-service-v1/src/main/resources/db/database.sql`)

## Thứ tự khởi động

### Bước 1: Chạy Eureka Gateway (PHẢI chạy đầu tiên)

```bash
cd eureka-gateway
mvn spring-boot:run
```

Kiểm tra: http://localhost:8080 → thấy Eureka Dashboard

### Bước 2: Chạy các Backend Services (có thể chạy song song)

**Terminal 1 – User Service:**
```bash
cd user-service
mvn spring-boot:run
```

**Terminal 2 – Product Service:**
```bash
cd product-service
mvn spring-boot:run
```

**Terminal 3 – Master Data Service:**
```bash
cd master-data-service
mvn spring-boot:run
```

**Terminal 4 – Order Service:**
```bash
cd order-service
mvn spring-boot:run
```

### Bước 3: Chạy React Frontend

```bash
cd frontend
npm install
npm start
```

Truy cập: http://localhost:3000

## Kiểm tra hoạt động

### Xem services đã đăng ký với Eureka:
http://localhost:8080

Bạn sẽ thấy:
- USER-SERVICE (8081) – UP
- PRODUCT-SERVICE (8082) – UP
- MASTER-DATA-SERVICE (8083) – UP
- ORDER-SERVICE (8084) – UP

### Test API qua Gateway:

```bash
# 1. Đăng nhập – qua Gateway → user-service
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@uniqlo.com","password":"admin123"}'

# 2. Copy access_token từ response, rồi test các API khác:

# Lấy products – qua Gateway → product-service
curl http://localhost:8080/api/products \
  -H "Authorization: Bearer <ACCESS_TOKEN>"

# Lấy colors – qua Gateway → master-data-service
curl http://localhost:8080/api/colors \
  -H "Authorization: Bearer <ACCESS_TOKEN>"

# Lấy sizes – qua Gateway → master-data-service
curl http://localhost:8080/api/sizes \
  -H "Authorization: Bearer <ACCESS_TOKEN>"

# Lấy users – qua Gateway → user-service
curl http://localhost:8080/api/users \
  -H "Authorization: Bearer <ACCESS_TOKEN>"
```

## Điểm học quan trọng

### 1. Eureka Service Discovery
- Gateway khởi động → @EnableEurekaServer → Eureka Server sẵn sàng
- Mỗi service khởi động → @EnableDiscoveryClient → tự đăng ký với Eureka
- Gateway dùng `lb://SERVICE-NAME` để route đến đúng service

### 2. @LoadBalanced RestTemplate
```java
// Trong product-service, gọi sang master-data-service:
restTemplate.getForObject("http://MASTER-DATA-SERVICE/api/colors", List.class)
// → Spring Cloud hỏi Eureka → lấy IP:Port của master-data-service → gọi đến đó
```

### 3. API Gateway Routing
```yaml
routes:
  - id: product-service
    uri: lb://PRODUCT-SERVICE   # lb = load balanced
    predicates:
      - Path=/api/products/**   # request có path /api/products/** → chuyển đến PRODUCT-SERVICE
```

### 4. JWT được validate tại Gateway
- JwtAuthenticationFilter chạy tại Gateway trước khi request đến service
- Các service phía sau không cần validate JWT nữa (tùy chọn thêm để bảo mật cao hơn)

## Sơ đồ kiến trúc

```
React (3000) → Gateway (8080) → user-service    (8081) ─┐
                                → product-service  (8082) ─┤
                                → master-data-svc  (8083) ─┤→ MySQL
                                → order-service    (8084) ─┘
                    │
                    Eureka Server (cũng ở 8080)
                    ← tất cả service đăng ký vào đây
```
