# Module 9: Thiết lập hạ tầng Kafka & Elasticsearch bằng Docker Compose

Thư mục này cung cấp tệp cấu hình `docker-compose.yml` để dựng nhanh môi trường chạy thử cho **Kafka** (kèm Zookeeper) và **Elasticsearch** (kèm Kibana). Việc phân tách này giúp bạn làm quen và thử nghiệm kết nối với hai hệ thống trung gian/lưu trữ phức tạp mà không làm quá tải hệ thống.

## Các cổng kết nối chính
- **Zookeeper**: `2181`
- **Kafka Broker**: `9092`
- **Elasticsearch API**: `9200`
- **Kibana Web UI**: `5601`

---

## Hướng dẫn khởi chạy và kiểm tra

### Bước 1: Khởi chạy cụm dịch vụ
Mở terminal tại thư mục này và gõ:
```bash
docker-compose up -d
```
Chờ từ 30s đến 1 phút để các dịch vụ khởi động hoàn tất.

### Bước 2: Thực hành với Apache Kafka (Message Broker)

Ta sẽ truy cập trực tiếp vào container `kafka-demo` để thực hiện tạo Topic, gửi tin nhắn (Producer) và nhận tin nhắn (Consumer).

1. **Mở terminal tương tác bên trong container**:
   ```bash
   docker exec -it kafka-demo bash
   ```

2. **Tạo một Topic có tên là `uniqlo-orders`**:
   ```bash
   kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic uniqlo-orders
   ```

3. **Khởi chạy Kafka Console Producer để gửi một số message**:
   ```bash
   kafka-console-producer --bootstrap-server localhost:9092 --topic uniqlo-orders
   ```
   *Nhập một vài dòng tin nhắn bất kỳ (ví dụ: `Order-001 Created`, `Payment success`), sau đó bấm Enter. Nhấn `Ctrl + C` để thoát.*

4. **Khởi chạy Kafka Console Consumer để nhận tin nhắn từ đầu (from-beginning)**:
   ```bash
   kafka-console-consumer --bootstrap-server localhost:9092 --topic uniqlo-orders --from-beginning
   ```
   *Bạn sẽ thấy các tin nhắn vừa nhập hiển thị ra màn hình. Nhấn `Ctrl + C` để thoát, sau đó gõ `exit` để ra ngoài máy host.*

---

### Bước 3: Thực hành với Elasticsearch & Kibana (Search Engine)

1. **Kiểm tra trạng thái hoạt động của Elasticsearch**:
   Mở trình duyệt truy cập: `http://localhost:9200`
   Bạn sẽ thấy thông tin JSON trả về chứa thông tin phiên bản cùng câu slogan: `"You Know, for Search"`.

2. **Truy cập giao diện Kibana**:
   Mở trình duyệt truy cập: `http://localhost:5601`
   - Giao diện quản trị Kibana hiển thị.
   - Truy cập mục **Management** -> **Dev Tools** để viết các câu lệnh REST API tương tác với Elasticsearch trực tiếp.
   - Thử chèn một document sản phẩm mới:
     ```http
     POST /products/_doc/1
     {
       "name": "Áo khoác chống nắng Uniqlo",
       "price": 499000,
       "category": "Áo khoác"
     }
     ```
   - Thử truy vấn tìm kiếm sản phẩm:
     ```http
     GET /products/_search?q=price:499000
     ```

## Dọn dẹp hệ thống
Khi kết thúc thực hành, giải phóng RAM và ổ cứng bằng cách chạy:
```bash
docker-compose down
```
