# Kiến trúc Thu thập Log Tập trung và Hướng dẫn Kiến thức ELK Stack

Tài liệu này cung cấp thông tin chi tiết bằng tiếng Việt về kiến trúc thiết kế, luồng dữ liệu và các kiến thức vận hành quan trọng của hệ thống thu thập log tập trung sử dụng bộ ba Elasticsearch, Logstash và Kibana (ELK Stack) cho hệ thống microservices.

---

## 1. Sơ đồ kiến trúc thiết kế hệ thống

Dưới đây là quy trình chạy của log từ khi được tạo ra tại microservice cho đến lúc người dùng truy cập trên trình duyệt:

```
[Các Microservices (Java Spring Boot)]
(Ghi log trực tiếp bằng thư viện Logstash Logback Encoder)
       |
       | (Format JSON Lines tự động)
       v
[Thư mục log trên Host] (./log/{tên_dịch_vụ}/application.log)
       |
       | (Docker Bind Mount - chế độ chỉ đọc read-only cho Logstash)
       v
[Logstash Container]
       | (Input: Plain Codec UTF-8)
       | (Filter: Json parser và Grok fallback cho plain text)
       | (Output: Bulk API đẩy dữ liệu vào Elasticsearch)
       v
[Elasticsearch Container]
       | (Lưu trữ, Indexing ngữ nghĩa và chia nhỏ index theo ngày)
       v
[Kibana UI Container] (Bypass chế độ nhập enrollment token và kết nối trực tiếp)
       |
       | (REST API Query qua port 5601)
       v
[Người dùng / Trình duyệt] (Truy vấn và theo dõi thời gian thực)
```

---

## 2. Kiến thức chi tiết về từng thành phần

### Spring Boot Logging với Logback JSON Encoder
Trong các microservices viết bằng Spring Boot, việc ghi log truyền thống thường dùng PatternLayoutEncoder để tạo ra các dòng log dạng chuỗi (plain-text). Tuy nhiên, định dạng này rất khó cho các hệ thống phân tích đọc và phân chia các trường thông tin cụ thể (như log level, tên class, tên thread).

Để giải quyết vấn đề này, chúng ta tích hợp thư viện:
net.logstash.logback:logstash-logback-encoder:7.4

Trong file cấu hình logback-spring.xml của mỗi dịch vụ, chúng ta thiết lập appender FILE_JSON sử dụng class ghi mã:
net.logstash.logback.encoder.LogstashEncoder

Cơ chế hoạt động chi tiết:
- Tự động cấu trúc thông tin: Khi gọi dòng lệnh log.info("Thông báo"), thư viện sẽ tự động bọc thông điệp này cùng toàn bộ thông tin ngữ cảnh liên quan vào một đối tượng JSON.
- Các trường tùy biến (Custom Fields): Các trường thông tin cố định như service_name và environment được nhúng trực tiếp vào trong chuỗi JSON thông qua thẻ customFields của Logback. Điều này giúp hệ thống Logstash nhận diện nguồn gốc của dòng log ngay từ thuộc tính của tài liệu mà không cần phải thực hiện bóc tách ngược từ đường dẫn file log của container.
- Bộ chuyển đổi định dạng lỗi (Throwable Converter): Khi xuất hiện lỗi ngoại lệ (Exception), bộ ShortenedThrowableConverter sẽ tự động định dạng khối stack trace dài thành một cấu trúc chuỗi JSON gọn gàng, giúp hiển thị trực quan trên giao diện Kibana và tiết kiệm tài nguyên lưu trữ đĩa cứng bằng cách giới hạn độ sâu dòng lỗi.

### Elasticsearch (Bộ não lưu trữ và truy vấn)
Elasticsearch là một công cụ tìm kiếm và phân tích phân tán mạnh mẽ được viết bằng Java, hoạt động thông qua giao thức RESTful API.
- Cơ chế tạo chỉ mục (Indexing): Elasticsearch sử dụng cấu trúc chỉ mục đảo ngược (Inverted Index). Đây là lý do cốt lõi giúp hệ thống có thể thực hiện tìm kiếm từ khóa trên hàng triệu bản ghi log với tốc độ phản hồi gần như tức thì.
- Cơ chế lưu trữ vật lý (Storage Persistence): Toàn bộ cơ sở dữ liệu của Elasticsearch được đồng bộ ra thư mục cứng `./data/elasticsearch` trên máy chủ. Do Elasticsearch lưu trữ dữ liệu dưới dạng phân tán và ghi nhận trạng thái liên tục, việc tắt đột ngột container mà không dùng lệnh dừng an toàn có thể gây lỗi cấu trúc dữ liệu hoặc hỏng file redo logs. Vì vậy, dịch vụ luôn được thiết lập chính sách khởi động lại tự động là unless-stopped và được quản lý tắt/mở an toàn qua file quản lý manage.bat.

### Logstash (Bộ xử lý pipeline dữ liệu trung gian)
Logstash hoạt động như một bộ tiếp nhận và xử lý dữ liệu trung gian theo mô hình ba giai đoạn: Input -> Filter -> Output.
- Giai đoạn Input: Sử dụng plugin file để giám sát thời gian thực các tệp tin log. Thông qua tệp tin cấu hình sincedb_path trỏ tới thư mục `./data/logstash`, Logstash sẽ lưu lại vị trí dòng cuối cùng mà nó đã đọc của từng tệp tin log. Khi hệ thống khởi động lại, Logstash sẽ đọc tiếp từ vị trí đã lưu thay vì quét lại từ đầu, ngăn chặn việc ghi trùng lặp dữ liệu log vào Elasticsearch.
- Giai đoạn Filter: Bộ lọc phân loại thông minh của Logstash thực hiện hai nhiệm vụ:
  - Phân tích cú pháp JSON: Sử dụng bộ lọc json để tự động phân tách cấu trúc log JSON từ Spring Boot. Nếu thành công, nó sẽ trích xuất trường service_name của ứng dụng làm nhãn index và đưa các trường dữ liệu quan trọng như level, thread_name, logger_name lên cấp cao nhất (root level).
  - Dự phòng Grok (Fallback): Nếu dòng log là định dạng văn bản cũ hoặc log khởi động mặc định của Docker, Logstash sẽ tự động kích hoạt bộ lọc grok để cắt chuỗi văn bản theo biểu thức chính quy, lấy ra mốc thời gian, cấp độ lỗi và trích xuất tên dịch vụ từ đường dẫn tệp tin để gán nhãn phân loại.
- Giai đoạn Output: Logstash đẩy dữ liệu đã định hình sang Elasticsearch thông qua Bulk API để tối ưu hóa hiệu năng mạng và đẩy nhanh tốc độ tạo chỉ mục. Tên index đích được phân loại động theo ngày và tên dịch vụ:
  `index => "microservice-logs-%{service_name}-%{+YYYY.MM.dd}"`

### Kibana (Giao diện hiển thị và quản lý trực quan)
Kibana chạy trên cổng 5601, đóng vai trò là giao diện đồ họa tương tác trực tiếp với cơ sở dữ liệu Elasticsearch.
- Tự động bỏ qua Enrollment Token (Bypass): Bắt đầu từ phiên bản 8.x, Elasticsearch và Kibana áp dụng chính sách bảo mật mặc định yêu cầu người dùng cấu hình thủ công token đăng ký khi cài đặt. Để loại bỏ bước cấu hình phức tạp này trong môi trường phát triển, chúng ta đã tắt tính năng bảo mật xpack của Elasticsearch và bổ sung cấu hình `INTERACTIVESETUP_ENABLED=false` cho Kibana. Nhờ đó, người dùng có thể truy cập thẳng vào giao diện Discover mà không gặp bất kỳ màn hình chặn yêu cầu token nào.

---

## 3. Luồng đi chi tiết của một dòng log trong hệ thống

Hành trình chi tiết của một dòng log từ lúc sinh ra đến khi hiển thị trên giao diện:

1. Ứng dụng Spring Boot phát sinh một dòng log thông qua mã nguồn:
   ```java
   log.info("Kết nối database Uniqlo thành công!");
   ```
2. Thư viện Logback mã hóa thông báo này thành một dòng JSON chuẩn UTF-8 và ghi vào file log:
   ```json
   {"timestamp":"2026-07-08T15:53:59.548Z","@version":"1","message":"Kết nối database Uniqlo thành công!","logger_name":"com.t3h.Config","thread_name":"main","level":"INFO","service_name":"user-service","environment":"docker"}
   ```
3. Logstash phát hiện có dòng mới được ghi thêm vào tệp tin `/log/user-service/application.log` và nạp vào pipeline xử lý.
4. Logstash phân tích cú pháp JSON, ánh xạ mốc thời gian timestamp từ Logback vào trường thời gian chuẩn `@timestamp` của Elasticsearch để đồng bộ giờ hiển thị chính xác.
5. Logstash ghi nhận trường service_name có giá trị là user-service, sau đó gửi yêu cầu Bulk API ghi bản ghi này vào chỉ mục `microservice-logs-user-service-2026.07.08` trên Elasticsearch.
6. Người dùng mở trình duyệt truy cập Kibana, tìm kiếm từ khóa "database" trên Data View `microservice-logs-*` và hệ thống sẽ hiển thị dòng log kết nối thành công tương ứng.

---

## 4. Hướng dẫn vận hành và các câu lệnh REST API quan trọng

Bạn có thể gửi các yêu cầu REST API trực tiếp đến Elasticsearch bằng các công cụ như Postman, tiện ích mở rộng Thunder Client trên VSCode hoặc sử dụng trực tiếp tab Dev Tools trong mục Management của Kibana.

### Kiểm tra danh sách các chỉ mục đang lưu trữ và dung lượng
Câu lệnh hiển thị thông tin về các index đang có, số lượng tài liệu log đã lưu và dung lượng đĩa cứng tiêu thụ:
```http
GET http://localhost:9200/_cat/indices?v
```

### Xóa bỏ dữ liệu log cũ để giải phóng dung lượng ổ đĩa
Vì hệ thống phân chia các chỉ mục lưu trữ theo ngày dạng `tên-dịch-vụ-YYYY.MM.dd`, việc dọn dẹp các log cũ diễn ra rất nhanh chóng và an toàn.
Để xóa toàn bộ log của tất cả các dịch vụ phát sinh vào một ngày cụ thể (ví dụ ngày 08/07/2026):
```http
DELETE http://localhost:9200/microservice-logs-*-2026.07.08
```

Để xóa toàn bộ log lịch sử tích lũy của riêng dịch vụ `product-service`:
```http
DELETE http://localhost:9200/microservice-logs-product-service-*
```

### Truy vấn dữ liệu log trực tiếp từ Elasticsearch không qua Kibana
Bạn có thể tìm kiếm nhanh các thông báo log chứa mã lỗi ERROR trực tiếp từ Elasticsearch bằng yêu cầu truy vấn:
```http
POST http://localhost:9200/microservice-logs-*/_search
Content-Type: application/json

{
  "query": {
    "match": {
      "level": "ERROR"
    }
  },
  "size": 10
}
```

---

## 5. Các điểm lưu ý khi sao lưu và di chuyển hệ thống sang máy khác

Thiết lập lưu trữ của dự án được tối ưu hóa cho tính di động cao giữa các môi trường làm việc khác nhau:
- Bảo toàn dữ liệu cũ: Toàn bộ thông tin cơ sở dữ liệu MySQL, Redis và các chỉ mục log của Elasticsearch đều được ánh xạ vật lý ra thư mục `./data/` ở máy chủ chứ không nằm trong các ổ đĩa ảo ẩn của Docker. Khi sao chép thư mục dự án sang máy tính khác, toàn bộ dữ liệu lịch sử này sẽ tự động được tải lại nguyên vẹn.
- Khả năng tự khởi tạo: Nếu bạn sử dụng file cấu hình `.gitignore` để bỏ qua việc đẩy các thư mục `./data/` và `./log/` lên hệ thống quản lý mã nguồn Git, các script quản lý tự động `manage.bat` (trên Windows) và `manage.sh` (trên Linux/macOS) sẽ tự động kiểm tra và khởi tạo lại toàn bộ cấu trúc thư mục con bị thiếu trước khi gọi docker-compose chạy. Điều này giúp ngăn ngừa hoàn toàn các lỗi crash do thiếu đường dẫn tệp tin khi chạy dự án lần đầu tiên ở máy tính mới.
- Lưu ý quyền thực thi trên Unix: Đối với hệ điều hành Linux hoặc macOS, bạn cần cấp quyền thực thi cho script trước khi chạy bằng lệnh: `chmod +x manage.sh`.
