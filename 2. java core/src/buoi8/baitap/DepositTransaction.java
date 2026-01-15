package buoi8.baitap;

public class DepositTransaction extends Transaction {

    public DepositTransaction(String id, double amount) {
        super(id, amount);
    }

    @Override
    public void process() {
        System.out.println("Processing deposit transaction...");
    }
}
