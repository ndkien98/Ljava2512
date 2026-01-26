package buoi10.baitap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 •	Sử dụng HashMap<String, Account> để quản lý danh sách tài khoản (Key là số tài khoản).
 •	Sử dụng HashSet<String> để lưu trữ danh sách mã định danh khách hàng (Citizen ID) nhằm đảm bảo không trùng lặp khi mở tài khoản mới.

 */
public class AccountManager {

    private Map<String, Account> accounts = new HashMap<>();
    private Set<String> citizenIds = new HashSet<>();


    /**
     Chức năng 1: Mở tài khoản (Validation & Exception)
     •	Nghiệp vụ: Nhập thông tin khách hàng. Nếu Citizen ID đã tồn tại trong HashSet, ném ra một Custom Exception: DuplicateCustomerException.
     •	Input: Nhập khách hàng A, ID: 123. Sau đó nhập tiếp khách hàng B, ID: 123.
     •	Output: Hệ thống báo lỗi trùng lặp và yêu cầu nhập lại thay vì dừng chương trình.

     */

    public void registerAccount() {
        System.out.println("Mở tài khoản mới:");
        String citizenId = getCitizenId();
        citizenIds.add(citizenId);
        Account newAccount = getAccount();
        newAccount.input();
        accounts.put(newAccount.getNumberAccount(), newAccount);
        System.out.println("Tài khoản đã được mở thành công.");
    }

    private String getCitizenId() {
        System.out.println("Nhập mã định danh khách hàng (Citizen ID): ");
        String citizenId = null;
        do {
            try {
                citizenId = enterCitizenId();
            } catch (DuplicateCustomerException e) {
                System.out.println(e.getMessage());
                System.out.println("Vui lòng nhập lại mã định danh khách hàng (Citizen ID): ");
            }
        }while (citizenId == null);
        return citizenId;
    }

    private static Account getAccount() {
        Account newAccount = null;
        System.out.println("Chọn loại tài khoản để mở:");
        System.out.println("1. Tài khoản tiết kiệm");
        System.out.println("2. Tài khoản tin dụng");
        System.out.print("Lựa chọn của bạn (1 hoặc 2): ");
        int choice = new java.util.Scanner(System.in).nextInt();
        if (choice == 1) {
            newAccount = new SavingsAccount();
        } else if (choice == 2) {
            newAccount = new CreditAccount();
        }
        return newAccount;
    }

    private String enterCitizenId() {
        String citizenId = new java.util.Scanner(System.in).nextLine();
        if (citizenIds.contains(citizenId)) {
            throw new DuplicateCustomerException("Lỗi: Mã định danh khách hàng đã tồn tại. Vui lòng nhập mã khác.");
        }
        return citizenId;
    }

    /**
     Chức năng 2: Giao dịch tài chính (Polymorphism & Interface)
     •	Nghiệp vụ: Thực hiện nạp/rút tiền. Hệ thống phải tự nhận diện loại tài khoản để áp dụng quy tắc rút tiền:
     o	SavingsAccount: Không được rút quá số dư.
     o	CreditAccount: Cho phép rút quá số dư nhưng không quá hạn mức (Credit Limit).
     •	Input: Tài khoản Credit có dư 0, hạn mức 5000. Thực hiện rút 3000.
     •	Output: Giao dịch thành công, số dư mới là -3000.
     */

    public void drawMoney() {
        Account account = this.findAccount();
        if (account == null) {
            System.out.println("Tài khoản không tồn tại.");
            return;
        }
        int amountDraw = 0;
        System.out.print("Nhập số tiền muốn rút: ");
        amountDraw = new java.util.Scanner(System.in).nextInt();
        account.withdraw(amountDraw);
        System.out.println("Giao dịch rút tiền thành công. số dư mới là: " + account.getBalance());
    }

    private boolean validateAccountDraw(Account account, int amountDraw) {
        if (account instanceof SavingsAccount && amountDraw > account.getBalance()){
            System.out.println("Số dư không đủ để rút tiền.");
            return true;
        }else if (account instanceof CreditAccount && amountDraw > ((CreditAccount) account).limitCredit){
            System.out.println("Vượt quá hạn mức tín dụng, không thể rút tiền.");
            return true;
        }
        return false;
    }

    private Account findAccount() {
        System.out.println("Rút tiền từ tài khoản:");
        System.out.print("Nhập số tài khoản: ");
        String accountNumber = new java.util.Scanner(System.in).nextLine();
        return accounts.get(accountNumber);
    }


}
