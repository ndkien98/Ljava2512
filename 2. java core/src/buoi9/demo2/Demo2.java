package buoi9.demo2;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Demo2 {


    public static void main(String[] args) {

        System.out.println("Nhập vào một số nguyên: ");
        int n = 0;
        try {
            n = nhapSoNguyen();
            System.out.println("Số nguyên vừa nhập là: " + n);
        } catch (InputMismatchException e) {
            System.out.println("Lỗi: Vui lòng nhập đúng định dạng số nguyên!");
            n = -1;
        }
        while (n < 0){
            System.out.println("Số nguyên vừa nhập là số âm, vui lòng nhập lại số nguyên dương: ");
            try {
                n = nhapSoNguyen();
                System.out.println("Số nguyên vừa nhập là: " + n);
            } catch (InputMismatchException e) {
                System.out.println("Lỗi: Vui lòng nhập đúng định dạng số nguyên!");
                n = -1;
            }
        }

    }
    /**
     throws InputMismatchException
     báo hiệu phường thức này có thể xảy ra ngoại lệ InputMismatchException
     tất cả các nơi sử dụng phương thức này phải xử lý ngoại lệ này
     đây như 1 cách thông báo cho người sử dụng phương thức biết phương thức có thể sảy ra ngoại lệ này
     */
    public static int nhapSoNguyen() throws InputMismatchException {
        int n = new Scanner(System.in).nextInt();
        return n;
    }
}
