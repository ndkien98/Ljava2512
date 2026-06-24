# Module 6: Docker Compose - Vận hành hệ thống Multi-container

Demo này hướng dẫn cách sử dụng Docker Compose để khởi chạy một hệ sinh thái Web hoàn chỉnh bao gồm: React Frontend (Nginx), Spring Boot API, MySQL Database, và Redis Cache chỉ bằng một câu lệnh duy nhất.

## Các tệp cấu hình trong Demo

1. **`docker-compose.yml`**: Định nghĩa 4 dịch vụ (`mysql-db`, `redis-cache`, `backend-api`, `frontend-web`), kết nối chúng vào chung một bridge network (`app-network`), cấu hình volumes cho cơ sở dữ liệu và thiết lập kiểm tra sức khỏe (`healthcheck`) cho database để đảm bảo ứng dụng backend chỉ khởi chạy khi database đã sẵn sàng nhận kết nối.
2. **`.env`**: Lưu trữ biến môi trường bảo mật (mật khẩu database).

## Các lệnh vận hành hệ thống

1. **Khởi chạy toàn bộ hệ thống dưới nền**:
   ```bash
   docker-compose up -d
   ```

2. **Xem danh sách các container đang chạy và trạng thái sức khỏe**:
   ```bash
   docker-compose ps
   ```

3. **Xem Logs đồng thời của toàn bộ hệ thống**:
   ```bash
   docker-compose logs -f
   ```

4. **Dừng và xóa sạch hệ thống (bao gồm cả network nội bộ)**:
   ```bash
   docker-compose down
   ```
   *Lưu ý*: Dữ liệu trong database và cache vẫn được giữ lại nhờ cấu hình volume `mysql_data` và `redis_data`. Nếu muốn xóa sạch cả volume dữ liệu, dùng:
   ```bash
   docker-compose down -v
   ```
