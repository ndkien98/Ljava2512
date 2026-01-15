package buoi8.baitap;

import buoi7.baitap.Utils;

import java.util.function.Predicate;

public class TransactionManager {

    private Transaction[] transactions;
    private int count;
    private static final int MAX_TRANSACTIONS = 100;

    public TransactionManager() {
        this.transactions = new Transaction[MAX_TRANSACTIONS];
        this.count = 0;
    }
    public void addTransaction(Transaction transaction) {
        if (transactions == null) {
            transactions = new Transaction[MAX_TRANSACTIONS];
            count = 0;
        }
        if (count < MAX_TRANSACTIONS) {
            transactions[count++] = transaction;
        } else {
            System.out.println("Không thể thêm giao dịch mới. Đã đạt đến giới hạn tối đa.");
        }
    }
    public void filterByAmount(){
        System.out.println("Nhập vào số tiền để lọc giao dịch:");
        int amount = Utils.scanNumber();
        Transaction[] filteredTransactions = filter(t -> t.getAmount() > amount);
        System.out.println("Các giao dịch có số tiền lớn hơn " + amount + ":");
        for (Transaction t : filteredTransactions) {
            System.out.println(t);
        }
    }

    public Transaction[] filter(Predicate<Transaction> condition){
        Transaction[] result = new Transaction[count];
        int index = 0;
        for (int i = 0; i < count; i++) {
            if (condition.test(transactions[i])) {
                result[index++] = transactions[i];
            }
        }
        Transaction[] filteredTransactions = new Transaction[index];
        for (int i = 0; i < index; i++) {
            filteredTransactions[i] = result[i];
        }
        return filteredTransactions;
    }

    public void calculateTax(){
        Taxable taxable = (amount) -> amount * 0.01;
        double[] taxes = calculateWithdrawTaxes(taxable);
        System.out.println("Thuế cho các giao dịch rút tiền:");
        for (double tax : taxes) {
            System.out.println(tax);
        }
    }

    public double[] calculateWithdrawTaxes(Taxable taxable) {
        int withdrawCount = 0;
        for (int i = 0; i < count; i++) {
            if (transactions[i] instanceof WithdrawTransaction) {
                withdrawCount++;
            }
        }
        double[] taxes = new double[withdrawCount];
        int idx = 0;
        for (int i = 0; i < count; i++) {
            if (transactions[i] instanceof WithdrawTransaction) {
                taxes[idx++] = taxable.calculateTax(transactions[i].getAmount());
            }
        }
        return taxes;
    }


    public void sort() {
        TransactionComparator comparator = (t1, t2) -> Double.compare(t1.getAmount(), t2.getAmount());
        sortImpl(comparator);
        hienThiGiaoDich();
    }

    public void sortImpl(TransactionComparator comparator) {
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (comparator.compare(transactions[i], transactions[j]) > 0) {
                    Transaction temp = transactions[i];
                    transactions[i] = transactions[j];
                    transactions[j] = temp;
                }
            }
        }
    }

    public void hienThiGiaoDich() {
        System.out.println("Danh sách giao dịch:");
        for (int i = 0; i < count; i++) {
            System.out.println(transactions[i]);
        }
    }


}
