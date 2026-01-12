package buoi7.baitap;

import java.util.Scanner;

public class Utils {

    public static Scanner scanner = new Scanner(System.in);

    public static int scanNumber(){
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }
}
