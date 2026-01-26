package buoi10.baitap;

/**

Account
 Chứa thông tin số tài khoản, chủ sở hữu, số dư
 */
public abstract class Account implements IBankingAction{

    private String numberAccount;
    private String owner;
    private double balance;


    public void input(){
        System.out.println("Nhập số tài khoản: ");
        this.numberAccount = new java.util.Scanner(System.in).nextLine();
        System.out.println("Nhập chủ sở hữu: ");
        this.owner = new java.util.Scanner(System.in).nextLine();
        System.out.println("Nhập số dư: ");
        this.balance = new java.util.Scanner(System.in).nextDouble();
    }

    public void display(){
        System.out.println("Số tài khoản: " + this.numberAccount);
        System.out.println("Chủ sở hữu: " + this.owner);
        System.out.println("Số dư: " + this.balance);
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
