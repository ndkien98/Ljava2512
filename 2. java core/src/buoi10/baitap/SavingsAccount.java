package buoi10.baitap;

public class SavingsAccount extends Account{


    @Override
    public void deposit(double amount) {
        super.setBalance(super.getBalance() + amount);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > super.getBalance()) {
            System.out.println("Số dư không đủ để rút!");
            return;
        }
        super.setBalance(super.getBalance() - amount);
    }

    @Override
    public void transfer() {

    }
}
