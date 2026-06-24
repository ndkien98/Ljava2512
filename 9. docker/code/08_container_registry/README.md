# Module 8: Quy trình phát hành Image lên Container Registry (Docker Hub)

Bài học này hướng dẫn các bước thủ công để đẩy (push) image `uniqlo-service-mono:prod` của bạn lên kho lưu trữ **Docker Hub** để chia sẻ hoặc triển khai lên máy chủ Cloud.

## Các bước thực hiện chi tiết

### Bước 1: Đăng nhập Docker Hub từ CLI
Mở terminal và thực thi câu lệnh sau để đăng nhập vào tài khoản Docker Hub của bạn:
```bash
docker login
```
*Nhập Username và Password của bạn.* (Khuyên dùng Access Token được tạo tại Docker Hub Settings -> Security -> New Access Token thay thế cho mật khẩu chính thức để bảo mật thông tin).

### Bước 2: Đặt nhãn (tag) cho Docker Image
Để đẩy được ảnh lên Docker Hub, ảnh cục bộ của bạn phải được đặt nhãn trùng khớp với cấu trúc tên tài khoản trên Docker Hub:
**Cú pháp**: `docker tag <tên_ảnh_local>:<tag> <tên_tài_khoản_dockerhub>/<tên_repository>:<tag>`

*Ví dụ*: Nếu tên tài khoản Docker Hub của bạn là `ndkien98`, bạn gõ:
```bash
docker tag uniqlo-service-mono:prod ndkien98/uniqlo-service-mono:v1.0
```

### Bước 3: Đẩy Image lên Docker Hub Registry
Tiến hành tải image lên đám mây Docker Hub:
```bash
docker push ndkien98/uniqlo-service-mono:v1.0
```

### Bước 4: Kiểm tra và sử dụng
- Đăng nhập giao diện web Docker Hub, bạn sẽ thấy repository `uniqlo-service-mono` cùng tag `v1.0` đã xuất hiện.
- Bây giờ, ở bất kỳ máy tính nào khác có cài đặt Docker, bạn chỉ cần gõ lệnh sau để tải về và chạy trực tiếp:
  ```bash
  docker run -d -p 8080:8080 ndkien98/uniqlo-service-mono:v1.0
  ```
