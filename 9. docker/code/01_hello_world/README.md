# Module 1: Chạy Container Đầu Tiên - Hello World

Demo này hướng dẫn cách khởi chạy container đầu tiên từ ảnh `hello-world` trên Docker Hub và tìm hiểu vòng đời của một container cơ bản.

## Các lệnh thực hành từng bước

1. **Khởi chạy container hello-world**:
   ```bash
   docker run hello-world
   ```

2. **Giải thích luồng hoạt động**:
   - **Bước 1**: Docker Client (CLI) gửi yêu cầu khởi chạy container từ image `hello-world:latest` tới Docker Daemon (`dockerd`) trên máy của bạn.
   - **Bước 2**: Docker Daemon tìm kiếm image `hello-world` trong kho lưu trữ cục bộ (Local Storage).
   - **Bước 3**: Vì đây là lần chạy đầu tiên, image chưa có sẵn dưới local. Daemon sẽ kết nối mạng tới **Docker Hub Registry** để tự động tải (pull) image về.
   - **Bước 4**: Docker Daemon tạo mới một container từ image vừa tải, khởi tạo tiến trình chạy in dòng chữ chào mừng ra màn hình.
   - **Bước 5**: Kết quả (stdout) được Daemon chuyển tiếp ngược về cho Docker Client hiển thị trên Command Prompt / PowerShell của bạn.
   - **Bước 6**: Sau khi hoàn thành việc in thông báo, tiến trình chính trong container kết thúc, container tự động chuyển sang trạng thái Stopped (đã dừng).

3. **Xem danh sách các container đã chạy**:
   ```bash
   # Lệnh hiển thị các container đang chạy (sẽ trống vì hello-world đã dừng)
   docker ps

   # Lệnh hiển thị tất cả container bao gồm cả các container đã dừng
   docker ps -a
   ```

4. **Xóa container hello-world để dọn dẹp hệ thống**:
   ```bash
   docker rm <container_name_or_id>
   ```
