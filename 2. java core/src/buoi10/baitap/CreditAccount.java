package buoi10.baitap;

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
        if (super.getBalance() < 0 && Math.abs(super.getBalance() - amount) > limitCredit) {
            System.out.println("Vượt quá hạn mức tín dụng!");
            return;
        }
        super.setBalance(super.getBalance() - amount);
    }



    @Override
    public void transfer() {

    }
}
