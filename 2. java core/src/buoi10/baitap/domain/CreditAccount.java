package buoi10.baitap.domain;

import buoi10.baitap.exception.InsufficientFundsException;

public class CreditAccount extends Account {

    public int limitCredit;

    @Override
    public void input() {
        super.input();
        System.out.println("Nhập hạn mức tín dụng: ");
        this.limitCredit = new java.util.Scanner(System.in).nextInt();
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Hạn mức tín dụng: " + this.limitCredit);
    }

    public int getLimitCredit() {
        return limitCredit;
    }

    public void setLimitCredit(int limitCredit) {
        this.limitCredit = limitCredit;
    }

    @Override
    public void deposit(double amount) {
        super.setBalance(super.getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) {
        double newBalance = super.getBalance() - amount;
        if (newBalance < 0 && Math.abs(newBalance) > limitCredit) {
            double over = Math.abs(newBalance) - limitCredit;
            throw new InsufficientFundsException(
                "Giao dịch thất bại: Vượt quá hạn mức tín dụng " + limitCredit + " (Vượt " + over + ")"
            );
        }
        super.setBalance(newBalance);
    }



    @Override
    public void transfer() {

    }
}
