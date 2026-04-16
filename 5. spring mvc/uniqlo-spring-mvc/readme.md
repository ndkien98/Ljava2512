

- controller: chứa các class controller của service 
- services: chứa các class service xử lý logic nghiệp vụ của service
  - impl: chứa các class implementation của service, các class này sẽ implement các interface trong thư mục services, phục vụ DI cho các controller
- repositories: chứa các class repository để tương tác với database
  - impl: chứa các class implementation của repository, các class này sẽ implement các interface trong thư mục repositories, phục vụ DI cho các service
- models: chứa các class model đại diện cho các bảng trong database, các dữ liệu qua các layer của service 
- exceptions: chứa các class exception tự định nghĩa để xử lý lỗi trong service\
- utils: chứa các class tiện ích, các hàm hỗ trợ cho service
- config: chứa các class cấu hình cho service, như cấu hình database, cấu hình logging, cấu hình các biến môi trường, cors, v.v...
- mappers: chứa các class mapper để chuyển đổi giữa các các model thành các đối tượng khác, như DTO, response, v.v...
- dto: chứa các class DTO (Data Transfer Object) để truyền dữ liệu giữa các layer của service, hoặc giữa các service với nhau
- resources: chứa các file tài nguyên như file SQL để tạo bảng, file cấu hình, v.v...
- webapp: chứa các file cấu hình cho webapp, như file cấu hình Spring MVC, file view resolver, v.v...
