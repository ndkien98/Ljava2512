package buoi3.baitap.bai5;

public class Account {

    public static String bankName = "T3h Bank";
    private String customerName;
    private double balance;

    public Account(String customerName, double initialBalance) {
        this.customerName = customerName;
        this.balance = initialBalance;
    }

    public void showInfo() {
        System.out.println("Bank: " + bankName);
        System.out.println("Customer: " + customerName);
        System.out.println("Balance: " + balance);
    }

    public void deposit(double amount) {
        if (amount < 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        double newBalance = balance + amount;
        this.balance = newBalance;
        System.out.println("Deposited: " + amount + ", New Balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }
}
