package buoi9.demo2;

import java.util.Scanner;

public class Demo3 {

    public static void main(String[] args) {
        System.out.println("Nhap so nguyen duong: ");
        int so = nhapSoNguyen();
        if (so != -1){
            System.out.println("So ban vua nhap la: " + so);
        }
    }

    public static int nhapSoNguyen(){
        try {
            int number = new Scanner(System.in).nextInt();
            if (number < 0){
                throw new NumberFormatException("vui long nhap so nguyen duong!");
            }
        }catch (Exception e){
            System.out.println("Loi: " + e.getMessage());
            return -1;
        }
        return -1;
    }
}
