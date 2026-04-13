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

## 2) Yêu cầu chức năng
### 2.1. Danh sách User
- URL gợi ý: `GET /admin/users`
- Hiển thị bảng dữ liệu:
  - Avatar, Full name, Email, Gender, Birthday, Role, Created At
- Có:
  - Tìm kiếm theo keyword (full_name/email)
  - Lọc theo role (ALL/USER/ADMIN)
  - Phân trang (bonus)

### 2.2. Tạo User
- URL gợi ý:
  - `GET /admin/users/create` (mở form)
  - `POST /admin/users/create` (submit)
- Validate tối thiểu:
  - email bắt buộc + đúng format + không trùng
  - fullName bắt buộc
  - password bắt buộc (khi tạo)

### 2.3. Sửa User
- URL gợi ý:
  - `GET /admin/users/{id}/edit`
  - `POST /admin/users/{id}/edit`
- Cho phép cập nhật:
  - fullName, birthday, gender, role, avatar
  - password: tùy chọn (nếu nhập mới thì cập nhật hash)

### 2.4. Xoá User
- URL gợi ý: `POST /admin/users/{id}/delete`
- Yêu cầu xác nhận trước khi xoá (UI)

---

## 3) Ràng buộc kỹ thuật (để học viên tự làm)
### 3.1. Model đề xuất
Tạo class `vn.edu.t3h.model.User` tương ứng bảng `users`.

### 3.2. Repository/Service
- `UserRepository`: dùng `JdbcTemplate` (tương tự `ProductRepository`)
- `UserService`: gom logic validate/hash/CRUD

### 3.3. Controller
- `UserController` trong package `vn.edu.t3h.controller` (để component-scan thấy)
- Return view JSP theo danh sách bên dưới.

### 3.4. Hash password
- Không lưu password plain text.
- Có thể hash bằng **MD5** (đang dùng trong seed admin của project) hoặc nâng cao hơn (BCrypt) tuỳ lớp học.

---

## 4) UI mẫu (HTML có sẵn) → học viên tự chuyển sang JSP
### 4.1. Vị trí UI HTML template
UI mẫu đã được chuẩn bị sẵn dưới dạng HTML tĩnh (để copy/paste sang JSP):

- `src/main/webapp/ui-assignment/admin/user_list.html`
- `src/main/webapp/ui-assignment/admin/user_form.html`

Bạn có thể mở trực tiếp trên server:
- `/ui-assignment/admin/user_list.html`
- `/ui-assignment/admin/user_form.html`

### 4.2. Nhiệm vụ của học viên
1) Tạo JSP thật cho chức năng admin user:
- `src/main/webapp/WEB-INF/views/admin/user_list.jsp`
- `src/main/webapp/WEB-INF/views/admin/user_form.jsp`

2) Copy UI từ HTML template vào JSP, sau đó thay data tĩnh bằng JSTL:
- Thay các dòng sample như “8 users”, các `<tr>` mẫu… bằng:
  - `<c:forEach ...>` để render danh sách
  - `<c:out ...>` để in dữ liệu an toàn
  - `<c:if ...>` / `<c:choose ...>` để xử lý hiển thị có điều kiện

3) Nối form action/URL thật (Spring MVC):
- List: `GET /admin/users`
- Create: `GET|POST /admin/users/create`
- Edit: `GET|POST /admin/users/{id}/edit`
- Delete: `POST /admin/users/{id}/delete` (gợi ý: dùng modal confirm)

### 4.3. Model attributes gợi ý để truyền sang JSP
#### user_list.jsp
- `users`: `List<User>`
- `keyword`: `String` (optional)
- `role`: `String` (optional)
- `successMessage` / `errorMessage`: `String` (optional)

#### user_form.jsp
- `mode`: `"create" | "edit"`
- `user`: `User` (khi edit)
- `errors`: `Map<String,String>` (optional)

> Lưu ý: file JSP hiện tại trong project có thể đang là placeholder. Học viên cần **tự thay bằng JSP thật** dựa trên HTML template.

---

## 5) Tiêu chí chấm điểm (gợi ý)
- (2đ) Render được danh sách users
- (2đ) Tạo user + validate cơ bản
- (2đ) Sửa user
- (2đ) Xoá user
- (1đ) Search + filter
- (1đ) UI/UX đẹp (responsive, có empty state, confirm delete)

---

## 6) Gợi ý cấu trúc URL/View
| Action | Method | URL | View |
|---|---|---|---|
| List | GET | `/admin/users` | `admin/user_list` |
| Create form | GET | `/admin/users/create` | `admin/user_form` |
| Create submit | POST | `/admin/users/create` | redirect list |
| Edit form | GET | `/admin/users/{id}/edit` | `admin/user_form` |
| Edit submit | POST | `/admin/users/{id}/edit` | redirect list |
| Delete | POST | `/admin/users/{id}/delete` | redirect list |

---

## 7) Lưu ý
- Không để lộ `password_hash` ra UI.
- Khi edit: password là tuỳ chọn; nếu để trống thì giữ nguyên password_hash cũ.
- Nên validate serverside 100% (không chỉ dựa vào HTML required).
