# Module 5: Docker Networking & DNS Resolution

Bài lab này hướng dẫn cách kết nối các container với nhau qua mạng ảo tùy biến (Custom Bridge Network) để các container có thể giao tiếp với nhau bằng **Tên Container (Container Name)** thay vì IP động thường xuyên thay đổi.

## Các bước thực hành chi tiết

1. **Tạo mạng Custom Bridge Network mới**:
   ```bash
   docker network create uniqlo-net
   ```
   *Kiểm tra danh sách mạng*:
   ```bash
   docker network ls
   ```

2. **Chạy container Database MySQL trong mạng `uniqlo-net`**:
   ```bash
   docker run -d --name mysql-db-net \
     --network uniqlo-net \
     -e MYSQL_ROOT_PASSWORD=root \
     -e MYSQL_DATABASE=uniqlo_education \
     mysql:8.0
   ```
   *Lưu ý*: Ta không cần phải ánh xạ cổng `-p 3306:3306` ra máy host nếu chỉ muốn ứng dụng Spring Boot kết nối nội bộ đến Database từ bên trong mạng Docker này.

3. **Chạy thử container kiểm thử (alpine) để kiểm tra DNS**:
   ```bash
   docker run -it --rm --network uniqlo-net alpine sh
   ```
   *Bên trong shell của container alpine vừa mở, hãy thực thi*:
   ```bash
   # Cài đặt công cụ mạng
   apk add --no-cache bind-tools
   
   # Phân giải tên miền để tìm IP của database container
   nslookup mysql-db-net
   
   # Ping kiểm tra kết nối
   ping -c 3 mysql-db-net
   
   exit
   ```
   *Kết quả*: Docker Engine có DNS nội bộ tích hợp, tự động dịch tên `mysql-db-net` thành IP nội bộ (ví dụ: `172.18.0.2`).

4. **Kết nối ứng dụng Java Spring Boot đến Database**:
   Để ứng dụng Spring Boot kết nối được tới Database, ta chỉ cần truyền địa chỉ URL kết nối sử dụng tên container là `mysql-db-net`:
   ```bash
   # Build ứng dụng ở máy host trước (ở thư mục uniqlo-service-v1)
   mvn clean package -DskipTests
   
   # Chạy app Spring boot cùng mạng 'uniqlo-net'
   docker run -d --name uniqlo-service-net \
     --network uniqlo-net \
     -p 8080:8080 \
     -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-db-net:3306/uniqlo_education?useUnicode=true&characterEncoding=UTF-8 \
     -e SPRING_DATASOURCE_USERNAME=root \
     -e SPRING_DATASOURCE_PASSWORD=root \
     uniqlo-service-mono:v1
   ```
   *Kiểm tra kết nối*:
   ```bash
   docker logs -f uniqlo-service-net
   ```
   Lúc này, Spring Boot sẽ khởi chạy thành công và kết nối mượt mà tới Database mà không bị lỗi kết nối!

5. **Dọn dẹp**:
   ```bash
   docker stop uniqlo-service-net mysql-db-net
   docker rm uniqlo-service-net mysql-db-net
   docker network rm uniqlo-net
   ```
