# Module 7: Docker nâng cao - Tối ưu hóa dung lượng với Multi-stage Build

Demo này cung cấp mẫu Dockerfile tối ưu chuẩn production sử dụng cơ chế **Multi-stage Build** cho ứng dụng Java Spring Boot `uniqlo-service-v1`.

## Cơ chế Multi-stage Build là gì?

- Trong cách build thông thường (Single-stage), ta dùng một base image chứa đầy đủ JDK và Maven để build code. Hệ quả là image thành phẩm sẽ chứa cả trình biên dịch Maven, các tệp cache và mã nguồn gốc vốn không cần thiết lúc chạy. Dung lượng lúc này có thể lên tới **800MB - 1GB**.
- **Multi-stage Build** chia Dockerfile thành nhiều giai đoạn:
  - **Giai đoạn 1 (builder)**: Sử dụng JDK & Maven để biên dịch ra file JAR.
  - **Giai đoạn 2 (runtime)**: Chỉ sử dụng JRE (Java Runtime Environment) siêu nhỏ và copy duy nhất tệp JAR từ Giai đoạn 1 sang. Toàn bộ mã nguồn, Maven, cache dependencies đều bị bỏ lại. Dung lượng ảnh thành phẩm chỉ còn **~100MB**.

## Cách thực hiện build

Sao chép tệp `Dockerfile.springboot` từ thư mục này vào gốc của dự án `uniqlo-service-v1` và đặt tên là `Dockerfile.production`, sau đó chạy:

```bash
# Đứng tại thư mục gốc uniqlo-service-v1:
docker build -f ../../../9.\ docker/code/07_advanced_multi_stage/Dockerfile.springboot -t uniqlo-service-mono:prod .
```

## Kiểm tra so sánh dung lượng
```bash
docker images | grep uniqlo-service-mono
```
Bạn sẽ thấy image `uniqlo-service-mono:prod` có dung lượng cực kỳ nhỏ gọn so với các image build đơn lớp thông thường, đồng thời giúp tăng độ bảo mật do giảm bề mặt tấn công.
