# Module 8: CI/CD Tự động hóa với GitHub Actions & Docker Hub

Demo này hướng dẫn cách thiết lập pipeline tự động hóa build và push Docker Image lên Docker Hub sử dụng GitHub Actions.

## Các bước cấu hình

1. **Chuẩn bị file workflow**: Tạo file cấu hình ở thư mục dự án của bạn theo đường dẫn `.github/workflows/docker-ci.yml` (Nội dung tham khảo tệp `docker-ci.yml` trong thư mục này).
2. **Cấu hình Secrets trên GitHub**:
   - Truy cập vào Repository trên GitHub -> **Settings** -> **Secrets and variables** -> **Actions**.
   - Thêm 2 biến mật mã (Secrets) sau:
     *   `DOCKERHUB_USERNAME`: Tên tài khoản đăng nhập Docker Hub của bạn.
     *   `DOCKERHUB_TOKEN`: Thay vì dùng mật khẩu, hãy tạo **Access Token** trên Docker Hub (Account Settings -> Security -> New Access Token) và dán vào đây để bảo mật.
3. **Kích hoạt**:
   - Mỗi lần bạn thực hiện `git push` code lên nhánh `main`, GitHub Actions sẽ tự động khởi chạy máy ảo Ubuntu, kéo mã nguồn về, cài đặt JDK/Maven, đăng nhập Docker Hub và chạy build Dockerfile rồi đẩy thẳng Image mới lên Docker Hub.
