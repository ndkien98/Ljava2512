package buoi4.bt;

import java.util.Scanner;

/**
 Bài 4: Tìm kiếm và Sắp xếp Mảng
 Viết chương trình nhập vào một mảng gồm n số nguyên (n do người dùng nhập từ bàn phím).
 Sau đó thực hiện các yêu cầu sau:
 •	Tìm và in ra giá trị lớn nhất (Max) và nhỏ nhất (Min) trong mảng10.
 •	Thực hiện sắp xếp mảng theo thứ tự tăng dần và in mảng sau khi sắp xếp ra màn hình
 •  Tìm phần tử lớn thứ 2 trong mảng
 •  Tìm kiếm một số nguyên x trong mảng và in ra vị trí của nó (nếu có nhiều vị trí thì in tất cả các vị trí, nếu không tìm thấy thì in thông báo không tìm thấy)

 */
public class Bai4 {

    public static void main(String[] args) {

        System.out.println("Bài 4: Tìm kiếm và Sắp xếp Mảng");
        System.out.println("Nhập vào số lượng phần tử của mảng: ");
        int n = new Scanner(System.in).nextInt();
        int[] arr = new int[n];
        enterArray(arr);
        printArray(arr);
        findMaxMin(arr);
        sortArray(arr);
        System.out.println("Mảng sau khi sắp xếp là: ");
        printArray(arr);
        System.out.println("phần tử lớn thứ 2 trong mảng là: " + arr[arr.length - 2]);
        System.out.println("Nhập vào phần tử cần tìm kiếm trong mảng: ");
        int x = new Scanner(System.in).nextInt();
        findElement(arr, x);
    }
    public static void enterArray(int[] arr) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào các phần tử của mảng:");
        // chạy vào lặp for đi qua từng phần tử ô nhớ của mảng, tại mỗi ô nhớ sẽ nhập giá trị từ bàn phím gán vào và lưu trữ trực tiếp vào ô nhớ đó
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Phần tử thứ " + (i + 1) + " tại index " + i + " = ");
            arr[i] = sc.nextInt();
        }
    }
    public static void printArray(int[] arr) {
        System.out.println("Các phần tử trong mảng là:");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("index " + i + " = " + arr[i] + " ; ");
        }
        System.out.println();
    }

    public static void findMaxMin(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        System.out.println("Gía trị lớn nhất trong mảng là: " + max);
        System.out.println("Gía trị nhỏ nhất trong mảng là: " + min);
    }

    public static void sortArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int term = arr[i];
                    arr[i] = arr[j];
                    arr[j] = term;
                }
            }
        }
    }

    public static void findElement(int[] arr, int x) {
        boolean found = false;
        System.out.println("Vị trí của phần tử " + x + " trong mảng là: ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                System.out.println("index " + i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy phần tử " + x + " trong mảng.");
        }
    }
}
