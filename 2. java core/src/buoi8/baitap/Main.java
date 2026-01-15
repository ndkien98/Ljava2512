package buoi8.baitap;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TransactionManager manager = new TransactionManager();

        manager.addTransaction(new DepositTransaction("D1", 2500));
        manager.addTransaction(new WithdrawTransaction("W1", 2000));
        manager.addTransaction(new DepositTransaction("D2", 1500));
        manager.addTransaction(new WithdrawTransaction("W2", 1000));
        manager.addTransaction(new WithdrawTransaction("W3", 700));
        manager.addTransaction(new DepositTransaction("D3", 500));

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. Hiển thị tất cả giao dịch");
            System.out.println("2. Nhập vào số tiền và hiển thị các giao dịch có số tiền lớn hơn số tiền đó");
            System.out.println("3. Tính thuế cho các giao dịch rút tiền");
            System.out.println("4. Sắp xếp các giao dịch theo số tiền tăng dần");
            System.out.println("0. Thoát");
            System.out.print("Chọn 1 số: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manager.hienThiGiaoDich();
                    break;
                case 2:
                    manager.filterByAmount();
                    break;
                case 3:
                    manager.calculateTax();
                    break;
                case 4:
                    manager.sort();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Số không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 0);

        scanner.close();
    }
}