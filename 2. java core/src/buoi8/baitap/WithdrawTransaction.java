package buoi8.baitap;

public class WithdrawTransaction extends Transaction {

    public WithdrawTransaction(String id, double amount) {
        super(id, amount);
    }

    @Override
    public void process() {
        System.out.println("Processing Withdraw Transaction...");
    }
}
