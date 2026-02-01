package buoi10.baitap.exception;

public class DuplicateCustomerException extends RuntimeException {

    public DuplicateCustomerException(String message) {
        super(message);
    }

    public DuplicateCustomerException(){
        super("Customer already exists!");
    }
}
