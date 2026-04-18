# Bài Giảng: Kiến Trúc Hybrid Identity với Keycloak

> **Dành cho:** Kỹ sư Backend / Fullstack muốn hiểu sâu về hệ thống xác thực hiện đại  
> **Công nghệ:** Keycloak 24+, Spring Boot 3.x, OAuth2 / OIDC

---

## Mục lục

1. [Tổng quan về Hybrid Identity](#1-tổng-quan-về-hybrid-identity)
2. [Cơ chế Identity Brokering của Keycloak](#2-cơ-chế-identity-brokering-của-keycloak)
3. [Kiến trúc luồng dữ liệu: Social Login vs. Local Account](#3-kiến-trúc-luồng-dữ-liệu)
4. [Cấu hình Google & GitHub làm Identity Provider](#4-cấu-hình-google--github-làm-identity-provider)
5. [First Broker Login Flow & Account Linking](#5-first-broker-login-flow--account-linking)
6. [Đăng ký tài khoản Local (User Registration)](#6-đăng-ký-tài-khoản-local)
7. [Map Roles vào Spring Security (@PreAuthorize)](#7-map-roles-vào-spring-security)
8. [CORS và cách xử lý khi Frontend gọi API](#8-cors)
9. [Keycloak Admin Client: Tự động quản lý User từ Java](#9-keycloak-admin-client)

---

## 1. Tổng quan về Hybrid Identity

**Hybrid Identity** là mô hình trong đó ứng dụng của bạn vừa cho phép người dùng đăng nhập bằng tài khoản nội bộ (username/password lưu trong Keycloak), vừa có thể sử dụng tài khoản Social (Google, GitHub, Facebook...). 

Toàn bộ quá trình này được **chuẩn hoá bởi Keycloak**, ứng dụng backend (Spring Boot) của bạn **không hề biết** người dùng đăng nhập bằng cách nào. Nó chỉ nhận một **JWT Token chuẩn** từ Keycloak và xử lý.

```
User ──► Keycloak (Broker) ──► Google/GitHub/Local ──► JWT ──► Spring Boot API
```

---

## 2. Cơ chế Identity Brokering của Keycloak

**Identity Brokering** là khả năng của Keycloak đóng vai trò **trung gian (Broker)** giữa ứng dụng và các Identity Provider (IdP) bên ngoài như Google, GitHub.

### Tại sao cần Identity Broker?

| Không có Broker | Có Keycloak Broker |
|---|---|
| Ứng dụng phải tích hợp riêng với từng IdP | Keycloak xử lý tất cả, ứng dụng chỉ cần biết Keycloak |
| Mỗi IdP có format token khác nhau | Keycloak chuẩn hoá thành 1 JWT duy nhất |
| Khó quản lý User nếu login bằng Google và GitHub | Keycloak có thể **gộp 2 tài khoản về 1** (Account Linking) |
| Logic bảo mật phân tán, khó audit | Tập trung tại 1 điểm, dễ kiểm soát |

### Luồng Broker hoạt động như thế nào?

Khi user click "Đăng nhập bằng Google":

1. Keycloak nhận request từ ứng dụng.
2. Keycloak **chuyển hướng (redirect) sang Google** (lúc này Keycloak là OAuth2 Client của Google).
3. User nhập Gmail/Password trên Google.
4. Google trả về `Authorization Code` cho Keycloak.
5. Keycloak đổi code lấy `id_token` từ Google, xác thực email.
6. Keycloak kiểm tra xem email này đã tồn tại trong DB chưa → **First Broker Login Flow** xử lý việc này.
7. Keycloak tự sinh ra **JWT Token của riêng mình** và trả về cho ứng dụng.

> **Kết quả:** Ứng dụng chỉ nhận JWT của Keycloak, không bao giờ thấy token của Google.

---

## 3. Kiến trúc Luồng Dữ Liệu

### Luồng 1: Đăng nhập qua Social (Google/GitHub)

```
┌─────────────┐   1. Chọn "Login with Google"   ┌───────────────────┐
│   User      │────────────────────────────────►│  Keycloak Server  │
│  (Browser)  │                                  │  (localhost:8080) │
└─────────────┘                                  └────────┬──────────┘
                                                          │ 2. Redirect sang Google
                                                ┌─────────▼──────────┐
                                                │     Google OAuth2   │
                                                │  (accounts.google)  │
                                                └─────────┬──────────┘
                                                          │ 3. Trả Authorization Code
                                                ┌─────────▼──────────┐
                                                │  Keycloak xử lý:   │
                                                │  - Đổi code → IdToken│
                                                │  - Tạo/update user │
                                                │  - Sinh Keycloak JWT│
                                                └─────────┬──────────┘
                                                          │ 4. Trả Keycloak JWT
┌─────────────┐◄────────────────────────────────────────┘
│  Frontend   │  5. Gắn Bearer token vào Header  ┌────────────────────┐
│  (React...)│────────────────────────────────►│ Spring Boot API    │
└─────────────┘   Authorization: Bearer <JWT>  │ (Resource Server)  │
                                                └────────────────────┘
```

### Luồng 2: Đăng ký / Đăng nhập bằng Local Account

```
User ──► Keycloak Login Page ──► Click "Register" ──► Điền form
    ──► Keycloak lưu User trực tiếp vào MySQL ──► Trả Keycloak JWT ──► Spring Boot API
```

**Điểm quan trọng:** MySQL của bạn chạy local là **database chung** cho cả 2 luồng. Tài khoản Social sau khi broker sẽ được lưu vào bảng `USER_ENTITY` trong DB của Keycloak, cùng cơ sở với tài khoản local.

---

## 4. Cấu hình Google & GitHub làm Identity Provider

### 4.1. Google Cloud Console

1. Truy cập [console.cloud.google.com](https://console.cloud.google.com), tạo Project.
2. Vào **APIs & Services** → **OAuth consent screen**: chọn **External**, điền thông tin app.
3. Vào **Credentials** → **Create Credentials** → **OAuth client ID**:
   - Application type: **Web application**
   - Authorized JS origins: `http://localhost:8080`
   - **Authorized redirect URIs:**  
     ```
     http://localhost:8080/realms/uniqlo-realm/broker/google/endpoint
     ```
4. Lưu lại `Client ID` và `Client Secret`.

### 4.2. GitHub Developer Settings

1. Vào [github.com/settings/developers](https://github.com/settings/developers) → **New OAuth App**.
2. Điền:
   - Homepage URL: `http://localhost:8080`
   - **Authorization callback URL:**  
     ```
     http://localhost:8080/realms/uniqlo-realm/broker/github/endpoint
     ```
3. Tạo xong, lấy `Client ID` và generate `Client Secret`.

### 4.3. Cấu hình Identity Provider trong Keycloak Admin UI

1. Login vào Keycloak Admin (`http://localhost:8080`), chọn realm `uniqlo-realm`.
2. Trong menu trái → **Identity Providers** → **Add provider**:
   - Chọn **Google**: Dán Client ID & Secret của Google, lưu lại.
   - Chọn **GitHub**: Dán Client ID & Secret của GitHub, lưu lại.
3. Ở giao diện Login, sẽ xuất hiện ngay 2 nút "Đăng nhập với Google" và "GitHub".

---

## 5. First Broker Login Flow & Account Linking

### Vấn đề: Một người dùng, nhiều tài khoản

Giả sử user `A` đăng ký email `a@gmail.com` qua Local Account. Hôm sau, họ click "Login with Google" cũng bằng email `a@gmail.com`. Điều gì xảy ra?

Mặc định, Keycloak sẽ **tạo tài khoản mới** → Hệ thống có 2 user khác nhau cho cùng 1 người thực.

**First Broker Login Flow** giải quyết điều này.

### Cấu hình Account Linking tự động theo Email

1. Vào **Authentication** → **Flows** → tìm flow **First Broker Login**.
2. Tại step **"Review Profile"** và **"Automatically Link Existing Account by Email"**:
   - Bật (set required) **"Automatically Set Existing User"** hoặc **"Confirm Link Existing Account"**.
3. Có 2 chế độ:
   - **Tự động (Silent Linking):** Nếu tìm thấy user có cùng email → tự liên kết luôn, không hỏi.
   - **Xác nhận (Prompt Linking):** Hiện thông báo hỏi user "Bạn có muốn liên kết tài khoản hiện tại không?".

> **Lưu ý bảo mật:** Chỉ dùng **Silent Linking** khi bạn chắc chắn rằng IdP (Google/GitHub) đã xác thực email. Cả 2 đều xác thực email nên thường là an toàn.

### Kết quả sau Account Linking

```
User A ──► Login with Google (a@gmail.com)
        └──► Keycloak tìm thấy user local a@gmail.com
             └──► Liên kết: user_id trong DB là 1, có 2 identity providers
                            user có thể dùng cả Google lẫn Local để đăng nhập
```

---

## 6. Đăng ký Tài khoản Local (User Registration)

Keycloak mặc định tắt tính năng đăng ký. Để bật:

1. Vào **Realm settings** → Tab **Login**.
2. Bật **User registration**: Trang Login sẽ hiện link "Register".
3. Bật **Verify email**: Sau đăng ký, Keycloak tự gửi email xác nhận.
4. User điền form → Keycloak lưu vào bảng `USER_ENTITY` trong MySQL của bạn.

---

## 7. Map Roles vào Spring Security

### Cấu trúc JWT từ Keycloak

```json
{
  "realm_access": {
    "roles": ["ROLE_USER", "ROLE_ADMIN", "offline_access"]
  },
  "resource_access": {
    "uniqlo-backend": {
      "roles": ["MANAGE_PRODUCTS"]
    }
  },
  "preferred_username": "nguyenvana",
  "email": "nguyenvana@gmail.com",
  "sub": "uuid-user-id"
}
```

### Vì sao phải có Converter tùy chỉnh?

Spring Security mặc định đọc Authorities từ claim `scope` hoặc `scp`. Với cấu trúc lồng nhau `realm_access.roles` của Keycloak, ta **phải viết converter riêng** để Spring hiểu.

### Sử dụng trong Controller

```java
@RestController
@RequestMapping("/api/products")
public class ProductController {

    // Chỉ ADMIN mới được xóa sản phẩm
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { ... }

    // Cả ADMIN và USER đều được xem
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<Product>> getAll() { ... }
}
```

---

## 8. CORS

Khi Frontend (React, Angular) chạy ở `localhost:3000` gọi API Spring Boot ở `localhost:8082`, trình duyệt sẽ chặn request do khác origin.

### Giải pháp: Cấu hình CORS trong SecurityConfig

```java
// Cấu hình này cho phép frontend localhost:3000 tương tác với API
http.cors(cors -> cors.configurationSource(request -> {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of("http://localhost:3000")); // Địa chỉ frontend
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
    config.setAllowCredentials(true);
    return config;
}));
```

> **Quan trọng:** Phải thêm `Authorization` vào `AllowedHeaders` để frontend có thể gửi Bearer token.  
> Trong Keycloak Admin UI cũng cần cấu hình **Web Origins** cho Client là `http://localhost:3000`.

---

## 9. Keycloak Admin Client

Đôi khi ta cần **Spring Boot tự tạo, xóa, cập nhật User** trong Keycloak (ví dụ: tạo tài khoản hàng loạt, reset password, gán role) mà không cần vào UI.

Công cụ: **Keycloak Admin REST Client** (thư viện Java chính thức).

### Dependency

```xml
<dependency>
    <groupId>org.keycloak</groupId>
    <artifactId>keycloak-admin-client</artifactId>
    <version>24.0.4</version>
</dependency>
```

### Cách hoạt động

1. Spring Boot gọi Keycloak Admin REST API (port 8080).
2. Dùng `master` realm hoặc một Service Account (confidential client) để lấy Admin Token.
3. Dùng token này để thực hiện CRUD trên User, Role, Group...

> Xem file `KeycloakAdminService.java` trong source code để thấy ví dụ cụ thể.
