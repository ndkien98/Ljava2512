package buoi3.baitap;

import java.util.Scanner;

/*
Bài tập:
    Cho phép nhập vào n phần tử của mảng số nguyên, tính tổng các phần tử trong mảng và in ra các số nguyên tố trong mảng đó.
 */
public class Bai6 {

    public static void main(String[] args) {
        // khai báo mảng số nguyên
        int [] arr = null;
        System.out.println("Nhập vào số lượng phần tử của mảng:");
        int n = new Scanner(System.in).nextInt();
        arr = new int[n];
        enterArray(arr);
        printArray(arr);
        // tính tổng các phần tử trong mảng
        int sum = 0;
        System.out.println("Các số nguyên tố trong mảng là:");
        for (int i = 0; i < arr.length; i++) {
            if (isPrime(arr[i])) {
                System.out.println(arr[i]);
            }
            sum += arr[i];
        }
        System.out.println("Tổng các phần tử trong mảng là: " + sum);
    }

    public static void enterArray(int [] arr){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào các phần tử của mảng:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("Phần tử thứ " + (i + 1) + " tại index : " + i + " = ");
            arr[i] = scanner.nextInt();
        }
    }

    public static void printArray(int [] arr){
        System.out.println("Các phần tử trong mảng là:");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("index " + i + " = " + arr[i] + " ; ");
        }
        System.out.println();
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
