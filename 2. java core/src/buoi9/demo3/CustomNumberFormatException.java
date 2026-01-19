package buoi9.demo3;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
Cú pháp khai báo một ngoại lệ tùy chỉnh trong Java bao gồm việc tạo một lớp mới kế thừa từ lớp Exception hoặc RuntimeException.
Dưới đây là cú pháp cơ bản để khai báo một ngoại lệ tùy chỉnh:
=> có thể sử dụng CustomNumberFormatException để biểu diễn lỗi định dạng số tùy chỉnh trong ứng dụng của bạn.
 */
public class CustomNumberFormatException extends Exception{

    public CustomNumberFormatException(String message){
        super(message);
    }

    public CustomNumberFormatException(){
        super("Lỗi định dạng số tùy chỉnh");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }


    public static void main(String[] args) {

        System.out.println("Nhập vào một số nguyên: ");
        int n = 0;
        try {
            n = nhapSoNguyen();
            System.out.println("Số nguyên vừa nhập là: " + n);
        } catch (CustomNumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập đúng định dạng số nguyên!: " + e.getMessage());
            n = -1;
        }
        while (n < 0){
            System.out.println("Số nguyên vừa nhập là số âm, vui lòng nhập lại số nguyên dương: ");
            try {
                n = nhapSoNguyen();
                System.out.println("Số nguyên vừa nhập là: " + n);
            } catch (CustomNumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập đúng định dạng số nguyên!: " + e.getMessage());
                n = -1;
            }
        }
    }


    public static int nhapSoNguyen() throws CustomNumberFormatException {
        int n;
        try {
            n = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            throw new CustomNumberFormatException("Định dạng số không hợp lệ");
        }
        return n;
    }
}
