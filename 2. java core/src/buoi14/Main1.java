package buoi14;

import buoi14.service.ClassService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
public class Main1 {

    public static void main(String[] args) {
        /**
         Tạo 500 thread chạy đồng thời
         mỗi thread gọi đến phương thức showAllClasses() để lấy ra danh sách tất cả các lớp học và hiển thị thông tin của chúng.
            Mục đích mở thật nhiều 10000 * 500 = 5 triệu kết nối đến mysql để xem mysql có thể xử lý được khoogn ?
         */
        ClassService classService = new ClassService();
        ExecutorService executor = Executors.newFixedThreadPool(500);
        for (int i = 0; i < 10000; i++) {
            executor.execute(() -> {
                classService.showAllClasses();
            });
        }
        executor.shutdown();
    }
}
