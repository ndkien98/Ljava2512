package buoi3.baitap;

import java.util.Scanner;

/**
 Bài 3: Vòng lặp do-while & Ứng dụng Hàm (Menu điều khiển)
 Yêu cầu:
 •	Tạo một chương trình có Menu điều khiển bằng switch-case đặt trong vòng lặp do-while
 •	Menu gồm các lựa chọn:
         1.	Tính tổng các số từ 1 đến 100 (Sử dụng hàm tự viết).
         2.	In bảng chữ cái từ A đến Z.
         3.	Thoát chương trình.
 •	Vòng lặp do-while phải đảm bảo chương trình chạy ít nhất một lần và chỉ kết thúc khi người dùng chọn tùy chọn "Thoát"9.


 */
public class Bai3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Tính tổng các số từ 1 đến 100");
            System.out.println("2. In bảng chữ cái từ A đến Z");
            System.out.println("3. Thoát chương trình");
            System.out.print("Chọn một tùy chọn (1-3): ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    sum();
                    break;
                case 2:
                    displayAlphabet();
                    break;
                case 3:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }while (choice != 3);
    }
    public static void displayAlphabet(){
        for (char i = 'A'; i <= 'Z'; i++) {
            System.out.print(i + " ");
        }
    }

    public static void sum(){
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println("Tổng các số từ 1 đến 100 là: " + sum);
    }

}
