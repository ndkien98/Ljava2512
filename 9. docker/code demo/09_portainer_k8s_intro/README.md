# Module 9: Hệ sinh thái Docker - Quản trị Web UI với Portainer

Demo này hướng dẫn cách chạy Portainer Dashboard để quản lý container, images, network, volume bằng giao diện đồ họa Web trực quan thay vì gõ dòng lệnh CLI.

## Các bước chạy thử

1. **Khởi chạy Portainer**:
   ```bash
   docker-compose up -d
   ```
2. **Truy cập Giao diện Web**:
   - Mở trình duyệt và truy cập: `http://localhost:9000`
   - Đăng ký tài khoản Admin mới cho lần đầu tiên truy cập.
   - Chọn môi trường quản lý là **Local** (Portainer tự động đọc Docker Engine thông qua mount file `/var/run/docker.sock`).
3. **Các tính năng sử dụng**:
   - Xem dashboard trực quan số lượng container đang chạy, đang dừng.
   - Xem chi tiết danh sách container, có thể Start, Stop, Restart hoặc xem trực tiếp logs và CPU/RAM của từng container theo thời gian thực.
   - Quản trị dễ dàng các volume, images và network.
4. **Dọn dẹp**:
   ```bash
   docker-compose down
   ```
