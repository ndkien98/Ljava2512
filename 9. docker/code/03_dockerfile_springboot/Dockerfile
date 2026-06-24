# Sử dụng JRE 17 Alpine siêu gọn làm nền
FROM eclipse-temurin:17-jre-alpine

# Thông tin tác giả
LABEL maintainer="t3h-java2512"

# Khai báo thư mục làm việc trong container
WORKDIR /app

# Khai báo cổng lắng nghe
EXPOSE 8080

# Cấu hình tối ưu JVM tránh ngốn RAM cục bộ
ENV JAVA_OPTS="-Xms128m -Xmx256m"

# Sao chép tệp JAR đã build từ thư mục target vào container
COPY target/*.jar app.jar

# Lệnh khởi chạy ứng dụng chính
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
