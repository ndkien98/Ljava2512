package buoi10.baitap;

import buoi10.baitap.service.AccountManager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("========== CHÀO MỪNG ĐẾN VỚI SMARTBANK PRO ==========\n");
        AccountManager accountManager = new AccountManager();

        // Khởi tạo dữ liệu mẫu
        accountManager.init();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu();
            System.out.print("Lựa chọn của bạn: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    accountManager.registerAccount();
                    break;
                case 2:
                    accountManager.depositOrWithdraw();
                    break;
                case 3:
                    accountManager.transferMoney();
                    break;
                case 4:
                    accountManager.showVIPCustomers();
                    break;
                case 5:
                    accountManager.endOfMonthProcessing();
                    break;
                case 6:
                    accountManager.showAllAccounts();
                    break;
                case 0:
                    System.out.println("Cảm ơn bạn đã sử dụng SmartBank Pro. Tạm biệt!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }

            System.out.println("\nNhấn Enter để tiếp tục...");
            scanner.nextLine();
        }
    }

    public static void showMenu(){
        System.out.println("\n========== SMARTBANK PRO MENU ==========");
        System.out.println("1. Mở tài khoản mới (Check trùng ID)");
        System.out.println("2. Nạp/Rút tiền (Xử lý Đa hình & Exception)");
        System.out.println("3. Chuyển khoản (Xử lý Map & Exception)");
        System.out.println("4. Danh sách khách hàng VIP (Stream & Lambda)");
        System.out.println("5. Chốt số dư cuối tháng (Functional Interface)");
        System.out.println("6. Xem tất cả tài khoản");
        System.out.println("0. Thoát hệ thống");
        System.out.println("========================================");
    }
}
