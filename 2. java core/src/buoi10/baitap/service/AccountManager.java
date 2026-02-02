package buoi10.baitap.service;

import buoi10.baitap.domain.Account;
import buoi10.baitap.domain.CreditAccount;
import buoi10.baitap.domain.SavingsAccount;
import buoi10.baitap.exception.AccountNotFoundException;
import buoi10.baitap.exception.DuplicateCustomerException;
import buoi10.baitap.exception.InsufficientFundsException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AccountManager {

    private Map<String, Account> accounts = new HashMap<>();
    private Set<String> citizenIds = new HashSet<>();
    private int accountCounter = 1;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Khởi tạo sẵn 5 tài khoản Savings và 5 tài khoản Credit để demo
     */
    public void init() {
        System.out.println("Đang khởi tạo dữ liệu mẫu...");
        
        // Tạo 5 tài khoản Savings
        String[] savingsNames = {"Nguyen Van A", "Tran Thi B", "Le Van C", "Pham Thi D", "Hoang Van E"};
        double[] savingsBalances = {5000000, 12000000, 3000000, 20000000, 8000000};
        
        for (int i = 0; i < 5; i++) {
            SavingsAccount account = new SavingsAccount();
            String accountNumber = "SAV-" + String.format("%03d", accountCounter++);
            String citizenId = "SAV" + String.format("%03d", i + 1);
            
            account.setNumberAccount(accountNumber);
            account.setOwner(savingsNames[i]);
            account.setBalance(savingsBalances[i]);
            account.setCitizenId(citizenId);
            
            accounts.put(accountNumber, account);
            citizenIds.add(citizenId);
        }
        
        // Tạo 5 tài khoản Credit
        String[] creditNames = {"Do Thi F", "Vu Van G", "Bui Thi H", "Dang Van I", "Ngo Thi K"};
        double[] creditBalances = {-500000, 3000000, -1000000, 10000000, 6000000};
        int[] creditLimits = {5000000, 10000000, 3000000, 15000000, 8000000};
        
        for (int i = 0; i < 5; i++) {
            CreditAccount account = new CreditAccount();
            String accountNumber = "CRE-" + String.format("%03d", accountCounter++);
            String citizenId = "CRE" + String.format("%03d", i + 1);
            
            account.setNumberAccount(accountNumber);
            account.setOwner(creditNames[i]);
            account.setBalance(creditBalances[i]);
            account.setCitizenId(citizenId);
            account.setLimitCredit(creditLimits[i]);
            
            accounts.put(accountNumber, account);
            citizenIds.add(citizenId);
        }
        
        System.out.println("Đã khởi tạo thành công 10 tài khoản mẫu (5 Savings + 5 Credit)");
    }

    public void registerAccount() {
        try {
            System.out.println("\n========== MỞ TÀI KHOẢN MỚI ==========");
            System.out.print("Nhập tên khách hàng: ");
            String name = scanner.nextLine();

            System.out.print("Nhập mã định danh (Citizen ID): ");
            String citizenId = scanner.nextLine();
            
            if (citizenIds.contains(citizenId)) {
                throw new DuplicateCustomerException(
                    "Lỗi: Khách hàng có ID " + citizenId + " đã tồn tại trong hệ thống. Không thể mở thêm!"
                );
            }
            
            System.out.println("Chọn loại tài khoản:");
            System.out.println("1. Tài khoản tiết kiệm (Savings)");
            System.out.println("2. Tài khoản tín dụng (Credit)");
            System.out.print("Lựa chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Nhập số tiền ban đầu: ");
            double initialBalance = scanner.nextDouble();
            scanner.nextLine();
            
            Account account;
            String accountNumber;
            
            if (choice == 1) {
                account = new SavingsAccount();
                accountNumber = "SAV-" + String.format("%03d", accountCounter++);
            } else {
                account = new CreditAccount();
                accountNumber = "CRE-" + String.format("%03d", accountCounter++);
                System.out.print("Nhập hạn mức tín dụng: ");
                int creditLimit = scanner.nextInt();
                scanner.nextLine();
                ((CreditAccount) account).setLimitCredit(creditLimit);
            }
            
            account.setNumberAccount(accountNumber);
            account.setOwner(name);
            account.setBalance(initialBalance);
            account.setCitizenId(citizenId);
            
            accounts.put(accountNumber, account);
            citizenIds.add(citizenId);
            System.out.println("Mở tài khoản thành công cho khách hàng " + name + ". Số TK: " + accountNumber);
        } catch (DuplicateCustomerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void depositOrWithdraw() {
        try {
            System.out.println("\n========== NẠP/RÚT TIỀN ==========");
            System.out.println("1. Nạp tiền");
            System.out.println("2. Rút tiền");
            System.out.print("Lựa chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Nhập số tài khoản: ");
            String accountNumber = scanner.nextLine();
            
            Account account = accounts.get(accountNumber);
            if (account == null) {
                throw new AccountNotFoundException("Lỗi: Không tìm thấy tài khoản " + accountNumber);
            }
            
            System.out.print("Nhập số tiền: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            
            if (choice == 1) {
                account.deposit(amount);
                System.out.println("Nạp tiền thành công. Số dư mới: " + account.getBalance());
            } else {
                account.withdraw(amount);
                System.out.println("Rút tiền thành công. Số dư mới: " + account.getBalance());
            }
            
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void transferMoney() {
        try {
            System.out.println("\n========== CHUYỂN KHOẢN ==========");
            System.out.print("Nhập số tài khoản nguồn: ");
            String fromAccount = scanner.nextLine();
            
            Account source = accounts.get(fromAccount);
            if (source == null) {
                throw new AccountNotFoundException("Lỗi: Không tìm thấy tài khoản nguồn " + fromAccount);
            }
            
            System.out.print("Nhập số tài khoản đích: ");
            String toAccount = scanner.nextLine();
            
            Account destination = accounts.get(toAccount);
            if (destination == null) {
                throw new AccountNotFoundException("Lỗi: Không tìm thấy tài khoản đích có mã " + toAccount);
            }
            
            System.out.print("Nhập số tiền chuyển: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            
            source.withdraw(amount);
            destination.deposit(amount);
            
            System.out.println("Chuyển khoản thành công!");
            System.out.println("Số dư TK nguồn: " + source.getBalance());
            System.out.println("Số dư TK đích: " + destination.getBalance());
            
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showVIPCustomers() {
        System.out.println("\n--- DANH SÁCH TÀI KHOẢN VIP ---");
        
        var vipAccounts = accounts.values().stream()
            .filter(account -> account.getBalance() > 5000)
            .collect(Collectors.toList());
        
        if (vipAccounts.isEmpty()) {
            System.out.println("Không có tài khoản VIP nào.");
            return;
        }
        
        int index = 1;
        for (Account account : vipAccounts) {
            System.out.println(index++ + ". " + account.getOwner().toUpperCase() + 
                             " - Số dư: " + (int)account.getBalance());
        }
        
        double totalBalance = accounts.values().stream()
            .mapToDouble(Account::getBalance)
            .sum();
        
        System.out.println("-------------------------------");
        System.out.println("Tổng số vốn ngân hàng đang quản lý: " + (int)totalBalance);
    }

    public void endOfMonthProcessing() {
        System.out.println("\n========== CHỐT SỐ DƯ CUỐI THÁNG ==========");
        System.out.println("Đang quét danh sách tài khoản...");
        
        accounts.values().forEach(account -> {
            double oldBalance = account.getBalance();
            
            if (account instanceof SavingsAccount) {
                InterestRate savingsRate = (balance) -> balance * 0.05;
                double interest = savingsRate.calculateInterest(oldBalance);
                account.deposit(interest);
                System.out.printf("- Tài khoản %s: +%.0f (Lãi tiết kiệm 5%%) -> Số dư mới: %.0f%n",
                    account.getNumberAccount(), interest, account.getBalance());
                    
            } else if (account instanceof CreditAccount) {
                InterestRate feeRate = (balance) -> Math.abs(balance) * 0.01;
                double fee = feeRate.calculateInterest(oldBalance);
                account.withdraw(fee);
                System.out.printf("- Tài khoản %s: -%.0f (Phí quản lý 1%%) -> Số dư mới: %.0f%n",
                    account.getNumberAccount(), fee, account.getBalance());
            }
        });
        
        System.out.println("Hoàn tất quyết toán cuối tháng!");
    }

    public void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("\n⚠ Chưa có tài khoản nào trong hệ thống.");
            System.out.println("Gợi ý: Chọn menu 1 để mở tài khoản mới hoặc khởi động lại chương trình để load dữ liệu mẫu.");
            return;
        }
        
        System.out.println("\n========== DANH SÁCH TẤT CẢ TÀI KHOẢN ==========");
        System.out.println(String.format("%-15s %-25s %-15s %-20s %-15s", 
            "Số TK", "Chủ TK", "Citizen ID", "Số dư", "Loại TK"));
        System.out.println("=".repeat(95));
        
        // Phân loại và hiển thị
        long savingsCount = accounts.values().stream()
            .filter(acc -> acc instanceof SavingsAccount)
            .count();
        long creditCount = accounts.values().stream()
            .filter(acc -> acc instanceof CreditAccount)
            .count();
        
        // Hiển thị tài khoản Savings
        System.out.println("\n--- TÀI KHOẢN TIẾT KIỆM (SAVINGS) ---");
        accounts.values().stream()
            .filter(acc -> acc instanceof SavingsAccount)
            .forEach(acc -> {
                System.out.println(String.format("%-15s %-25s %-15s %,15.0f VNĐ   %-15s", 
                    acc.getNumberAccount(), 
                    acc.getOwner(), 
                    acc.getCitizenId(),
                    acc.getBalance(),
                    "Tiết kiệm"));
            });
        
        // Hiển thị tài khoản Credit
        System.out.println("\n--- TÀI KHOẢN TÍN DỤNG (CREDIT) ---");
        accounts.values().stream()
            .filter(acc -> acc instanceof CreditAccount)
            .forEach(acc -> {
                CreditAccount creditAcc = (CreditAccount) acc;
                String balanceDisplay = String.format("%,15.0f VNĐ", acc.getBalance());
                String accountType = "Tín dụng (Hạn mức: " + String.format("%,d", creditAcc.getLimitCredit()) + ")";
                System.out.println(String.format("%-15s %-25s %-15s %s   %-15s", 
                    acc.getNumberAccount(), 
                    acc.getOwner(), 
                    acc.getCitizenId(),
                    balanceDisplay,
                    accountType));
            });
        
        // Thống kê tổng hợp
        double totalBalance = accounts.values().stream()
            .mapToDouble(Account::getBalance)
            .sum();
        
        System.out.println("\n" + "=".repeat(95));
        System.out.println(String.format("====Tổng số tài khoản: %d (Savings: %d | Credit: %d)====",
            accounts.size(), savingsCount, creditCount));
        System.out.println(String.format("====Tổng số dư toàn hệ thống: %,15.0f VNĐ====", totalBalance));
        System.out.println("=".repeat(95));
    }
}
