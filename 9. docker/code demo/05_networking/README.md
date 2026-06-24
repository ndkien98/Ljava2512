# Module 5: Docker Networking & DNS Resolution

Demo này hướng dẫn cách kết nối các container với nhau qua mạng ảo tùy biến (Custom Bridge Network) để các container có thể tự gọi nhau bằng Tên Container (DNS resolution) thay vì IP động.

## Các bước thực hành chi tiết

1. **Tạo mạng Custom Bridge**:
   ```bash
   docker network create uniqlo-net
   ```
   *Kiểm tra danh sách mạng*:
   ```bash
   docker network ls
   ```

2. **Chạy container cơ sở dữ liệu MySQL gắn vào mạng `uniqlo-net`**:
   ```bash
   docker run -d --name mysql-db-net \
     --network uniqlo-net \
     -e MYSQL_ROOT_PASSWORD=root \
     -e MYSQL_DATABASE=uniqlo_education \
     mysql:8.0
   ```
   *Lưu ý*: Ta không cần map port `-p 3306:3306` ra máy host nếu chỉ muốn các container nội bộ kết nối với nhau. Ở đây ta gắn `--network uniqlo-net` và đặt tên `--name mysql-db-net`.

3. **Chạy một container Spring Boot hoặc kiểm thử kết nối mạng**:
   Ta chạy một container phụ (như `alpine`) cùng mạng để kiểm tra xem có ping được sang `mysql-db-net` bằng tên không:
   ```bash
   docker run -it --rm --network uniqlo-net alpine sh
   ```
   *Bên trong container alpine, chạy lệnh*:
   ```bash
   # Cài đặt công cụ ping và nslookup
   apk add --no-cache bind-tools
   
   # Truy vấn DNS để tìm IP của mysql-db-net
   nslookup mysql-db-net
   
   # Ping thử tới container mysql-db-net
   ping -c 3 mysql-db-net
   
   exit
   ```
   *Kết quả*: Bạn sẽ thấy DNS của Docker tự động phân giải tên `mysql-db-net` thành IP nội bộ (ví dụ: `172.20.0.2`).

4. **Kết nối ứng dụng Java Spring Boot vào Database**:
   Khi cấu hình ứng dụng Spring Boot chạy trong cùng mạng `uniqlo-net`, ta truyền chuỗi kết nối sử dụng tên host của db container:
   ```properties
   spring.datasource.url=jdbc:mysql://mysql-db-net:3306/uniqlo_education
   ```
   Lệnh chạy:
   ```bash
   docker run -d --name user-service --network uniqlo-net -p 8081:8081 my-user-service:latest
   ```
