# Module 10: Capstone Project - Triển khai hệ thống Microservices Uniqlo lên Docker

Thư mục này đóng vai trò hướng dẫn tổng hợp, kết nối toàn bộ kiến thức đã học vào dự án Microservices Uniqlo thực tế gồm 10 dịch vụ container chạy đồng bộ.

Mã nguồn và toàn bộ tệp cấu hình đã được thiết lập trực tiếp tại thư mục dự án microservice của bạn:
[Thư mục nguồn Microservice](file:///D:/t3h/java2512/core/java2512/8.%20microservice/source_microservice)

---

## 1. Cấu trúc các thành phần trong dự án Microservices
Hệ thống microservices của Uniqlo bao gồm:
1. **`mysql-db` (Port 3306)**: Lưu trữ dữ liệu. Tự động chạy file `database.sql` để import dữ liệu mẫu khi khởi động lần đầu.
2. **`redis-db` (Port 6379)**: Cung cấp cache.
3. **`config-server` (Port 19088)**: Quản lý cấu hình tập trung lưu ở `config-repo`.
4. **`eureka-server` (Port 19089)**: Quản lý định danh đăng ký của các service (Service Registry).
5. **`eureka-gateway` (Port 8080)**: API Gateway duy nhất chuyển tiếp request tới các service.
6. **`user-service` (Port 8081)**: Quản lý người dùng, đăng ký, đăng nhập và JWT.
7. **`product-service` (Port 8082)**: Quản lý sản phẩm.
8. **`master-data-service` (Port 8083)**: Danh mục kích cỡ, màu sắc.
9. **`order-service` (Port 8084)**: Giỏ hàng và đặt hàng.
10. **`frontend` (Port 3000)**: React Frontend được phục vụ tĩnh qua Nginx.

---

## 2. Hướng dẫn vận hành nhanh bằng Docker Compose

### Bước 1: Di chuyển vào thư mục dự án chứa compose
```bash
cd "D:\t3h\java2512\core\java2512\8. microservice\source_microservice"
```

### Bước 2: Khởi chạy toàn bộ hệ thống
```bash
docker-compose up -d --build
```
*Lưu ý*:
- Tham số `--build` giúp Docker tự động build lại toàn bộ các Dockerfile của các dịch vụ Java và React Frontend tại local để luôn chạy bản cập nhật code mới nhất.
- Khi database khởi chạy lần đầu tiên, file SQL `database.sql` sẽ tự động được import vào MySQL giúp hệ thống sẵn sàng hoạt động ngay lập tức mà không cần import tay.

### Bước 3: Kiểm tra trạng thái hệ thống
1. **Kiểm tra danh sách container**:
   ```bash
   docker-compose ps
   ```
2. **Kiểm tra giao diện quản trị Eureka Server**:
   Mở trình duyệt: `http://localhost:19089`
   *Yêu cầu*: Thấy đầy đủ 5 services đăng ký UP thành công bao gồm `USER-SERVICE`, `PRODUCT-SERVICE`, `MASTER-DATA-SERVICE`, `ORDER-SERVICE`, và `EUREKA-GATEWAY`.
3. **Mở giao diện Web Uniqlo**:
   Mở trình duyệt: `http://localhost:3000`
   *Yêu cầu*: Thấy giao diện hiển thị, đăng nhập/đăng ký thành công (thông qua chuyển tiếp API Gateway).

### Bước 4: Tắt hệ thống
```bash
docker-compose down
```
Nếu muốn xóa sạch cơ sở dữ liệu để khởi chạy lại từ đầu:
```bash
docker-compose down -v
```
