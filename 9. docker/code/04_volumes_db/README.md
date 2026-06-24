# Module 4: Lưu trữ dữ liệu bền vững (Persistent Storage) với MySQL & Redis

Bài lab này hướng dẫn cách bảo vệ dữ liệu cơ sở dữ liệu (MySQL) và bộ nhớ đệm (Redis) không bị mất khi container bị tắt hoặc xóa bằng cơ chế **Docker Volumes**.

## Phần 1: MySQL Persistence với Docker Volume

1. **Tạo Docker Volume riêng cho MySQL**:
   ```bash
   docker volume create uniqlo_mysql_data
   ```

2. **Chạy MySQL container và mount volume vừa tạo**:
   ```bash
   docker run -d --name mysql-db-volume \
     -p 3306:3306 \
     -e MYSQL_ROOT_PASSWORD=root \
     -e MYSQL_DATABASE=uniqlo_education \
     -v uniqlo_mysql_data:/var/lib/mysql \
     mysql:8.0
   ```
   *Giải thích*:
   - Thư mục `/var/lib/mysql` trong container là nơi lưu trữ toàn bộ cơ sở dữ liệu.
   - `-v uniqlo_mysql_data:/var/lib/mysql`: Gắn volume `uniqlo_mysql_data` vào thư mục này để lưu trữ trực tiếp trên đĩa của máy host, được Docker quản lý độc lập.

3. **Kiểm tra tính bền vững**:
   - Sử dụng một client Database (như DBeaver hoặc Workbench) kết nối với MySQL (`localhost:3306`, user `root`, pass `root`).
   - Tạo một bảng thử nghiệm và chèn dữ liệu.
   - Xóa container hiện tại đi:
     ```bash
     docker stop mysql-db-volume
     docker rm mysql-db-volume
     ```
   - Chạy lại container mới và mount lại volume cũ:
     ```bash
     docker run -d --name mysql-db-volume-new \
       -p 3306:3306 \
       -e MYSQL_ROOT_PASSWORD=root \
       -e MYSQL_DATABASE=uniqlo_education \
       -v uniqlo_mysql_data:/var/lib/mysql \
       mysql:8.0
     ```
   - Kết nối lại database và bạn sẽ thấy toàn bộ dữ liệu vẫn còn nguyên vẹn!

---

## Phần 2: Redis Cache Persistence với Docker Volume

Redis mặc định lưu dữ liệu trên RAM để cho tốc độ truy cập cực nhanh. Tuy nhiên, ta có thể bật chế độ ghi log xuống đĩa (AOF) và dùng Docker Volume để lưu trữ bền vững.

1. **Tạo Volume cho Redis**:
   ```bash
   docker volume create uniqlo_redis_data
   ```

2. **Chạy Redis container và kích hoạt chế độ AppendOnly (AOF)**:
   ```bash
   docker run -d --name redis-cache-volume \
     -p 6379:6379 \
     -v uniqlo_redis_data:/data \
     redis:7-alpine redis-server --appendonly yes
   ```
   *Giải thích*:
   - `--appendonly yes`: Yêu cầu Redis ghi log mỗi thay đổi dữ liệu xuống thư mục `/data`.
   - `-v uniqlo_redis_data:/data`: Mount volume để lưu trữ bền vững thư mục `/data` ra đĩa máy host.

## Dọn dẹp
```bash
docker stop mysql-db-volume-new redis-cache-volume
docker rm mysql-db-volume-new redis-cache-volume
```
