package buoi14;

import buoi14.service.StudentService;

/**

 Bài tập: Tạo một ứng dụng Java để quản lý sinh viên sử dụng JDBC để kết nối đến MySQL. Ứng dụng sẽ thực hiện các chức năng sau:
     1. Kết nối đến cơ sở dữ liệu MySQL.
     2. Thực hiện truy vấn để lấy danh sách sinh viên từ bảng "sinh_vien" và hiển thị thông tin của họ.
     3. Thêm một sinh viên mới vào bảng "sinh_vien", nhập vào tên lớp học để tự tìm kiếm lớp học, nếu không có sẽ để ""
     4. Cập nhật thông tin của một sinh viên đã tồn tại.
     5. Xóa một sinh viên khỏi bảng "sinh_vien".
     6. Tìm kiếm sinh viên theo tên hoặc mã sinh viên.
         Lấy ra toàn bộ thông tin chi tiết của sinh viên bao gồm:
         1. Mã sinh viên (ma_sv)
         2. Họ tên (ho_ten)
         3. Ngày sinh (ngay_sinh)
         4. Email (email)
         5. Học phí (hoc_phi)
         6. Tên lớp đang theo học
         7. Danh sach các môn học và điểm của mỗi môn học

 các package:
    dao (Data access object): package chuyên chứa các class giao tiếp với cơ sở dữ liệu
    entity: package chuyên chứa các class mô tả đối tượng trong cơ sở dữ liệu, mapping dữ liệu với bảng trong cơ sở dữ liệu
    service: package chuyên chứa các class xử lý logic nghiệp vụ, giao tiếp với dao để lấy dữ liệu và xử lý dữ liệu
    utils: package chuyên chứa các class tiện ích, ví dụ như class để kết nối đến cơ sở dữ liệu
 */
public class Main {

    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        studentService.addStudent();
    }
}
