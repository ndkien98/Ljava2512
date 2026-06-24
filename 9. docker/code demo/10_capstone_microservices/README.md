# Module 10: Capstone Project - Triển khai hệ thống Microservices Uniqlo

Capstone Project này cung cấp toàn bộ cơ sở hạ tầng Docker để vận hành hệ thống Uniqlo Microservice gồm 9 containers phối hợp hoạt động.

Tất cả các cấu hình gốc đã được triển khai trực tiếp tại thư mục dự án: `d:\t3h\java2512\core\java2512\8. microservice\source_microservice`.

## Cấu trúc hạ tầng đã tạo

1. **Dockerfiles**:
   - `config-server/Dockerfile` (Port 19088)
   - `eureka-server/Dockerfile` (Port 19089)
   - `eureka-gateway/Dockerfile` (Port 8080)
   - `user-service/Dockerfile` (Port 8081)
   - `product-service/Dockerfile` (Port 8082)
   - `master-data-service/Dockerfile` (Port 8083)
   - `order-service/Dockerfile` (Port 8084)
   - `frontend/Dockerfile` (Port 3000)
2. **Nginx Reverse Proxy**:
   - `frontend/nginx.conf`: Phân phối file tĩnh React ở cổng 3000 và chuyển tiếp các API call `/api/**` sang API Gateway.
3. **Orchestrator**:
   - `docker-compose.yml`: Gom toàn bộ 8 services trên kèm 1 container `mysql-db` (MySQL 8) và 1 container `redis-db` (Redis 7), liên kết mạng ảo bridge `uniqlo-network`.
   - `.env`: Chứa thông tin cấu hình DB Password và JWT Secret.

---

## Hướng dẫn triển khai và vận hành hệ thống

### Bước 1: Di chuyển vào thư mục dự án chứa compose
```bash
# LƯU Ý: Chạy lệnh này trên terminal của bạn
cd "d:\t3h\java2512\core\java2512\8. microservice\source_microservice"
```

### Bước 2: Build các image và chạy toàn bộ hệ thống
```bash
docker-compose up -d --build
```
*Giải thích*:
- `--build`: Ép Docker build lại Dockerfile của các module (Spring Boot và React) để đảm bảo cập nhật code mới nhất từ local.
- `-d`: Khởi chạy ở chế độ chạy ngầm (detached mode).

### Bước 3: Kiểm tra trạng thái hệ thống
1. **Kiểm tra trạng thái container**:
   ```bash
   docker-compose ps
   ```
   *Yêu cầu*: Tất cả các container phải ở trạng thái `running` (Up) và các DB ở trạng thái `healthy`.
2. **Kiểm tra Eureka Dashboard**:
   - Truy cập: `http://localhost:19089` trên trình duyệt.
   - *Yêu cầu*: Thấy đầy đủ 5 services đăng ký thành công: `USER-SERVICE`, `PRODUCT-SERVICE`, `MASTER-DATA-SERVICE`, `ORDER-SERVICE`, và `EUREKA-GATEWAY`.
3. **Kiểm tra ứng dụng React Frontend**:
   - Truy cập: `http://localhost:3000`
   - *Yêu cầu*: Giao diện web Uniqlo hiển thị, gọi được API đăng nhập/đăng ký thông qua API Gateway (được proxy tự động bởi Nginx).

### Bước 4: Tắt hệ thống
Khi muốn giải phóng tài nguyên máy tính:
```bash
docker-compose down
```
Nếu muốn xóa sạch cơ sở dữ liệu để import lại dữ liệu từ đầu:
```bash
docker-compose down -v
```
