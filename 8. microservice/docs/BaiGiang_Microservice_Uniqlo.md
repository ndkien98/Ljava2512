# BÀI GIẢNG: KIẾN TRÚC MICROSERVICE
## Chuyển đổi từ Monolithic sang Microservice với Spring Cloud & React

> **Đối tượng:** Lập trình viên Java đã biết Spring Boot  
> **Thời lượng:** 8-10 tiết  
> **Dự án thực hành:** Hệ thống thương mại điện tử Uniqlo

---

## MỤC LỤC

1. [Monolithic vs Microservice – Hiểu từ thực tế](#1-monolithic-vs-microservice)
2. [Thiết kế kiến trúc Microservice cho Uniqlo](#2-thiết-kế-kiến-trúc)
3. [Eureka Server – Trung tâm đăng ký service](#3-eureka-server)
4. [API Gateway với Spring Cloud Gateway](#4-api-gateway)
5. [Spring Cloud Config – Quản lý cấu hình tập trung](#5-spring-cloud-config)
6. [Các Backend Microservice](#6-các-backend-microservice)
7. [Frontend với ReactJS](#7-frontend-reactjs)
8. [Giao tiếp giữa các service (@LoadBalanced RestTemplate)](#8-giao-tiếp-giữa-services)
9. [Demo toàn bộ hệ thống](#9-demo-hệ-thống)

---

## 1. MONOLITHIC vs MICROSERVICE

### 1.1 Kiến trúc Monolithic – Phân tích dự án Uniqlo hiện tại

```
uniqlo-service-v1/ (Monolithic)
├── controller/
│   ├── AuthController.java
│   ├── CategoryController.java
│   ├── UserController.java
│   └── resource/
│       ├── ProductResource.java
│       ├── UserResource.java
│       └── MasterDataResource.java
├── service/
│   ├── AuthService.java
│   ├── ProductService.java
│   ├── UserService.java
│   └── CategoryService.java
├── entity/ (14 entities dùng chung)
│   ├── Product.java, ProductSku.java, ProductImage.java
│   ├── User.java, Role.java, Token.java
│   ├── Category.java, Color.java, Size.java
│   ├── CartItem.java, Review.java
│   └── ...
└── resources/
    └── db/database.sql (1 database duy nhất)
```

**Vấn đề của Monolithic khi scale:**

| Vấn đề | Ảnh hưởng |
|--------|-----------|
| Deploy toàn bộ ứng dụng khi thay đổi 1 module | Downtime, rủi ro cao |
| Không thể scale riêng từng tính năng | Lãng phí tài nguyên |
| Team lớn khó làm việc song song | Conflict code thường xuyên |
| Technology lock-in | Không thể dùng công nghệ phù hợp cho từng domain |
| Một bug trong 1 service → toàn bộ hệ thống sập | Single point of failure |

### 1.2 Kiến trúc Microservice – Giải pháp

```
                           ┌─────────────────────────────────────────────┐
                           │              Uniqlo Ecosystem                │
                           │                                              │
  React Frontend           │  ┌──────────────────────────────────────┐   │
  (port 3000)  ─────────►  │  │   API Gateway + Eureka Server        │   │
                           │  │         (port 8080)                  │   │
                           │  └──────────────────────────────────────┘   │
                           │           │         │         │         │    │
                           │     ┌─────┘    ┌────┘    ┌───┘    ┌────┘   │
                           │     ▼          ▼         ▼        ▼        │
                           │  user-     product-  master-   order-      │
                           │  service   service   data-svc  service     │
                           │  (8081)    (8082)    (8083)    (8084)      │
                           │     │          │         │        │        │
                           │     └──────────┴─────────┴────────┘        │
                           │                    │                        │
                           │           MySQL: uniqlo_education           │
                           └─────────────────────────────────────────────┘
```

**Lợi ích của Microservice:**

| Lợi ích | Mô tả |
|---------|-------|
| Independent Deploy | Deploy từng service riêng lẻ, không ảnh hưởng service khác |
| Scale độc lập | Product service nhận nhiều traffic → scale riêng product service |
| Team autonomy | Team product, team user làm việc độc lập |
| Technology flexibility | Product service dùng Java, frontend dùng React |
| Fault isolation | User service sập → các service khác vẫn hoạt động |

---

## 2. THIẾT KẾ KIẾN TRÚC

### 2.1 Phân tích Domain và Microservice

Dựa trên database hiện tại (`uniqlo_education`), ta chia domain như sau:

```
Database Tables → Domain → Service
─────────────────────────────────────────────────────────────────
users, roles, user_roles, tokens          → USER DOMAIN      → user-service
products, product_skus, product_images    → PRODUCT DOMAIN   → product-service  
categories, colors, sizes                 → MASTER DATA      → master-data-service
cart_items, reviews, visit_stats          → ORDER/CART       → order-service
```

### 2.2 Cấu trúc dự án hoàn chỉnh

```
source_microservice/
├── config-server/          # Spring Cloud Config Server (port 8888)
├── eureka-gateway/         # Eureka Server + API Gateway (port 8080)
├── user-service/           # User management (port 8081)
├── product-service/        # Product & SKU (port 8082)
├── master-data-service/    # Category, Color, Size (port 8083)
├── order-service/          # Cart, Orders (port 8084)
└── frontend/               # React app (port 3000)
```

### 2.3 Technology Stack

```yaml
Backend:
  - Spring Boot: 3.5.x
  - Spring Cloud: 2024.0.x
  - Spring Cloud Netflix Eureka: Service Discovery
  - Spring Cloud Gateway: API Gateway + Load Balancer
  - Spring Cloud Config: Centralized Configuration
  - Spring Security + JWT: Authentication
  - Spring Data JPA: ORM
  - MySQL 8: Database

Frontend:
  - React 18: UI Framework
  - Axios: HTTP Client
  - React Router: Navigation
  - TailwindCSS: Styling

Infrastructure:
  - Maven: Build tool
  - Git: Config repository
```

---

## 3. EUREKA SERVER

### 3.1 Eureka Server là gì?

Eureka Server là **Service Registry** – một "cuốn sổ danh bạ" nơi:
- Mỗi microservice **tự đăng ký** tên và địa chỉ của mình khi khởi động
- Các service khác **tra cứu** địa chỉ của service cần gọi
- Tự động **phát hiện** service bị lỗi và loại khỏi danh sách

```
user-service khởi động → đăng ký với Eureka: "Tôi là USER-SERVICE, đang ở localhost:8081"
product-service muốn gọi user-service → hỏi Eureka: "USER-SERVICE đang ở đâu?"
Eureka trả lời: "USER-SERVICE đang ở localhost:8081"
```

### 3.2 Tích hợp Eureka vào API Gateway

Trong dự án này ta **nhúng Eureka Server vào API Gateway** để giảm số lượng service cần quản lý.

**pom.xml cho eureka-gateway:**

```xml
<dependencies>
    <!-- Spring Cloud Gateway -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
    </dependency>
    
    <!-- Eureka Server (nhúng vào gateway) -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
    
    <!-- Eureka Client (để gateway tự đăng ký với Eureka) -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    
    <!-- Load Balancer -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-loadbalancer</artifactId>
    </dependency>
</dependencies>
```

**application.yml cho eureka-gateway:**

```yaml
server:
  port: 8080

spring:
  application:
    name: eureka-gateway
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: lb://USER-SERVICE          # lb:// = Load Balanced
          predicates:
            - Path=/api/users/**,/api/v1/auth/**
            
        - id: product-service
          uri: lb://PRODUCT-SERVICE
          predicates:
            - Path=/api/products/**
            
        - id: master-data-service
          uri: lb://MASTER-DATA-SERVICE
          predicates:
            - Path=/api/categories/**,/api/colors/**,/api/sizes/**
            
        - id: order-service
          uri: lb://ORDER-SERVICE
          predicates:
            - Path=/api/orders/**,/api/cart/**

# Eureka Server Configuration
eureka:
  client:
    register-with-eureka: false    # Gateway không cần tự đăng ký
    fetch-registry: false
    service-url:
      defaultZone: http://localhost:8080/eureka/
  server:
    wait-time-in-ms-when-sync-empty: 0  # Không đợi sync khi start
```

**Annotation kích hoạt Eureka Server:**

```java
@SpringBootApplication
@EnableEurekaServer   // Kích hoạt Eureka Server
public class EurekaGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaGatewayApplication.class, args);
    }
}
```

### 3.3 Eureka Client – Cấu hình cho mỗi service

Mỗi backend service cần:

**pom.xml:**
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

**application.yml:**
```yaml
spring:
  application:
    name: user-service    # Tên đăng ký với Eureka (phải viết hoa khi gọi lb://)

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8080/eureka/   # Địa chỉ Eureka Server
  instance:
    prefer-ip-address: true    # Đăng ký bằng IP thay vì hostname
```

**Annotation:**
```java
@SpringBootApplication
@EnableDiscoveryClient   // Kích hoạt Eureka Client
public class UserServiceApplication {
    ...
}
```

---

## 4. API GATEWAY

### 4.1 API Gateway là gì?

API Gateway là **cổng vào duy nhất** của toàn bộ hệ thống:
- Client chỉ cần biết **1 địa chỉ** (gateway)
- Gateway tự **định tuyến** request đến service phù hợp
- Xử lý **Authentication** tập trung
- **Load balancing** tự động
- **Rate limiting**, logging tập trung

### 4.2 Cơ chế định tuyến (Routing)

```
Client                     Gateway                    Services
  │                           │                          │
  │  GET /api/products/1      │                          │
  ├──────────────────────────►│                          │
  │                           │  Path matches            │
  │                           │  /api/products/**        │
  │                           │  → lb://PRODUCT-SERVICE  │
  │                           ├─────────────────────────►│ product-service:8082
  │                           │                          │
  │       200 OK + data       │        200 OK + data     │
  │◄──────────────────────────├◄─────────────────────────┤
```

### 4.3 JWT Filter trong Gateway

```java
@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    private static final List<String> PUBLIC_PATHS = List.of(
        "/api/v1/auth/login",
        "/api/v1/auth/refresh"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        
        // Cho phép public paths không cần token
        if (PUBLIC_PATHS.stream().anyMatch(path::startsWith)) {
            return chain.filter(exchange);
        }
        
        // Kiểm tra JWT token
        String authHeader = exchange.getRequest()
            .getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        
        // Validate token và forward request
        String token = authHeader.substring(7);
        if (jwtService.isTokenValid(token)) {
            return chain.filter(exchange);
        }
        
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
    
    @Override
    public int getOrder() { return -1; } // Chạy trước các filter khác
}
```

---

## 5. SPRING CLOUD CONFIG

### 5.1 Vấn đề khi không có Config Server

Khi có nhiều microservice, mỗi service có file `application.yml` riêng:
- Thay đổi DB password → phải vào từng service sửa → restart từng service
- Khó quản lý cấu hình theo môi trường (dev/staging/prod)
- Config bị phân tán, khó theo dõi lịch sử thay đổi

### 5.2 Giải pháp: Spring Cloud Config

```
Git Repository (config-repo/)        Config Server        Microservices
├── application.yml (chung)         ─────────────────►   user-service
├── user-service.yml                     (8888)           product-service
├── product-service.yml                                   master-data-service
└── master-data-service.yml
```

**Config Server setup:**

```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

```java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

```yaml
# config-server/application.yml
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: file:///D:/config-repo    # Local git repo (hoặc GitHub URL)
          default-label: main
```

**Client sử dụng Config Server:**

```yaml
# bootstrap.yml (đọc trước application.yml)
spring:
  application:
    name: user-service
  cloud:
    config:
      uri: http://localhost:8888
      fail-fast: true
```

---

## 6. CÁC BACKEND MICROSERVICE

### 6.1 User Service (port 8081)

**Trách nhiệm:** Quản lý users, authentication, authorization  
**Bảng DB:** users, roles, user_roles, tokens

```
user-service/
├── controller/
│   ├── AuthController.java     (POST /api/v1/auth/login, /refresh)
│   └── UserController.java     (GET/POST/PUT/DELETE /api/users/**)
├── service/
│   ├── AuthService.java
│   └── UserService.java
├── entity/
│   ├── User.java
│   ├── Role.java
│   └── Token.java
├── security/
│   ├── JwtService.java
│   ├── SecurityConfig.java
│   └── JwtAuthFilter.java
└── repository/
    ├── UserRepository.java
    └── TokenRepository.java
```

**Key API endpoints:**
```
POST /api/v1/auth/login       → Đăng nhập, trả về JWT
POST /api/v1/auth/refresh     → Refresh access token
GET  /api/users               → Danh sách users (phân trang)
POST /api/users               → Tạo user mới
PUT  /api/users/{id}          → Cập nhật user
DELETE /api/users/{id}        → Xóa user
```

### 6.2 Product Service (port 8082)

**Trách nhiệm:** Quản lý sản phẩm, SKU, images  
**Bảng DB:** products, product_skus, product_images

```
product-service/
├── controller/
│   └── ProductController.java
├── service/
│   ├── ProductService.java
│   └── impl/ProductServiceImpl.java
├── entity/
│   ├── Product.java
│   ├── ProductSku.java
│   └── ProductImage.java
├── dto/
│   ├── ProductRequestDto.java
│   └── ProductResponseDto.java
└── repository/
    └── ProductRepository.java
```

**Gọi Master Data Service để lấy Colors/Sizes:**

```java
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final RestTemplate restTemplate;   // @LoadBalanced
    
    public List<ColorDto> getAvailableColors() {
        // Gọi sang master-data-service qua Eureka
        return restTemplate.getForObject(
            "http://MASTER-DATA-SERVICE/api/colors",
            List.class
        );
    }
}
```

### 6.3 Master Data Service (port 8083)

**Trách nhiệm:** Quản lý dữ liệu danh mục (ít thay đổi)  
**Bảng DB:** categories, colors, sizes

```
Key API endpoints:
GET  /api/categories          → Danh sách categories (tree)
POST /api/categories          → Tạo category
GET  /api/colors              → Danh sách màu sắc
POST /api/colors              → Thêm màu
GET  /api/sizes               → Danh sách kích cỡ
POST /api/sizes               → Thêm kích cỡ
```

### 6.4 Order Service (port 8084)

**Trách nhiệm:** Giỏ hàng, đặt hàng, reviews  
**Bảng DB:** cart_items, reviews, visit_stats

```
Key API endpoints:
GET  /api/cart/{userId}       → Giỏ hàng của user
POST /api/cart                → Thêm vào giỏ
DELETE /api/cart/{itemId}     → Xóa khỏi giỏ
POST /api/orders              → Đặt hàng
GET  /api/orders/{userId}     → Lịch sử đặt hàng
```

---

## 7. FRONTEND REACTJS

### 7.1 Cấu trúc React App

```
frontend/
├── public/
├── src/
│   ├── api/
│   │   ├── axiosConfig.js      # Cấu hình Axios + interceptors
│   │   ├── authApi.js          # Auth API calls
│   │   ├── productApi.js       # Product API calls
│   │   ├── userApi.js          # User API calls
│   │   └── masterDataApi.js    # Category/Color/Size APIs
│   ├── components/
│   │   ├── common/
│   │   │   ├── Navbar.jsx
│   │   │   ├── Sidebar.jsx
│   │   │   └── Table.jsx
│   │   ├── product/
│   │   │   ├── ProductList.jsx
│   │   │   └── ProductForm.jsx
│   │   └── user/
│   │       ├── UserList.jsx
│   │       └── UserForm.jsx
│   ├── pages/
│   │   ├── LoginPage.jsx
│   │   ├── DashboardPage.jsx
│   │   ├── ProductPage.jsx
│   │   └── UserPage.jsx
│   ├── hooks/
│   │   └── useAuth.js
│   ├── context/
│   │   └── AuthContext.jsx
│   └── App.jsx
└── package.json
```

### 7.2 Axios Configuration với JWT

```javascript
// src/api/axiosConfig.js
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',  // Gateway URL
  timeout: 10000,
});

// Request interceptor – tự động đính token
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('access_token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Response interceptor – tự động refresh token khi 401
api.interceptors.response.use(
  (response) => response,
  async (error) => {
    if (error.response?.status === 401) {
      try {
        const refreshToken = localStorage.getItem('refresh_token');
        const res = await axios.post('/api/v1/auth/refresh', {}, {
          headers: { Authorization: `Bearer ${refreshToken}` }
        });
        localStorage.setItem('access_token', res.data.access_token);
        // Retry request gốc
        error.config.headers.Authorization = `Bearer ${res.data.access_token}`;
        return axios(error.config);
      } catch {
        localStorage.clear();
        window.location.href = '/login';
      }
    }
    return Promise.reject(error);
  }
);

export default api;
```

---

## 8. GIAO TIẾP GIỮA SERVICES

### 8.1 @LoadBalanced RestTemplate

Khi một service cần gọi sang service khác, ta dùng `RestTemplate` với annotation `@LoadBalanced`:

```java
// Cấu hình Bean trong mỗi service cần gọi service khác
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced   // Quan trọng! Cho phép dùng tên service thay vì IP:Port
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

**Cách sử dụng:**

```java
@Service
@RequiredArgsConstructor
public class OrderServiceImpl {
    
    private final RestTemplate restTemplate;
    
    public CartItemResponseDto addToCart(CartItemRequest request) {
        // Kiểm tra user tồn tại – gọi sang USER-SERVICE
        UserDto user = restTemplate.getForObject(
            "http://USER-SERVICE/api/users/" + request.getUserId(),
            UserDto.class
        );
        
        // Kiểm tra SKU tồn tại – gọi sang PRODUCT-SERVICE
        SkuDto sku = restTemplate.getForObject(
            "http://PRODUCT-SERVICE/api/products/skus/" + request.getSkuId(),
            SkuDto.class
        );
        
        // Tạo cart item...
    }
}
```

### 8.2 Luồng giao tiếp đầy đủ

```
React App (3000)
    │
    │ GET /api/products?page=0
    ▼
API Gateway (8080)
    │ Path: /api/products/** → lb://PRODUCT-SERVICE
    │ Load Balancer hỏi Eureka: PRODUCT-SERVICE ở đâu?
    │ Eureka: 127.0.0.1:8082
    ▼
Product Service (8082)
    │ Xử lý query, cần lấy category info
    │ restTemplate.getForObject("http://MASTER-DATA-SERVICE/api/categories/5")
    ▼
Master Data Service (8083)
    │ Trả về category data
    ▼
Product Service (8082)
    │ Combine data, build response
    ▼
API Gateway (8080)
    ▼
React App (3000) ← JSON response
```

---

## 9. DEMO HỆ THỐNG

### 9.1 Thứ tự khởi động

```bash
# 1. Khởi động Config Server (nếu dùng)
cd config-server && mvn spring-boot:run

# 2. Khởi động Eureka Gateway
cd eureka-gateway && mvn spring-boot:run

# 3. Khởi động các backend services (có thể song song)
cd user-service && mvn spring-boot:run
cd product-service && mvn spring-boot:run
cd master-data-service && mvn spring-boot:run
cd order-service && mvn spring-boot:run

# 4. Khởi động Frontend
cd frontend && npm install && npm start
```

### 9.2 Kiểm tra Eureka Dashboard

Truy cập: http://localhost:8080/eureka

Bạn sẽ thấy tất cả services đã đăng ký:
```
Application         AMIs    Availability Zones    Status
USER-SERVICE        n/a     (1)                   UP (1) - localhost:8081
PRODUCT-SERVICE     n/a     (1)                   UP (1) - localhost:8082
MASTER-DATA-SERVICE n/a     (1)                   UP (1) - localhost:8083
ORDER-SERVICE       n/a     (1)                   UP (1) - localhost:8084
```

### 9.3 Test API qua Gateway

```bash
# Đăng nhập
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@uniqlo.com","password":"admin123"}'

# Lấy danh sách sản phẩm (qua gateway → product-service)
curl http://localhost:8080/api/products \
  -H "Authorization: Bearer <your_token>"

# Lấy categories (qua gateway → master-data-service)
curl http://localhost:8080/api/categories \
  -H "Authorization: Bearer <your_token>"
```

---

## SO SÁNH MONOLITHIC vs MICROSERVICE – TỔNG KẾT

```
                MONOLITHIC                   MICROSERVICE
               ────────────                 ────────────────
Deploy:        Toàn bộ app cùng lúc         Từng service độc lập
Scale:         Scale toàn bộ                Scale từng service
Database:      1 DB chung                   Mỗi service có thể có DB riêng
Team:          1 team làm tất cả            Team theo domain
Complexity:    Đơn giản khi nhỏ             Phức tạp hơn nhưng flexible
Debug:         Dễ debug local               Cần distributed tracing
Phù hợp:      Startup, MVP                 Enterprise, scale lớn
```

> **Lưu ý thực tế:** Trong bài học này ta dùng **1 database dùng chung** cho tất cả services (Shared Database Pattern) để đơn giản hóa việc học. Trong production thực tế, mỗi service nên có database riêng.

---

*Bài giảng được xây dựng dựa trên dự án Uniqlo Education – T3H*
