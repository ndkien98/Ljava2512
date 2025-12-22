package buoi3.baitap;

import java.util.Scanner;

/**
 Bài 2: Vòng lặp & Điều khiển luồng (Số nguyên tố)
 Yêu cầu:
 •	Viết chương trình cho phép người dùng nhập vào một số nguyên dương n.
 •	Sử dụng vòng lặp for để kiểm tra và in ra tất cả các số nguyên tố từ 2 đến n
 •	Yêu cầu học sinh sử dụng lệnh break để tối ưu hóa việc kiểm tra một số có phải số nguyên tố hay không (dừng vòng lặp ngay khi tìm thấy một ước số).

 */
public class Bai2 {


    public static void main(String[] args) {
        System.out.println("Nhập vào số nguyên dương n: ");
        int n = new Scanner(System.in).nextInt();
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)){
                System.out.print(i + " ");
            }
        }

    }


    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
