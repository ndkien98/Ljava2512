# Bài Giảng: Spring Boot - REST API, MVC và Thymeleaf

Chào mừng các bạn đến với bài giảng chi tiết về việc xây dựng ứng dụng web hiện đại với **Spring Boot**. Trong bài này, chúng ta sẽ bắt đầu từ các khái niệm căn bản nhất của Web như Giao thức HTTP, mô hình REST API, sau đó đi sâu vào thực hành qua công cụ Postman và mô hình MVC trong Spring Boot.

---

## Phần 1: Giao thức HTTP và REST API

### 1. Giao thức HTTP (HyperText Transfer Protocol) là gì?
HTTP là một giao thức ở tầng ứng dụng (Application Layer) truyền tải dữ liệu giữa Client (Trình duyệt web, ứng dụng điện thoại) và Server.
Nó hoạt động theo cơ chế **Request-Response** (Yêu cầu - Phản hồi).

#### Khái niệm cần biết:
- **Header**: Nơi chứa các thông tin metadata mô tả cho request hoặc response. Ví dụ: `Content-Type: application/json` thông báo dữ liệu truyền lên/trả về ở định dạng JSON, `Authorization: Bearer <token>` để xác thực.
- **Param (Query Parameter)**: Tham số truy vấn đính kèm trên URL sau dấu `?`. VD: `.../search?keyword=áo&page=1`.
- **Body**: Nơi chứa nội dung chính của Request/Response (thường dùng trong POST, PUT) để chứa đối tượng dài, phức tạp. Không xuất hiện ở phương thức GET.
- **HTTP Status Code**: Mã trạng thái mà Server báo lại cho Client:
  - `2xx`: Thành công (200 OK, 201 Created).
  - `3xx`: Điều hướng (301 Moved Permanently, 302 Found).
  - `4xx`: Lỗi từ Client (400 Bad Request, 401 Unauthorized, 404 Not Found).
  - `5xx`: Lỗi từ Server (500 Internal Server Error).

### 2. REST API là gì?
API (Application Programming Interface) là các hàm/phương thức để 2 ứng dụng nói chuyện với nhau. Còn **REST** (REpresentational State Transfer) là một tập hợp các quy tắc (Architectural Style) thiết kế API trên nền web.

#### Thực hành 4 Phương thức kinh điển (HTTP Methods):
REST sử dụng các HTTP Method để định nghĩa hành động:
- **`GET`**: Lấy thông tin tài nguyên (VD: Lấy danh sách sản phẩm, lấy thông tin 1 category).
- **`POST`**: Tạo mới tài nguyên (VD: Thêm 1 category mới).
- **`PUT`**: Cập nhật toàn bộ/ghi đè tài nguyên đã có.
- **`DELETE`**: Xoá tài nguyên.

---

## Phần 2: Công cụ kiểm thử (Test) - Postman

### 1. Postman là gì?
Khi chúng ta viết ứng dụng web Backend thuần API (ví dụ bằng Spring REST), bạn chưa có giao diện web để bấm nút gọi API. Postman đóng vai trò làm một Client (bên gọi) giả lập. Bạn có thể xây dựng gửi Request (chọn Method, điền URL, gắn Header, Body) và xem kết quả Response Server trả về.

### 2. Cách cài đặt và sử dụng
- Tải Postman tại: `https://www.postman.com/downloads/`
- Mở Postman, bấm vào dấu `+` để tạo Request mới.
- Chọn Method (GET/POST/PUT), nhập URL.
- Tab **Params**: thêm bộ lọc (Keyword).
- Tab **Headers**: truyền Access Token nếu cần.
- Tab **Body**: chọn `raw` -> chuyển kiểu text sang `JSON` để truyền đối tượng cục bộ lúc test POST/PUT.

---

## Phần 3: REST Controller trong Spring Boot

Trong Spring, để xây dựng 1 API, ta tạo ra một Class và gắn Annotaion `@RestController`.
Thành phần bên trong một `@RestController` dùng để xử lý Request:

### 1. Annotation phân tích Endpoint
Để chỉ định phương thức HTTP, Spring cung cấp:
- `@GetMapping("/api/categories")`
- `@PostMapping("/api/categories")`
- `@PutMapping("/api/categories/{id}")`
- `@DeleteMapping("/api/categories/{id}")`

### 2. Phân tách tham số đầu vào (Inputs)
- **`@PathVariable`**: Lấy giá trị nằm trực tiếp trên đường dẫn (URL template). 
  - VD: `/api/users/123`, dùng `@PathVariable Integer id` để nhận `123`.
- **`@RequestParam`**: Lấy giá trị trên cấu trúc query params (`?key=value`). 
  - VD: `/api/users?status=ACTIVE`, dùng `@RequestParam String status` để nhận `"ACTIVE"`.
- **`@RequestBody`**: Yêu cầu Spring ánh xạ phần JSON nằm trong Body của HTTP Request vào một Java Object (DTO) tương ứng.
- **`@RequestHeader`**: Lấy các meta properties từ Header.

### 3. Consumer và Producer (XML, JSON)
Mặc định Spring Boot (nhờ thư viện web chứa Jackson) sẽ tự động cấu hình:
- **Consumer** (Tiêu thụ dữ liệu vào): Controller hiểu định dạng JSON khi Client gọi lên (`produces` = HTTP request body format).
- **Producer** (Sản xuất dữ liệu ra): Khi hàm trong Controller `return object`, Spring sẽ "chuyển ngữ" (Serialize) đối tượng Java thành chuỗi JSON trả về cho Client.
Bạn cũng có thể cấu hình để trả về `XML` nếu bổ sung thư viện `jackson-dataformat-xml`.

### 4. `@Controller` và `@RestController`
- **`@Controller`**: Sử dụng với MVC để ánh xạ và trả về **View** (một giao diện web HTML/Thymeleaf). Hàm trả về String sẽ là **tên của tập tin HTML**.
- **`@RestController`**: Là sự kết hợp của `@Controller` + `@ResponseBody`. Nó mặc định rằng mọi giá trị từ controller trả về KHÔNG PHẢI là trang HTML, mà là **Dữ liệu thô** (Data/JSON).

---

## Phần 4: Spring Web MVC và Application Archiecture

### 1. Kiến trúc Project và Model - View - Controller (MVC)
MVC chia ứng dụng thành 3 phần:
- **Model (M - Data/Logic)**: Xử lý dữ liệu nghiệp vụ (bao gồm Service, Entity).
- **View (V - HTML/UI)**: Thiết kế giao diện (Thymeleaf, JSP).
- **Controller (C)**: Cầu nối, nhận request từ người dùng, gọi Model xử lý, sau đó chỉ định View để hiển thị.

Tại sao phải chia mô hình MVC?
- Phân tách trách nhiệm (Separation of Concerns).
- Giúp code tái sử dụng, dễ maintain, Frontend team và Backend team có thể làm việc mượt mà ít chồng chéo.

### 2. Sự phân biệt giữa Entity và DTO
*Đây là khái niệm cực kỳ quan trọng*.
- **Entity (Thực thể mô hình DB)**: Class ánh xạ 1-1 với cái Bảng (Table) trong Database. Ví dụ bảng `categories` có bao nhiêu cột thì `Category` (Entity) có bấy nhiêu fields. Dùng để làm việc trực tiếp với tầng Repository.
- **DTO (Data Transfer Object)**: Đối tượng vận chuyển dữ liệu. Dùng để giao tiếp với Client. 
  - Đôi khi bạn không muốn lộ trường `password` trong bảng `users` cho Client -> Tạo UserResponseDTO để ẩn nó đi.
  - Hoặc Client chỉ gửi lên thông tin đăng ký (Username, Password) -> Tạo UserRequestDTO rồi controller nhận vào.

### 3. Build Dự án và Vai Trò Apache Tomcat
Trong môi trường Spring Boot:
- Tomcat không phải là phần mềm chúng ta cài ở ngoài máy (External).
- Nó là một công nghệ được nhúng trực tiếp vào (Embedded Tomcat). 
- Khi bạn ấn `Run` app, Spring sẽ làm hành động khởi tạo lên một Web Server (Tomcat) ảo lắng nghe ở một cổng nhất định (mặc định 8080) và chèn ứng dụng MVC của bạn vào đó để có thể tiếp nhận yêu cầu từ mạng.

---

## Phần 5: Khởi tạo ViewResolver và Thymeleaf

### 1. Thymeleaf là gì?
Thymeleaf là một Engine render View trong Spring. Dễ hiểu hơn, Java lấy được dữ liệu động từ DB, nhưng HTML thì nó "tĩnh". Làm sao để kết hợp lại? Ta dùng Thymeleaf. Thymeleaf dùng HTML thông thường nhưng chèn thêm các "Tag" logic riêng (`th:text`, `th:each`) và trả code chuẩn HTML hoàn thiện xuống trình duyệt.

### 2. Truyền dữ liệu Controller -> View
Trong `Controller`, bạn sẽ truyền vào `org.springframework.ui.Model`. Mọi thứ add vào model bằng `model.addAttribute("key", value)` sẽ đẩy được xuống cho Thymeleaf.
Dùng `${key}` để đánh giá biểu thức trong Thymeleaf.

Và đây là lúc chúng ta thực hành! 
*(Vui lòng xem các file code được sinh ra gồm `CategoryController`, `ApiDemoRestController`, Thymeleaf để kiểm chứng lý thuyết này...)*
