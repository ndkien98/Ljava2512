# Sử dụng JDK 17 bản Alpine siêu gọn làm Base Image
FROM openjdk:17-jdk-alpine

# Khai báo tác giả
LABEL maintainer="ndkien98@t3h.edu.vn"

# Thiết lập thư mục làm việc bên trong container
WORKDIR /app

# Khai báo cổng ứng dụng lắng nghe
EXPOSE 8080

# Thiết lập các biến môi trường cấu hình JVM và Spring Boot profile
ENV JAVA_OPTS="-Xms64m -Xmx256m"
ENV SPRING_PROFILES_ACTIVE=prod

# Copy file jar đã build sẵn từ thư mục target ở máy host vào container
COPY target/*.jar app.jar

# Lệnh chạy ứng dụng khi khởi động container
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
