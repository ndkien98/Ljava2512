package buoi8.baitap;

@FunctionalInterface
public interface TransactionComparator {
    int compare(Transaction t1, Transaction t2);
}