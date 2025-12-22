package buoi3;

/**

 Hàm đệ quy:
    Hàm đệ quy là hàm gọi chính nó để giải quyết một bài toán bằng cách chia nhỏ bài toán đó thành các bài toán con giống hệt nhau.
    Mỗi lần gọi hàm sẽ tiến gần hơn đến điểm dừng (điều kiện kết thúc) để tránh việc gọi hàm vô hạn.
 Ví dụ tính giai thừa sử dụng đệ quy:
    Giai thừa của 0 và 1 là 1
    Giai thừa của n (n > 1) là n * giai thừa của (n - 1)
    Điểm dừng: n == 0 hoặc n == 1, trả về 1\

 Bài tập vẽ tree:

 */
public class Main2 {

    public static void main(String[] args) {

        int n = 5;
        long result = tinhGiaiThua(n);
        System.out.println(n + "! = " + result);
    }

    public static long tinhGiaiThua(int n) {
        // Điểm dừng
        if (n == 0 || n == 1) {
            return 1;
        }
        // Phần đệ quy
        return n * tinhGiaiThua(n - 1);
    }

    /**
     Nếu không có điều kiện dừng, hàm sẽ gọi lại chính nó vô hạn, dẫn đến lỗi tràn ngăn xếp (Stack Overflow Error)
     */
}
