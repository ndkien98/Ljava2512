package buoi3;

import java.util.Scanner;

/**

 Mảng trong Java

    1. Định nghĩa
        Mảng: là một tập hợp các phần tử nằm liên kề nhau trong ô nhớ, có cùng kiểu dữ liệu.
            - trong jAVA mảng là một đối tượng object
            - Các phần tử trong mảng được truy cập thông qua chi số index của mảng
            - Mảng được lưu trữ trong vùng nhớ Heap, bao gồm cả mảng nguyên thủy và mảng các đối tượng
            - Vùng nhớ stack sẽ lưu trữ biến tham chiều trỏ đến vùng nhớ heap chứa mảng
    2. Các tính chất
            - Kích thưởng mảng cố định:
                + không thể thay đổi được kích thước của mảng sau khi đã khởi tạo với số lượng ô nhớ cố định
            - Kiểu dữ liệu trong mảng phải đồng nhất:
                + tất cả các phần tử trong mảng phải có cùng kiểu dữ liệu
            - Truy cập các phần tử trong mảng thông qua chỉ số index:
                + chỉ số index của mảng bắt đầu từ 0 đến n-1 với n là số lượng phần tử trong mảng
            - Các phần tử trong mảng được lưu trữ liên tiếp với nhau trong bộ nhớ lên việc truy cập mảng thông qua index rất nhanh chóng chỉ có độ phức tạp thời gian là O(1)

    3. Khai báo và khởi tạo mảng
        Cách 1: khai báo và khởi tạo mảng cùng lúc
            KiểuDữLiệu[] tênMảng = new KiểuDữLiệu[kíchThướcMảng];

        Cách 2: Khai báo mảng trưc và khởi tạo mảng sau
            cú pháp:
                KiểuDữLiệu[] tênMảng; // khai báo mảng
                tênMảng = new KiểuDữLiệu[kíchThướcMảng]; // khởi tạo mảng
        Cách 3: Khai báo, khởi tạo và gán giá trị cho mảng cùng lúc
            cú pháp:
                KiểuDữLiệu[] tênMảng = {giáTrị1, giáTrị2, giáTrị3, ...};


    4. Truy cập phần tử mảng
        - Sử dụng chỉ số index để truy cập phần tử của mảng
        Cú pháp:
            + truy cập phần tử mảng với index i: tênMảng[i];
            + gán giá trị cho phần tử màng với index i: tênMảng[i] = giáTrị;
        Ở đó:
            + tênMảng: là tên của mảng
            + i: là chỉ số index của phần tử trong mảng (bắt đầu từ
                0 đến kích thước mảng - 1)
            + giáTrị: là giá trị cần gán cho phần tử mảng tại vị trí i
        - Lưu ý:
            i nhỏ hơn kích thưởng mảng và i >= 0
    5. Duyệt mảng
        Để duyệt mảng sẽ sử dụng các vòng lặp trong Java đê duệt hết tất cả các phần tử trong mảng
        ví dụ 1: sử dụng vòng lặp for để duyệt mảng
            for (int i = 0; i < tênMảng.length; i++) {
                // thao tác với phần tử tênMảng[i]
            }

        ví dụ 2: sử dụng vòng lặp for-each để duyệt mảng
            for-each: cùng là dạng vòng lặp for được sử dụng để duyệt tất cả các phần tử tương tự vòng lặp for i thông thường
                    nhưng cú pháp ngắn gọn hơn và bỏ qua việc sử dụng chỉ số index i
            cú pháp:
                for (KiểuDữLiệu phầnTử : tênMảng) {
                    // thao tác với phần tử phầnTử
                }

 6. Mảng đa chiều


 */

public class Main4 {

    public static void main(String[] args) {
        System.out.println("Ví dụ 1: Khai báo và khởi tạo mảng cùng lúc");
        // Khai báo và khởi tạo mảng cùng lúc
        int[] mangSoNguyen = new int[5]; // mảng gồm 5 ô nhớ nằm liên tiếp nhau chứa kiểu dữ liệu int

        System.out.println("Ví dụ 2: Khai báo mảng trưc và khởi tạo mảng sau");
        // Khai báo mảng trưc và khởi tạo mảng sau
        double[] mangSoThuc; // khai báo mảng
        mangSoThuc = new double[3]; // khởi tạo mảng gồm 3 ô nhớ chứa kiểu dữ liệu double


        System.out.println("Ví dụ 3: Khai báo, khởi tạo và gán giá trị cho mảng cùng lúc");
        // Khai báo, khởi tạo và gán giá trị cho mảng cùng lúc
        String[] mangChuoi = {"Java", "Python", "C++", "JavaScript"}; // mảng gồm 4 ô nhớ chứa kiểu dữ liệu String\
                            //   0      1         2           3    => index của mảng

        System.out.println("Ví dụ 4: Truy cập phần tử mảng mangChuoi, lấy ra phần tử thứ 2");
        String phanTuThu2 = mangChuoi[1]; // truy cập phần tử mảng với index 1 (phần tử thứ 2)
        System.out.println("Phần tử thứ 2 trong mảng mangChuoi: " + phanTuThu2);

        System.out.println("Ví dụ 5: Gán giá trị cho phần tử mảng mangChuoi cho phần tử thứ 3 thay đổi từ C++ thành Ruby");
        System.out.println("Giá trị mảng trước khi thay đổi của phần tử thứ 3: " + mangChuoi[2]);
        mangChuoi[2] = "Ruby"; // gán giá trị cho phần tử mảng với index 2 (phần tử thứ 3)
        System.out.println("Giá trị mảng sau khi thay đổi của phần tử thứ 3: " + mangChuoi[2]);

        System.out.println("Ví dụ 6: Duyệt mảng chuỗi sử dụng vòng lặp for i, hiển thị toàn bộ các phần tử của mảng");
        for (int i = 0; i < mangChuoi.length; i++) { // mangChuoi.length: lấy ra kích thước của mảng, sử dụng kích thưởng của mảng để duyệt toàn bộ các phần tử trong mảng
            System.out.println("Phần tử thứ " + (i + 1) + " với index là : " + i + " có giá trị là: " + mangChuoi[i]);
        }

        System.out.println("Ví dụ 7: Duyệt mảng chuỗi sử dụng vòng lặp for-each, hiển thị toàn bộ các phần tử của mảng");
        for (String phanTu : mangChuoi) { // duyệt toàn bộ các
            System.out.println("Phần tử có giá trị là: " + phanTu);
        }

        System.out.println("Ví dụ 8: Khai báo mảng số nguyên và nhập các phần tử cho mảng với kích thưởng n nhập từ bàn phím");
        System.out.println("Nhập vào số lượng phần tử của mảng ");
        int n = new Scanner(System.in).nextInt();
        System.out.println("Số lượng phần tử của mảng là: " + n);
        int[] mangSoNguyenNhapTuBanPhim = new int[n]; // khai báo và khởi tạo mảng số nguyên với kích thưởng n nhập từ bàn phím, mảng các phần tử vừa khai báo xong sẽ mới chỉ được cấp phát ô nhớ, và trong các ô nhớ đó vẫn chưa có dữ liệu, cần phẩn nhập và gán giá trị cho các ô nhớ, thì mảng mới có dữ liệu
        System.out.println("Bắt đầu nhập các phần tử cho mảng: ");
        for (int i=0; i < mangSoNguyenNhapTuBanPhim.length;i++){
            System.out.println("Nhập vào phần tử thứ " + (i+1) + " có index là : " + i);
            int giaTri = new Scanner(System.in).nextInt();
            mangSoNguyenNhapTuBanPhim[i] = giaTri; // gán giá trị nhập từ bàn phím cho phần tử mảng tại vị trí i
        }
        System.out.println("Các phần tử trong mảng sau khi nhập từ bàn phím là: ");
        for (int i = 0; i < mangSoNguyenNhapTuBanPhim.length; i++) {
            System.out.println("Phần tử thứ " + (i + 1) + " với index là : " + i + " có giá trị là: " + mangSoNguyenNhapTuBanPhim[i]);
        }

    }

}
