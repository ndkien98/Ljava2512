# Module 1: Hello World Docker Demo

Demo này hướng dẫn cách kéo và chạy image cơ bản nhất của Docker: `hello-world`.

## Các lệnh thực hành

1. Chạy thử container `hello-world`:
   ```bash
   docker run hello-world
   ```

2. Phân tích luồng hoạt động khi gõ lệnh trên:
   - **Bước 1**: Docker Client gửi yêu cầu chạy container `hello-world` tới Docker Daemon.
   - **Bước 2**: Docker Daemon tìm kiếm image `hello-world:latest` trên ổ đĩa cục bộ của máy host.
   - **Bước 3**: Vì đây là lần đầu chạy, image chưa có cục bộ, Daemon tự động tải (pull) image từ Docker Hub về máy.
   - **Bước 4**: Docker Daemon tạo ra một container mới từ image vừa tải, thực thi câu lệnh in ra màn hình và kết thúc tiến trình.
   - **Bước 5**: Kết quả (stdout) được Daemon gửi ngược lại cho Docker Client hiển thị ra màn hình của bạn.
