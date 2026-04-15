# Bài tập: Xây dựng chức năng **Quản lý User** (Spring MVC + JSP + JDBC)

## 1) Bối cảnh dự án
Project đang dùng:
- Spring MVC (Servlet-based, cấu hình XML)
- JSP + JSTL
- JDBC (Spring JdbcTemplate) + MySQL

Database có bảng `users` (trích theo `database.sql` / `database_authen.sql`):

| Cột | Kiểu | Ghi chú |
|---|---|---|
| `id` | int, PK, auto_increment | định danh |
| `full_name` | varchar(100) | tên hiển thị |
| `email` | varchar(255) | **unique**, bắt buộc |
| `password_hash` | varchar(255) | bắt buộc (lưu hash) |
| `birthday` | date | tùy chọn |
| `gender` | enum('Male','Female','Decline to state') | tùy chọn |
| `role` | varchar(20) | mặc định `USER` (gợi ý: USER/ADMIN) |
| `remember_token` | varchar(255) | tùy chọn |
| `avatar` | varchar(500) | url ảnh đại diện |
| `created_at` | timestamp | auto |
| `updated_at` | timestamp | auto |

Mục tiêu bài tập: **CRUD users** + giao diện quản trị đẹp bằng JSP. Phần dữ liệu (repository/service/controller) học viên tự lắp.

---

## 2) Yêu cầu kiến trúc (bắt buộc) — theo rule 3-layer
Bài này phải tuân theo mô hình 3-layer rõ ràng:
- **Controller layer**: chỉ nhận request/response, validate input, gọi Service; **không viết business logic**
- **Service layer**: chứa business logic (validate, hash password, rule edit/create...), gọi Repository
- **Repository layer**: thao tác DB bằng `JdbcTemplate` (query/insert/update/delete)

Ngoài ra tạo thêm package hỗ trợ (cùng tinh thần rule):
- `dto` (request/response)
- `mapper` (convert DTO ↔ Model)
- `exception` (custom exception, xử lý lỗi/validation)
- `util` (hash, helper)

> Lưu ý: project hiện tại là **Spring MVC XML + JdbcTemplate**, không phải Spring Boot/JPA. Tuy nhiên cấu trúc package + nguyên tắc phân lớp **vẫn áp dụng tương tự**.

---

## 3) Cấu trúc thư mục/package yêu cầu
Tạo các file theo cấu trúc sau (dùng base package của project: `vn.edu.t3h`):

```
src/main/java/vn/edu/t3h
│
├── controller
│   └── AdminUserController.java
│
├── service
│   ├── UserService.java
│   └── impl
│       └── UserServiceImpl.java
│
├── repository
│   ├── UserRepository.java
│   └── impl
│       └── UserRepositoryImpl.java
│
├── model
│   └── User.java
│
├── dto
│   ├── request
│   │   ├── UserCreateRequest.java
│   │   └── UserUpdateRequest.java
│   └── response
│       └── UserListItemResponse.java
│
├── mapper
│   └── UserMapper.java
│
├── exception
│   ├── NotFoundException.java
│   └── ValidationException.java
│
└── util
    └── PasswordUtil.java
```

### Quy tắc đặt tên (bắt buộc)
- Controller: `XxxController`
- Service: `XxxService` + `impl/XxxServiceImpl`
- Repository: `XxxRepository` + `impl/XxxRepositoryImpl`
- DTO: `XxxRequest` / `XxxResponse`
- Mapper: `XxxMapper`

---

## 4) Yêu cầu chức năng
### 4.1. Danh sách User
- URL gợi ý: `GET /admin/users`
- Hiển thị bảng dữ liệu:
  - Avatar, Full name, Email, Gender, Birthday, Role, Created At
- Có:
  - Tìm kiếm theo keyword (full_name/email)
  - Lọc theo role (ALL/USER/ADMIN)
  - Phân trang (bonus)

### 4.2. Tạo User
- URL gợi ý:
  - `GET /admin/users/create` (mở form)
  - `POST /admin/users/create` (submit)
- Validate tối thiểu:
  - email bắt buộc + đúng format + không trùng
  - fullName bắt buộc
  - password bắt buộc (khi tạo)

### 4.3. Sửa User
- URL gợi ý:
  - `GET /admin/users/{id}/edit`
  - `POST /admin/users/{id}/edit`
- Cho phép cập nhật:
  - fullName, birthday, gender, role, avatar
  - password: tùy chọn (nếu nhập mới thì cập nhật hash)

### 4.4. Xoá User
- URL gợi ý: `POST /admin/users/{id}/delete`
- Yêu cầu xác nhận trước khi xoá (UI)

---

## 5) Ràng buộc kỹ thuật chi tiết theo từng layer
### 5.1. Model
Tạo `vn.edu.t3h.model.User` map theo bảng `users`.
- Không expose `password_hash` ra UI.

### 5.2. Repository layer (JdbcTemplate)
- `UserRepository` (interface): khai báo các hàm CRUD + search/filter
- `UserRepositoryImpl` (class): inject `JdbcTemplate`, viết SQL.

Gợi ý các hàm:
- `List<User> findAll(String keyword, String role)`
- `User findById(int id)`
- `User findByEmail(String email)`
- `int insert(User user)`
- `int update(User user)`
- `int deleteById(int id)`

> Query phức tạp/search/filter đặt ở RepositoryImpl.

### 5.3. Service layer
- `UserService` (interface)
- `UserServiceImpl` (implementation)

Nhiệm vụ service:
- Validate business (email unique, required fields...)
- Hash password khi create / khi update có nhập password mới
- Decide rule edit (password bỏ trống → giữ nguyên)
- Throw `NotFoundException` / `ValidationException` khi sai

### 5.4. Controller layer
Tạo `AdminUserController` trong `vn.edu.t3h.controller`.
- Chỉ:
  - nhận params/modelAttribute
  - gọi `UserService`
  - return view/redirect
- Không viết SQL/không hash password trong controller.

---

## 6) Hash password
- Không lưu password plain text.
- Có thể hash bằng **MD5** (đang dùng trong seed admin của project) hoặc nâng cao hơn (BCrypt nếu tự tích hợp).
- Đặt logic hash trong `util/PasswordUtil` và chỉ gọi từ Service.

---

## 7) UI mẫu (HTML có sẵn) → học viên tự chuyển sang JSP
### 7.1. Vị trí UI HTML template
UI mẫu đã được chuẩn bị sẵn dưới dạng HTML tĩnh (để copy/paste sang JSP):

- `src/main/webapp/ui-assignment/admin/user_list.html`
- `src/main/webapp/ui-assignment/admin/user_form.html`

Bạn có thể mở trực tiếp trên server:
- `/ui-assignment/admin/user_list.html`
- `/ui-assignment/admin/user_form.html`

### 7.2. Hướng dẫn học viên dùng HTML để làm JSP (bắt buộc)
1) Tạo JSP thật:
- `src/main/webapp/WEB-INF/views/admin/user_list.jsp`
- `src/main/webapp/WEB-INF/views/admin/user_form.jsp`

2) Copy toàn bộ HTML từ template vào JSP.

3) Sửa đường dẫn asset trong JSP (nếu có) theo contextPath:
- Dùng: `${pageContext.request.contextPath}`

4) Thay dữ liệu tĩnh bằng JSTL/EL:
- Bảng list:
  - `<c:forEach ...>` render danh sách
  - `<c:out ...>` in dữ liệu an toàn
  - Empty state: `<c:if test="${empty users}">...`
- Form:
  - set value từ `${user.fullName}`, `${user.email}`...
  - hiển thị lỗi validate (nếu có) từ attribute `errors`

5) Nối URL thật theo mapping controller:
- List: `GET /admin/users`
- Create: `GET|POST /admin/users/create`
- Edit: `GET|POST /admin/users/{id}/edit`
- Delete: `POST /admin/users/{id}/delete`

### 7.3. Model attributes gợi ý
#### user_list.jsp
- `users`: `List<UserListItemResponse>` hoặc `List<User>`
- `keyword`: `String` (optional)
- `role`: `String` (optional)
- `successMessage` / `errorMessage`: `String` (optional)

#### user_form.jsp
- `mode`: `"create" | "edit"`
- `user`: `User` hoặc DTO response
- `errors`: `Map<String,String>` (optional)

---

## 8) Tiêu chí chấm điểm (gợi ý)
- (2đ) Render được danh sách users (JSTL)
- (2đ) Tạo user + validate cơ bản + hash password
- (2đ) Sửa user đúng rule (password optional)
- (2đ) Xoá user + confirm UI
- (1đ) Search + filter
- (1đ) Tuân thủ đúng 3-layer + interface/impl + DTO/Mapper

---

## 9) Checklist nộp bài
- [ ] Có đủ package theo mục (3)
- [ ] Controller không chứa SQL/hash
- [ ] Service có interface + impl
- [ ] Repository có interface + impl
- [ ] Có DTO request/response và mapper
- [ ] Có `PasswordUtil`
- [ ] JSP được dựng từ HTML template và thay data bằng JSTL
- [ ] Không render `password_hash` ra UI
