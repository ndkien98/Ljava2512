# Module 3: Dockerfile - Đóng gói ứng dụng Spring Boot

Demo này hướng dẫn cách đóng gói một ứng dụng Java Spring Boot thành một Docker Image và tối ưu dung lượng & cache layer.

## Giải thích chi tiết các chỉ thị trong Dockerfile

1. **FROM openjdk:17-jdk-alpine**: Sử dụng JDK 17 phiên bản Alpine (Linux siêu nhỏ) để làm nền. Điều này giúp giảm đáng kể kích thước Image so với bản JDK đầy đủ của Ubuntu.
2. **WORKDIR /app**: Chỉ định `/app` là thư mục gốc của các thao tác chạy lệnh sau đó bên trong container.
3. **EXPOSE 8080**: Khai báo cổng 8080.
4. **ENV JAVA_OPTS="..."**: Tối ưu hóa bộ nhớ JVM. Vì chạy nhiều microservice cùng lúc trên máy, việc giới hạn `-Xmx256m` là cực kỳ quan trọng để tránh lỗi tràn bộ nhớ (Out Of Memory).
5. **COPY target/*.jar app.jar**: Copy file jar đã build thành công ở máy host vào container.
6. **ENTRYPOINT**: Định nghĩa lệnh khởi chạy chính. Ta sử dụng `sh -c` để nạp được biến môi trường `$JAVA_OPTS`.

## Các lệnh thực hành Build & Run

Để build được Dockerfile này, trước tiên bạn cần build file JAR ứng dụng Spring Boot bằng maven ở máy host:
```bash
# Biên dịch ra file JAR
mvn clean package -DskipTests

# Build Docker Image với tag là 'uniqlo-service:v1'
# Dấu chấm (.) chỉ định build context ở thư mục hiện tại chứa Dockerfile
docker build -t uniqlo-service:v1 .

# Chạy thử container từ Image vừa build
docker run -d -p 8080:8080 --name my-service uniqlo-service:v1
```
