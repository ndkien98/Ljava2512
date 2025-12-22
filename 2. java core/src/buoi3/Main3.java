package buoi3;

/**
 Vòng lặp trong java:
    Được sử dụng để thực hiện một công việc, khối lệnh lặp đi lặp lại nhiều lần cho đến khi một điều kiện nào đó không còn đúng nữa.
    Các loại vòng lặp:
        vòng lặp for
        vòng lặp while
        vòng lặp do while
 => điểm chung:
    Tất cả các vòng lặp đều bao gồm 3 thành phần chính:
         - Khởi tạo biến: được sử dụng để khởi tạo giá trị ban đầu cho biến điều khiển vòng lặp.
         - Điều kiện dừng: là biểu thức logic được kiểm tra trước mỗi lần lặp, nếu điều kiện đúng (true) thì vòng lặp tiếp tục thực hiện khối lệnh trong for, nếu sai (false) thì vòng lặp kết thúc.
         - Bước nhảy: được sử dụng để cập nhật giá trị của biến điều khiển sau mỗi lần lặp, thường là tăng hoặc giảm giá trị của biến điều khiển
 1. Cấu trúc vòng lặp for:
    for( khởi tạo biến; điều kiện dừng; bước nhảy ){
        // khối lệnh thực hiện trong vòng lặp
    }
    - Khởi tạo biến: được sử dụng để khởi tạo giá trị ban đầu cho biến điều khiển vòng lặp.
    - Điều kiện dừng: là biểu thức logic được kiểm tra trước mỗi lần lặp, nếu điều kiện đúng (true) thì vòng lặp tiếp tục thực hiện khối lệnh trong for, nếu sai (false) thì vòng lặp kết thúc.
    - Bước nhảy: được sử dụng để cập nhật giá trị của biến điều khiển sau mỗi lần lặp, thường là tăng hoặc giảm giá trị của biến điều khiển
 Ví dụ 1:

 2. Cấu trúc vòng lặp while:
    - khởi tạo biến: được sử dụng để khởi tạo giá trị ban đầu cho biến điều khiển vòng lặp.
    while( điều kiện dừng ){
        // khối lệnh thực hiện trong vòng lặp
        // bước nhảy
    }

    - khởi tạo biến: biến được khởi tạo bên ngoài vòng lap while
    - điều kiện dừng: là biểu thức logic được kiểm tra trước mỗi lần lặp bang biến được tạo bên ngoài vòng lặp,
        nếu điều kiện đúng (true) thì vòng lặp tiếp tục thực hiện khối lệnh trong for, nếu sai (false) thì vòng lặp kết thúc.
    - bước nhảy: được sử dụng để cập nhật giá trị của biến điều khiển sau mỗi lần lặp, thường là tăng hoặc giảm giá trị của biến điều khiển
    Ví dụ 2:
 3. cấu trúc vòng lặp do-while
    do {
        // khối lệnh thực hiện trong vòng lặp
        // bước nhảy
    } while( điều kiện dừng );

    - Khởi tạo biến: biến được khởi tạo bên ngoài vòng lap do-while
    - Khác vơi 2 vòng lặp trên, vòng lặp do-while sẽ luôn được thực hiện ít nhất 1 lần đầu tiên trước khi kiểm tra biểu thức điều kiện dừng
        (bất kể điều kiện dừng đúng hay sai)
    - Điều kiện dừng: là biểu thức logic được kiểm tra sau mỗi lần lặp bằng biến được tạo bên ngoài vòng lặp
    - Bước nhảy: được sử dụng để cập nhật giá trị của biến điều khiển sau mỗi lần lặp, thường là tăng hoặc giảm giá trị của biến điều khiển
    Ví dụ 3:

 Các từ khóa trong vòng lặp:
    continue:
        - được sử dụng để bỏ qua các câu lệnh còn lại trong vòng lặp hiện tại và chuyển sang lần lặp tiếp theo.
        - khi gặp từ khóa continue, vòng lặp sẽ ngay lập tức dừng thực hiện, và chuyển sang vòng lặp tiếp theo (nếu điều kiện dừng vẫn đúng)
    break:
        - Được sử dụng khi muốn thoát khoải vòng lặp ngay lập tức, không cần kiếm tra điều kiện dừng nữa

 Ví dụ 4: in ra các số chẵn trong dãy từ 1-20., khi gặp số 10 vòng lặp bắt buộc dừng


 */
public class Main3 {

    public static void main(String[] args) {

        System.out.println("VD1 In ra màn hình các số từ 1 đến 10");
        int n = 10;
        for (int i=0;i< n;i++){
            System.out.println("Số thứ "+ (i+1));
        }

        System.out.println("VD2 in ra màn hinh các số từ 1 đến 10 sử dụng vòng lặp while");
        int j = 0;
        while (j < n){
            System.out.println("Số thứ "+ (j+1));
            j++;
        }
        System.out.println("Ví dụ 3: in ra màn hinh các số từ 1 đến 10 sử dụng vòng lặp do-while");
        int k = 1;
        do {
            System.out.println("Số thứ "+ (k));
            k++;
        }while (k <= 10);

        System.out.println("Ví dụ 4: in ra các số chẵn trong dãy từ 1-20, khi gặp số 10 vòng lặp bắt buộc dừng ");
        int m = 20;
        for (int i=0;i<m;i++){
            if (i % 2 != 0){
                continue;
            }
            if (i == 10){
                break;
            }
            System.out.println("Số chẵn: "+ i);
        }
    }
}
