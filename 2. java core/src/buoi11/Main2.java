package buoi11;

/**
 Thread, MultiThreading trong Java
    - Chương trình (Program): 1 tập hợp các câu lệnh để thực hiện 1 nhiệm vụ nào đó, ví dụ chương máy tính của ứng dụng intellij, word, excel..
    - Tiến trình (Process): là 1 đơn vị xử lý trong hệ điều hành của chương trình tạo ra, 1 chương trình có thể tạo ra nhiều tiến trình khác nhau khi chạy.
        Ví dụ: khi bạn mở nhiều cửa sổ intellij, mỗi cửa sổ sẽ là 1 tiến trình riêng biệt.
    - Luồng (Thread): là 1 đơn vị xử lý nhỏ nhất trong tiến trình, mỗi tiến trình có thể có nhiều thread khác nhau.
        Ví dụ:
            Trong 1 tiến trình nghe nhạc app zingmp3 có thể có các thread xử lý cùng nhau như sau:
                + Thread 1: Hiển thị giao diện người dùng (UI)
                + Thread 2: Xử lý phát nhạc song song trong quá trình user thao tác
                + Thread 3: Tải dữ liệu từ app về

    - Đa luồng (MultiThreading):
        + là 1 kỹ thuật tuận dụng tối đa khả năng xử lý đa nhiệm của core CPU. Giúp thực hiện nhiều công việc cùng 1 lúc.
        + ưu điểm:
            + Tăng hiệu suất xứ lý cùng 1 hoặc nhiều nhiệm vụ
            + Cải thiện trải  nhiệm người dùng
            + Giảm thời gian chờ đợi
        + Nhược điểm:
            + Quản lý luồng phực tạp. Nếu không quán lý tốt có thể dẫn đến các lỗi:
                + race condition:
                     - Có 2 thread cùng truy cập thay đổi 1 biến chung mà không có xử lý đồng bộ lần lượt các thread. Dẫn đến không thể kiếm soát được
                        dữ liệu được trả về của biến chung đó.
                    ví dụ: 2 thread cùng update biến count từ 0 lên 1, nếu không có xử lý đồng bộ thì biến count có thể bị ghi đè dẫn đến giá trị
                            cuối cùng của biến count không đúng với số lần được update.
                    - Giải pháp:
                        - Sử dụng cơ chế đồng bộ hóa(synchronization) để đảm bảo bảo trong 1 thời điểm chỉ được có 1 thread duy nhất được truy cập vào tài nguyên hệ thống
                + deadlock:
                    - Là tình trạng 2 thread cùng nắm giữ tài nguyên của nhau và chờ nhau giải phóng tài nguyên mới thực thi
                    Thread A: nắm giữ tài nguyên R1 và chờ tài nguyên R2 giải phóng mới thực thi
                    Thread B: nắm giữ tài nguyên R2 và chờ tài nguyên R1 giải phóng mới thực thi
                    => Kết quả: cả 2 thread đều không thể thực thi được vì đang Thread A đang chờ Thread B giải phóng tài nguyên R2 và ngược lại.
                    => Giải pháp:
                        + Nếu sảy ra rồi thì bắt buộc phải kill 1 trong 2 thread để giải phóng tài nguyên. Chọn kill thread nào ít ảnh hưởng dữ liệu
                        + Xử lý cần xét thời gian timeout, nếu trong 1 thời gian nhất định ko xử lý xong, bắt buộc phải giải phóng tài nguyên.
            + Khởi tạo 1 thread rất tốn tài nguyên hệ thống, khi khởi tạo 1 thread sẽ tốn 1MB cho bộ nhớ RAM để cấp phát và lưu trữ thông tin thread đó
                => nếu không kiếm soát số lượng thread được mở ra sẽ gây ra tình trạng quá tải bộ nhớ, làm chậm hệ thống.
            + Việc gỡ lỗi (debug) trong môi trường đa luồng phức tạp hơn so với môi trường đơn luồng.
            + Context Switching (Chuyển đổi ngữ cảnh):
                + Quá trình này xảy ra khi CPU phải chuyển đổi qua lại giữa nhiều thread hoặc các tính trình khác nhau.
                + Mỗi lần chuyển đổi context này sẽ tồn rất nhiều thời gian, tài nguyên để setup, init lại các trạang thái của thread hoặc tiến trình
                vì bản chất CPU chỉ có 1 số lượng core nhất định. Tại 1 thời điểm sẽ chỉ có 1 thread được 1 core xử lý.
                nếu có quá nhiều thread thì CPU sẽ bắt các thread phải chờ để được xử lý và sẽ phải context switch liên tục giữa các thread với nhau. gây ra tình
                trạng tốn tài nguyên làm chậm hơn cả siger threading.
                Giải pháp:
                    + Quản lý số lượng thread được tạo ra, Không tạo 1 cách bừa bãi
                    + Áp dụng thread pool để quản lý và tái sử dụng 1 số lượng thread nhất định
                Thread pool: là 1 tập hợp các thread đã được khởi tạo sẵn và sẵn sàng để thực thi các nhiệm vụ khi có yêu cầu.
                    Khi có nhiệm vụ mới, thay vì tạo mới 1 thread, hệ thống sẽ lấy 1 thread từ pool để thực thi nhiệm vụ đó.
                    Sau khi hoàn thành nhiệm vụ, thread sẽ được trả về pool để tái sử dụng cho các nhiệm vụ sau.
                ví dụ:
                    tạo 1 thread pool có 10 thread:
                        -> luôn lấy các thread trong pool để thực thi các nghiệm vụ. Khi nào có thread rảnh sẽ được sử dụng luôn.
                    + Ưu điểm:
                        - Giảm overhead tài nguyên khi phải tạo và hủy thread liên tục
                        - Kiểm soát được số lượng thread tối đa trong hệ thống


 */

public class Main2 {

    public static void main(String[] args) {
        System.out.println("Main thread started");
        // Ví dụ tạo 3 Thread để in số từ 1 đến 5
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread 1: " + i);
                try {
                    Thread.sleep(100); // Giả lập thời gian xử lý của method nào đó mất khoảng 100ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread 2: " + i);
                try {
                    Thread.sleep(100); // Giả lập thời gian xử lý của method nào đó mất khoảng 100ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread 3: " + i);
                try {
                    Thread.sleep(100); // Giả lập thời gian xử lý của method nào đó mất khoảng 100ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("Starting threads...");
        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println("Main thread ended");
    }
}
