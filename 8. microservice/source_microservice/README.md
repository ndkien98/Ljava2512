# Uniqlo Microservice and ELK Stack Manager

Tài liệu hướng dẫn quản lý và chạy toàn bộ hệ thống Uniqlo Microservices cùng với bộ công cụ thu thập log tập trung ELK Stack (Elasticsearch, Logstash, Kibana).

---

## Quản lý hệ thống bằng 1-Click

Hệ thống cung cấp sẵn script quản lý tự động cho cả hai môi trường Windows và Linux/macOS. Khi chạy, script sẽ tự động kiểm tra và tạo đầy đủ các thư mục dữ liệu và logs nếu thiếu, giúp bạn chạy dự án ở bất kỳ máy tính mới nào một cách nhanh chóng.

### Trên Windows
Chạy file manage.bat ở thư mục gốc bằng cách nhấn đúp chuột hoặc chạy qua Command Prompt:
```bash
manage.bat
```

### Trên Linux / macOS
Mở Terminal tại thư mục gốc, cấp quyền thực thi và chạy file manage.sh:
```bash
chmod +x manage.sh
./manage.sh
```

Giao diện quản lý hiển thị các lựa chọn:
- **1. Start All Services**: Tự động kiểm tra, tạo cấu trúc thư mục dữ liệu/logs nếu thiếu và chạy toàn bộ 13 containers lên.
- **2. Stop All Services**: Dừng toàn bộ các container của dự án để giải phóng tài nguyên CPU/RAM, đồng thời bảo toàn dữ liệu database/log.
- **3. Build and Start**: Rebuild lại code Java Spring Boot và khởi chạy lại các container tương ứng.
- **4. Clean Reset and Fresh Start**: Xóa toàn bộ dữ liệu MySQL, Redis, Elasticsearch indices, logs và khởi động lại sạch sẽ như ban đầu.
- **5. Check System Status**: Kiểm tra danh sách container nào đang chạy và cổng hoạt động.
- **6. View Container Logs**: Xem log trực tiếp (real-time stream) của một container bất kỳ (ví dụ: user-service, logstash, v.v.)

---

## Cấu trúc thư mục dữ liệu (Persistent Data)

Hệ thống đã được thiết lập để tách biệt hoàn toàn dữ liệu và logs ra các thư mục riêng của từng container:
```
source_microservice/
│
├── manage.bat                  - Công cụ quản lý trên Windows (Tự động tạo folder)
├── manage.sh                   - Công cụ quản lý trên Linux/macOS (Tự động tạo folder)
├── .gitignore                  - File cấu hình bỏ qua thư mục data và log khi commit lên Git
├── docker-compose.yml          - File cấu hình toàn bộ hệ thống
│
├── data/                       - Dữ liệu lưu trữ persistent (Tự sinh)
│   ├── mysql/                  - Dữ liệu cơ sở dữ liệu MySQL
│   ├── redis/                  - Dữ liệu đệm Redis
│   ├── elasticsearch/          - Các chỉ mục tìm kiếm và logs lưu trữ của ES
│   ├── kibana/                 - Các thiết lập cấu hình của Kibana
│   └── logstash/               - Vị trí ghi nhớ của Logstash (sincedb)
│
└── log/                        - Logs dạng JSON của các Service (Tự sinh)
    ├── user-service/           - logs của user-service
    ├── product-service/        - logs của product-service
    ├── master-data-service/    - logs của master-data-service
    ├── order-service/          - logs của order-service
    ├── config-server/          - logs của config-server
    ├── eureka-server/          - logs của eureka-server
    └── eureka-gateway/         - logs của eureka-gateway
```

---

## Xem Logs trên Kibana

### Bước 1: Mở trình duyệt
Truy cập: http://localhost:5601

### Bước 2: Tạo Data View (Chỉ cần làm 1 lần duy nhất)
1. Bấm vào biểu tượng Menu ở góc trên bên trái -> Chọn Stack Management (dưới cùng).
2. Chọn Data Views -> Click nút Create data view.
3. Cấu hình như sau:
   - **Name**: Microservice Logs
   - **Index pattern**: microservice-logs-*
   - **Timestamp field**: @timestamp
4. Click Save data view to Kibana.

### Bước 3: Đọc log tập trung
- Click Menu -> Chọn Discover.
- Bạn có thể lọc log theo từng service bằng cách thêm filter service_name : "user-service" hoặc tìm kiếm theo từ khóa ở ô Search bar.

---

## Các cổng kết nối của hệ thống

| Tên Dịch Vụ | Port | URL / Địa chỉ truy cập |
| :--- | :--- | :--- |
| Kibana UI | 5601 | http://localhost:5601 (Xem log) |
| Frontend UI | 3000 | http://localhost:3000 (Ứng dụng chính) |
| API Gateway | 8080 | http://localhost:8080 |
| Eureka Server | 19089 | http://localhost:19089 (Quản lý đăng ký dịch vụ) |
| Config Server | 19088 | http://localhost:19088 |
| Elasticsearch API | 9200 | http://localhost:9200 |
| MySQL Database | 3307 | localhost:3307 (User: root / Pass: root) |
| Redis Database | 6379 | localhost:6379 |
