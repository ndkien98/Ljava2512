# Module 4: Lưu trữ dữ liệu (Persistent Storage) với MySQL & Redis

Demo này hướng dẫn cách cấu hình lưu trữ bền vững để tránh việc mất dữ liệu khi container bị xóa, áp dụng cho MySQL và Redis.

## Thực hành 1: MySQL Persistence với Docker Volume

1. **Tạo một volume độc lập**:
   ```bash
   docker volume create uniqlo_mysql_data
   ```

2. **Khởi chạy container MySQL gắn volume vừa tạo**:
   ```bash
   docker run -d --name mysql-db-lab \
     -p 3306:3306 \
     -e MYSQL_ROOT_PASSWORD=root \
     -e MYSQL_DATABASE=uniqlo_education \
     -v uniqlo_mysql_data:/var/lib/mysql \
     mysql:8.0
   ```
   *Giải thích*: Thư mục `/var/lib/mysql` trong container MySQL là nơi lưu trữ toàn bộ database. Việc mount volume `uniqlo_mysql_data` vào đây giúp đồng bộ toàn bộ file dữ liệu ra ổ đĩa máy host.

3. **Kiểm tra tính bền vững**:
   - Sử dụng một công cụ quản trị Database (như DBeaver, Navicat) kết nối tới `localhost:3306` (user: `root`, pass: `root`).
   - Tạo thử một bảng `test_table` và chèn dữ liệu.
   - Xóa container hiện tại:
     ```bash
     docker stop mysql-db-lab
     docker rm mysql-db-lab
     ```
   - Tạo lại container mới và gắn lại volume cũ:
     ```bash
     docker run -d --name mysql-db-lab-new \
       -p 3306:3306 \
       -e MYSQL_ROOT_PASSWORD=root \
       -e MYSQL_DATABASE=uniqlo_education \
       -v uniqlo_mysql_data:/var/lib/mysql \
       mysql:8.0
     ```
   - Kết nối lại database: Bạn sẽ thấy bảng `test_table` và toàn bộ dữ liệu vẫn còn nguyên vẹn!

---

## Thực hành 2: Redis Persistence với Docker Volume

Tương tự MySQL, Redis lưu trữ cache trong RAM nhưng cũng ghi log xuống đĩa (AOF/RDB). Ta bảo toàn dữ liệu bằng cách mount thư mục `/data` của Redis.

1. **Tạo volume cho Redis**:
   ```bash
   docker volume create uniqlo_redis_data
   ```

2. **Chạy container Redis**:
   ```bash
   docker run -d --name redis-cache-lab \
     -p 6379:6379 \
     -v uniqlo_redis_data:/data \
     redis:7-alpine redis-server --appendonly yes
   ```
   *Giải thích*: Tham số `--appendonly yes` kích hoạt chế độ lưu trữ bền vững AOF (Append Only File) của Redis vào thư mục `/data`.
