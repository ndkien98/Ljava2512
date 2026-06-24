# Module 6: Docker Compose - Vận hành hệ thống Multi-container Monolithic

Thư mục này hướng dẫn sử dụng Docker Compose để khởi chạy toàn bộ ngăn xếp dịch vụ (stack) cho dự án monolithic `uniqlo-service-v1` bao gồm: **MySQL**, **Redis**, **Spring Boot App**, và **Nginx** làm cổng reverse proxy.

## Các thành phần cấu hình

1. **`docker-compose.yml`**: Gom 4 dịch vụ, liên kết qua mạng ảo bridge `mono-network`.
2. **`.env`**: Lưu trữ các tham số cấu hình bảo mật cục bộ.
3. **`nginx.conf`**: Cấu hình Nginx chuyển tiếp tất cả request từ cổng 80 vào cổng 8080 của ứng dụng Spring Boot.
4. **Volume mount tự động**: Khi `mysql-db` được tạo lần đầu, nó sẽ tự động chạy tệp `database.sql` của dự án để tạo bảng và dữ liệu mẫu.

## Hướng dẫn chạy thử từng bước

### Bước 1: Build file JAR ở dự án
Trước khi khởi chạy, bạn cần đảm bảo đã build file JAR của dự án `uniqlo-service-v1` tại thư mục của nó:
```bash
cd "../../../8. microservice/source_monolithic/uniqlo-service-v1"
mvn clean package -DskipTests
```

### Bước 2: Khởi chạy docker-compose
Quay trở lại thư mục `06_docker_compose_stack` và chạy lệnh:
```bash
docker-compose up -d
```

### Bước 3: Kiểm tra trạng thái
```bash
# Xem các container đang chạy
docker-compose ps

# Xem log khởi chạy của ứng dụng Spring Boot
docker-compose logs -f uniqlo-service
```

### Bước 4: Truy cập ứng dụng qua Nginx
Bây giờ, thay vì truy cập cổng `8080` trực tiếp, bạn truy cập cổng `80` (cổng mặc định) qua trình duyệt: `http://localhost/` hoặc `http://localhost/swagger-ui/index.html`. Nginx sẽ đứng ra đón nhận request và điều hướng mượt mà vào container Spring Boot.

### Bước 5: Tắt hệ thống
```bash
docker-compose down
```
Nếu muốn xóa sạch toàn bộ dữ liệu database lưu trữ trong volume để khởi tạo lại:
```bash
docker-compose down -v
```
