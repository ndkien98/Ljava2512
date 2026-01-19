package buoi9.demo2;

/**
 Exception:
    là các ngoại lệ lỗi có thể xảy ra đã được tính đến hoặc chưa được tính đến trong quá trình thực thi chương trình
    khi một ngoại lệ (exceoption) xảy ra có thể làm cho chương trình bị dừng độ ngột gây ra:
        + thiệt hại cho doanh nghiệp
        + giảm trải nghiệm người dùng
        + sai lệch dữ liệu hệ thống
        + Hỏng hoặc dừng hoạt động của ứng dụng
        + Ảnh hưởng rất nhiều đến uy tín, tài chính của doanh nghiệp và người sử dụng

 2. Hệ thống phân cấp các class để xử lý các ngoại lệ
        + Java đã định nghĩa sẵn rất nhiều class để đại diện có các loại ngoại lệ khác nhau, phục vụ việc sử dụng để đại diện cho các ngoại lệ cần xử lý
        trong quá trình phát triển hoặc thực thi chương trình.
        + Tại sao cần định nghĩa ra các class để xử lý exception?
            - Giúp phân loại các ngoại lệ khác nhau
            - Giúp người lập trình dễ dàng nhận biết và xử lý các ngoại lệ cụ thể
            - Giúp tổ chức mã nguồn tốt hơn, dễ bảo trì và mở rộng
            - Khi xảy ra ngoại lệ cần thể hiện cho hệ thống biết đó loại ngoại lệ gì để có cách xử lý
 => Java đã tạo ra 1 cây phân cấp các class để biểu diễn các loại ngoại lệ khác nhau,
    + Đầu tiên to nhất tất cả các class đều phải kế thừa là class Object
    + Trong cây phân cấp các class xử lý ngoại lệ có 1 class gốc kế thừa từ class Object đó là class Throwable
    + từ class Throwable sẽ có 2 nhánh con kế thừa trực tiếp là class Error và class Exception
        - class Error:
            + đại diện cho các lỗi nghiêm trọng xảy ra trong quá trình thực thi chương trình
            + thường do môi trường chạy (JVM)
            + bộ nhớ đầy, lỗi đọc ghi phần cứng, lỗi hệ thống
            ex:
                OutOfMemoryError: lỗi hết bộ nhớ sảy ra khi JVM không đủ bộ nhớ để cấp phát cho chương trình sử dụng
                StackOverflowError: lỗi tràn ngăn xếp
                OutOfHeapSpaceError: lỗi hết bộ nhớ heap
        - class Exception:
            + đại diện cho các ngoại lệ có thể xảy ra trong quá trình thực thi chương trình(run time) hoặc trong quá trình biên dịch mã nguồn(compile time)
            + thường do lỗi lập trình hoặc điều kiện không mong muốn trong quá trình chạy chương trình
            + các ngoại lệ này có thể được xử lý và khắc phục để chương trình tiếp tục chạy
            + các ngoại lệ phổ biến thường gặp:
                + IOEception: lỗi nhập xuất dữ liệu, file chưa tồn tại
                + SqlException: lỗi liên quan đến cơ sở dữ liệu, sai cú pháp truy vấn, kết nối thất bại, sai thông tin đăng nhập
                + ClassNotFoundException: lỗi không tìm thấy class khi sử dụng reflection hoặc nạp
                + NullPointerException: Không khởi tạo đối tượng mà đã sử dụng các tham chiếu của đối tượng đó
                + ArithmeticException: lỗi toán học như chia cho 0
                + ArrayIndexOutOfBoundsException: Lỗi truy cập ngoài phạm vi của mảng

            + Trong class Exception có 2 loại ngoại lệ chính:
                - Checked Exception (ngoại lệ kiểm tra):
                    + là các ngoại lệ sảy ra trong quá trình biên dịch mã nguồn (compile time)
                    + các ngoại lệ này bắt buộc phải được xử lý bằng try-catch hoặc khai báo throws mới có thể biên dịch mã nguồn thành công
                    ví dụ:
                        IOException
                        SQLException
                        ClassNotFoundException
                - Unchecked Exception (ngoại lệ không kiểm tra):
                    + là các ngoại lệ sảy ra trong quá trình thực thi chương trình (run time)
                    + các ngoại lệ này không bắt buộc phải được xử lý hoặc khai báo throws
                    + nếu không được xử lý sẽ làm chương trình bị dừng đột ngột
                    ví dụ:
                        NullPointerException
                        ArithmeticException
                        ArrayIndexOutOfBoundsException

    II. Cách xử lý ngoại lệ trong Java
    2.1: Sử dụng khối try-catch-finally
        + try: chứa mã nguồn có thể phát sinh ngoại lệ
        + catch: Xác định rõ loại ngoại lệ có thể xả ra và cần xử lý, cách xử lý khi ngoại lệ xảy ra để đảm bảo chương trình không bị dừng đột ngột
        + finally: Khối mã luôn luôn được thực thi sau khối try-catch, dùng để giải phóng tài nguyên hoặc thực hiện các công việc dọn dẹp khác
    cú pháp:
        try {
            // Mã nguồn có thể phát sinh ngoại lệ
        } catch (ExceptionType1 e1) {
            // Xử lý ngoại lệ loại ExceptionType1
        } catch (ExceptionType2 e2) {
            // Xử lý ngoại lệ loại ExceptionType2
        } finally {
            // Mã nguồn luôn được thực thi
        }

    2.2: Sử dụng từ khấo throws
        + được sử dụng trong khai báo phương thức để chỉ ra rằng phương thức đó có thể tạo ra một hoặc nhiều loại ngoại lệ
        + khi một phương thức khai báo throws một ngoại lệ, bất kỳ nơi nào gọi phương thức đó phải xử lý ngoại lệ đó bằng try-catch hoặc tiếp tục khai báo throws ra ngoài
    Cú pháp:
        public void methodName() throws ExceptionType1, ExceptionType2 {
            // Mã nguồn có thể phát sinh ngoại lệ
        }

    2.3: Sử dụng từ khóa throw
        + được sử dụng để ném một ngoại lệ cụ thể trong mã nguồn tại logic sử xử lý cụ thể
        + thường được sử dụng để ném các ngoại lệ tùy chỉnh hoặc để tái ném các ngoại lệ đã bắt được trong khối catch
    Cú pháp:
        throw new ExceptionType("Thông điệp lỗi");

    III. Tạo ngoại lệ tùy chỉnh trong Java
       + Java cho phép developer có thể tự tạo ra các class ngoại lệ riêng phù hợp với từng yêu cầu logic cụ thể của ứng dụng
       + để tạo ra một ngoại lệ tùy chỉnh, ta cần tạo một class mới kế thừa từ class Exception hoặc một trong các lớp con của nó
            nếu muốn tạo ra một checked exception => kế thừa từ class Exception
            nếu muốn tạo ra một unchecked exception => kế thừa từ class RuntimeException
 */

public class Main{


    public static void main(String[] args) {

        System.out.println("Nhập vào số nguyên dương n:");
        int n = 0;
        try {
            n = new java.util.Scanner(System.in).nextInt();
        } catch (Exception e) {
            System.out.println("Lỗi: Vui lòng nhập vào một số nguyên hợp lệ.");
            n = -1;
        }finally {
            System.out.println("Cảm ơn bạn đã nhập");
        }
        while (n < 0) {
            System.out.println("Số nguyên dương n phải lớn hơn hoặc bằng 0. Vui lòng nhập lại:");
            try {
                n = new java.util.Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.out.println("Lỗi: Vui lòng nhập vào một số nguyên hợp lệ.");
                n = -1;
            }finally {
                System.out.println("Cảm ơn bạn đã nhập");
            }
        }
        System.out.println("Số nguyên dương bạn vừa nhập là: " + n);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
