# Module 7: Docker nâng cao - Tối ưu hóa dung lượng với Multi-stage Build

Demo này cung cấp các mẫu Dockerfile chuẩn production sử dụng cơ chế **Multi-stage Build** cho hai công nghệ chính trong hệ sinh thái Uniqlo: Java Spring Boot và ReactJS.

## Các tệp Dockerfile mẫu

1. **`Dockerfile.springboot`**:
   - **Giai đoạn 1 (Builder)**: Sử dụng base image Maven đầy đủ dung lượng để biên dịch mã nguồn Java ra file JAR.
   - **Giai đoạn 2 (Runtime)**: Chỉ sử dụng JRE Alpine siêu nhỏ (chỉ chứa môi trường chạy Java JRE, không có trình biên dịch Maven) và copy file JAR sang. Giúp giảm dung lượng image từ ~800MB xuống còn ~100MB.
2. **`Dockerfile.react`**:
   - **Giai đoạn 1 (Builder)**: Sử dụng Node.js để cài đặt các thư viện `node_modules` và chạy lệnh `npm run build` để xuất mã HTML/JS tĩnh.
   - **Giai đoạn 2 (Runtime)**: Sử dụng Nginx để phân phối trực tiếp các tệp tin tĩnh đó. Giúp loại bỏ hoàn toàn Node.js và thư mục `node_modules` khổng lồ ra khỏi Image chạy production. Giảm dung lượng từ ~1GB xuống còn ~20MB.

## Cách build cụ thể

```bash
# Build Spring Boot Multi-stage
docker build -f Dockerfile.springboot -t my-springboot-app:latest .

# Build ReactJS Multi-stage
docker build -f Dockerfile.react -t my-react-app:latest .
```
