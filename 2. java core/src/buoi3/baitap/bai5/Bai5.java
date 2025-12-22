package buoi3.baitap.bai5;

import java.util.Scanner;

/**
 Bài 5 (Nâng cao): Hệ thống Giao dịch Ngân hàng Tích hợp
 Mục tiêu: Quản lý tài khoản,
 thực hiện nạp tiền nhiều lần bằng vòng lặp và tính toán lãi suất kép bằng đệ quy.
 1. Thiết kế lớp Account
 •	Thành phần Static & Instance:
     o	static String bankName = "T3H Bank"; (Thông tin dùng chung).
     o	String customerName;, double balance; (Thông tin riêng biệt).
 •	Phương thức showInfo(): In thông tin tài khoản.
 •	Phương thức deposit(double amount):
     o	Sử dụng if-else để kiểm tra: Nếu amount <= 0 thì báo lỗi, ngược lại mới thực hiện nạp.
     o	Sử dụng biến local newBalance để tính toán.
 •	Vòng lặp (Menu nạp tiền nhiều lần):
     o	Trong hàm main, sử dụng vòng lặp while hoặc do-while để cho phép người dùng nạp tiền liên tục cho đến khi nhập số 0 thì dừng.
     o	Mỗi lần nạp thành công, sử dụng lệnh showInfo() để cập nhật tình trạng tài khoản.
 •	Đệ quy (Tính lãi suất tích lũy):
     o	Viết hàm đệ quy double calculateCompoundInterest(double currentBalance, int months).
     o	Logic: Mỗi tháng lãi suất là 1%.
     o	Điểm dừng: Nếu months == 0, trả về currentBalance.
     o	Công thức đệ quy: return calculateCompoundInterest(currentBalance * 1.01, months - 1);

 */
public class Bai5 {

    public static void main(String[] args) {
        int exits = 1;
        Account account = new Account("Nguyen Van A", 1000);
        do {
            System.out.println("Menu:");
            System.out.println("1. Xem thông tin tài khoản");
            System.out.println("2. Nạp tiền");
            System.out.println("3. Tính lãi suất kép");
            System.out.println("4. Thoát");
            System.out.print("Chọn chức năng (1-4): ");
            int choice = new Scanner(System.in).nextInt();
            switch (choice) {
                case 1:
                    account.showInfo();
                    break;
                case 2:
                    System.out.println("Nhập số tiền nạp (0 để dừng): ");
                    double amount = new Scanner(System.in).nextDouble();
                    account.deposit(amount);
                    break;
                case 3:
                    System.out.println("Nhập vào số tháng để tính lãi suất kép: ");
                    int months = new Scanner(System.in).nextInt();
                    double newBalance = calculateCompoundInterest(account.getBalance(), months);
                    System.out.printf("Số dư sau %d tháng với lãi suất kép là: %.2f%n", months, newBalance);
                    break;
                case 4:
                    exits = 0;
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }

        }while (exits != 0);
    }

    public static double calculateCompoundInterest(double currentBalance, int months) {
        if (months <= 0) {
            return currentBalance;
        }
        return calculateCompoundInterest(currentBalance * 1.01, months - 1);
    }
}
