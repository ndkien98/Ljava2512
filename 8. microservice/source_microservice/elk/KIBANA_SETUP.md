# Hướng dẫn Setup Kibana Index Pattern

## Bước 1: Đảm bảo ELK đang chạy

```bash
cd elk
docker-compose -f docker-compose.elk.yml ps
```

Kiểm tra Elasticsearch:
```
curl http://localhost:9200/_cluster/health
```

## Bước 2: Đảm bảo microservices đang chạy và sinh log

```bash
# Quay về thư mục gốc
cd ..
docker-compose up -d

# Kiểm tra log được tạo ra
ls log/user-service/
ls log/order-service/
```

## Bước 3: Kiểm tra Elasticsearch có nhận log chưa

```bash
curl http://localhost:9200/_cat/indices?v
```
Bạn sẽ thấy các index như:
- `microservice-logs-user-service-2024.xx.xx`
- `microservice-logs-order-service-2024.xx.xx`
- v.v.

## Bước 4: Tạo Data View trong Kibana

1. Mở trình duyệt: **http://localhost:5601**
2. Vào menu **☰ → Stack Management → Index Management** để kiểm tra indices
3. Vào **☰ → Stack Management → Data Views**
4. Click **Create data view**
5. Điền:
   - **Name**: `Microservice Logs`
   - **Index pattern**: `microservice-logs-*`
   - **Timestamp field**: `@timestamp`
6. Click **Save data view to Kibana**

## Bước 5: Xem Log trong Kibana Discover

1. Vào **☰ → Discover**
2. Chọn Data View **Microservice Logs**
3. Điều chỉnh time range (góc trên phải) → **Last 15 minutes** hoặc **Today**
4. Bạn sẽ thấy tất cả log từ các microservices!

## Các field hữu ích để filter/search

| Field | Mô tả | Ví dụ |
|-------|--------|-------|
| `service_name` | Tên service | `user-service`, `order-service` |
| `level` | Log level | `INFO`, `ERROR`, `WARN` |
| `logger_name` | Tên class | `com.t3h.userservice.UserController` |
| `message` | Nội dung log | |
| `@timestamp` | Thời gian | |
| `stack_trace` | Stack trace khi có lỗi | |
| `thread_name` | Thread name | |

## Tips tạo Dashboard

1. Vào **☰ → Dashboard → Create dashboard**
2. Click **Add panel → Aggregation based**
3. Tạo biểu đồ **Pie chart**: thống kê log theo `service_name`
4. Tạo biểu đồ **Bar chart**: log level theo thời gian
5. Tạo **Data table**: liệt kê các ERROR log gần nhất

## Troubleshooting

### Không thấy log trong Kibana
```bash
# Kiểm tra Logstash có đọc file không
docker logs logstash --tail 50

# Kiểm tra file log có tồn tại không
ls log/user-service/application.log

# Kiểm tra Elasticsearch indices
curl http://localhost:9200/_cat/indices?v
```

### Elasticsearch không khởi động được
```bash
# Tăng virtual memory (Windows PowerShell as Admin)
wsl -d docker-desktop sysctl -w vm.max_map_count=262144
```
