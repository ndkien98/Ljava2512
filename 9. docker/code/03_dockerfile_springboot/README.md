# Module 3: Dockerfile - Đóng gói ứng dụng Spring Boot Monolithic

Thư mục này chứa cấu hình Dockerfile mẫu để đóng gói ứng dụng `uniqlo-service-v1` thành một Docker Image chạy độc lập.

## Hướng dẫn từng bước cách đóng gói ứng dụng

### Bước 1: Build file JAR từ máy host
Trước khi build Docker Image, bạn cần biên dịch mã nguồn của dự án Monolithic `uniqlo-service-v1` thành tệp tin JAR.
Mở terminal tại thư mục dự án `uniqlo-service-v1` (`D:\t3h\java2512\core\java2512\8. microservice\source_monolithic\uniqlo-service-v1`):
```bash
mvn clean package -DskipTests
```
*Kết quả*: File `uniqlo-service-0.0.1-SNAPSHOT.jar` sẽ được tạo ra tại thư mục `target/`.

### Bước 2: Sao chép Dockerfile sang dự án hoặc chạy build chỉ định context
Sao chép tệp `Dockerfile` từ thư mục này vào gốc của dự án `uniqlo-service-v1` hoặc chạy lệnh sau trực tiếp từ thư mục `uniqlo-service-v1`:
```bash
# Đứng tại thư mục uniqlo-service-v1, dùng Dockerfile từ lab này để build:
docker build -f ../../../9.\ docker/code/03_dockerfile_springboot/Dockerfile -t uniqlo-service-mono:v1 .
```
*(Nếu bạn dùng Windows Command Prompt/PowerShell, hãy điều chỉnh đường dẫn tương ứng: `docker build -f ..\..\..\9. docker\code\03_dockerfile_springboot\Dockerfile -t uniqlo-service-mono:v1 .`)*

### Bước 3: Chạy container từ Image vừa build
Khởi chạy container ánh xạ cổng 8080:
```bash
docker run -d -p 8080:8080 --name my-uniqlo-app uniqlo-service-mono:v1
```

### Bước 4: Kiểm tra trạng thái
Kiểm tra logs xem Spring Boot đã boot thành công chưa:
```bash
docker logs -f my-uniqlo-app
```
*Lưu ý*: Ứng dụng có thể log ra lỗi kết nối Database do ta chưa thiết lập và khởi chạy MySQL container (sẽ học tiếp ở các Module sau).
Để dọn dẹp container này:
```bash
docker stop my-uniqlo-app
docker rm my-uniqlo-app
```
