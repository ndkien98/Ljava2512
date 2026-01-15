package buoi8.demo2;

import buoi8.demo2.c1.MathUtils;

/**

 Lamda Expression :
    - là một cách viết ngắn gọn để triển khai một method của interface một cách ngắn gọn trực tiếp hơn so với cách viết thông thường
    - và tối ưu hơn so với cach viết Anonymous Class (lớp ẩn danh)
    - Chỉ áp dựng được với các funtional interface( interface chi có 1 method trừu tượng duy nhất)
    cu phap:
        (danh sách tham số) -> {
            // thân hàm
            // return nếu có giá trị trả về
        }
 */

public class Main {

    public static void main(String[] args) {
        // c1
        IMathUtils c1 = new MathUtils();
        int tong = c1.add(5, 10);
        System.out.println("Tổng c1: " + tong);

        //c2 sử dụng anonymous class
        IMathUtils c2 = new IMathUtils() {
            @Override
            public int add(int a, int b) {
                return a + b;
            }
        };
        int tong2 = c2.add(5, 10);
        System.out.println("Tổng c2: " + tong2);

        // c3 sử dụng lamda expression
//        IMathUtils c3 = (a, b) -> {
//            System.out.println("Đang thực hiện phép cộng trong lamda expression");
//            return a + b;
//        };
        IMathUtils c3 = (a, b) -> a + b;
        int tong3 = c3.add(5, 10);
        System.out.println("Tổng c3: " + tong3);
    }

}
