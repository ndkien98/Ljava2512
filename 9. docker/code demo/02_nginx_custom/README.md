# Module 2: Quản lý Images & Containers - Lab Nginx Custom

Demo này hướng dẫn cách khởi chạy máy chủ Nginx, ánh xạ cổng, truy cập terminal bên trong container và xem logs.

## Các lệnh thực hành từng bước

1. **Khởi chạy container Nginx dưới nền (cổng 8080)**:
   ```bash
   docker run -d -p 8080:80 --name web-nginx nginx
   ```
   *Giải thích*:
   - `-d`: Chạy ngầm.
   - `-p 8080:80`: Ánh xạ cổng 8080 của máy host vào cổng 80 (cổng mặc định của Nginx) trong container.
   - `--name web-nginx`: Đặt tên container là `web-nginx`.

2. **Kiểm tra trạng thái container**:
   ```bash
   docker ps
   ```

3. **Xem Logs của container**:
   ```bash
   docker logs web-nginx
   # Theo dõi logs real-time
   docker logs -f web-nginx
   ```

4. **Truy cập vào shell bên trong container**:
   ```bash
   docker exec -it web-nginx /bin/bash
   ```
   *Khi đã ở bên trong shell của container, bạn có thể xem các tiến trình hoặc file của Nginx*:
   ```bash
   cat /usr/share/nginx/html/index.html
   exit
   ```

5. **Thay đổi file index mặc định bằng Bind Mount**:
   Dừng và xóa container cũ:
   ```bash
   docker stop web-nginx
   docker rm web-nginx
   ```
   Khởi chạy container mới kết nối thư mục `html` hiện tại vào thư mục của Nginx (Lưu ý: Thay đổi đường dẫn tuyệt đối chính xác trên máy của bạn):
   ```bash
   # Trong cmd/powershell tại thư mục '02_nginx_custom':
   docker run -d -p 8080:80 --name web-nginx-custom -v "%CD%/html:/usr/share/nginx/html" nginx
   ```
   *Kết quả*: Truy cập `http://localhost:8080` trên trình duyệt để thấy trang web tùy biến đã được hiển thị.

6. **Dọn dẹp**:
   ```bash
   docker stop web-nginx-custom
   docker rm web-nginx-custom
   ```
