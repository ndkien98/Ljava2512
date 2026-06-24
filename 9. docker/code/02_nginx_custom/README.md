# Module 2: Quản lý Images & Containers - Nginx Custom Lab

Demo này hướng dẫn cách chạy web server Nginx, cấu hình port mapping và sử dụng cơ chế Bind Mount để đưa file HTML từ máy host vào container phục vụ nhanh mà không cần build lại image.

## Các lệnh thực hành từng bước

1. **Khởi chạy container Nginx mặc định dưới nền (port 8080)**:
   ```bash
   docker run -d -p 8080:80 --name my-nginx nginx:alpine
   ```
   *Giải thích*:
   - `-d`: Chạy ngầm (detach).
   - `-p 8080:80`: Ánh xạ cổng 8080 của máy host vào cổng 80 (cổng web mặc định của Nginx) trong container.
   - `--name my-nginx`: Đặt tên dễ nhớ cho container.

2. **Kiểm tra logs của Nginx**:
   ```bash
   docker logs my-nginx
   # Xem log real-time
   docker logs -f my-nginx
   ```

3. **Truy cập terminal bên trong container**:
   ```bash
   docker exec -it my-nginx sh
   ```
   *Khi đã ở trong container*:
   ```bash
   ls -la /usr/share/nginx/html
   exit
   ```

4. **Sử dụng Bind Mount để tải trang web tùy biến**:
   Dừng và xóa container cũ:
   ```bash
   docker stop my-nginx
   docker rm my-nginx
   ```
    Chạy container mới và mount thư mục `html` cùng cấu hình `nginx.conf`. Do Docker CLI yêu cầu đường dẫn tuyệt đối khi mount volume, ta sử dụng biến môi trường đại diện cho thư mục hiện tại (`$PWD` hoặc `%CD%`) để hoạt động như đường dẫn tương đối:
    - **Trong PowerShell hoặc Git Bash / Linux / macOS**:
      ```bash
      docker run -d -p 8080:80 --name my-nginx-custom -v ${PWD}/html:/usr/share/nginx/html -v ${PWD}/nginx.conf:/etc/nginx/conf.d/default.conf nginx:alpine
      ```
    - **Trong Command Prompt (cmd) của Windows**:
      ```bash
      docker run -d -p 8080:80 --name my-nginx-custom -v "%CD%/html:/usr/share/nginx/html" -v "%CD%/nginx.conf:/etc/nginx/conf.d/default.conf" nginx:alpine
      ```
   
   Truy cập `http://localhost:8080` trên trình duyệt để kiểm tra kết quả!

5. **Dọn dẹp**:
   ```bash
   docker stop my-nginx-custom
   docker rm my-nginx-custom
   ```
