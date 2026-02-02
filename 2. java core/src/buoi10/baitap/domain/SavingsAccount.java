package buoi10.baitap.domain;

import buoi10.baitap.exception.InsufficientFundsException;

public class SavingsAccount extends Account {


    @Override
    public void deposit(double amount) {
        super.setBalance(super.getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > super.getBalance()) {
            double shortage = amount - super.getBalance();
            throw new InsufficientFundsException(
                "Giao dịch thất bại: Tài khoản Tiết kiệm không được phép rút quá số dư (Thiếu " + shortage + ")"
            );
        }
        super.setBalance(super.getBalance() - amount);
    }

    @Override
    public void transfer() {

    }
}
