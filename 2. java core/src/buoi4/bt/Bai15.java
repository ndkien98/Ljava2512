package buoi4.bt;

import java.util.Scanner;

/**
 Bài 15: Xử lý Ma trận - Tổng đường chéo (Square Matrix)
 Cho một mảng hai chiều kích thước n x n (ma trận vuông)
 •	Yêu cầu:
 1. Tính tổng của tất cả các phần tử trong ma trận và in kết quả.
 2. Tính tổng các phần tử nằm trên đường chéo chính và đường chéo phụ của ma trận.

 n = 3 =. ma trận 2 chiều 3x3
 số dòng: 3
 số cột: 3
 => Ma trận: giả sử đây là giá trị của ma trận 3x3
 4 6 3
 4 5 6
 7 8 9
 1. tổng tất cả các phần tử trong ma trận
 duyệt tất cả các dòng, với mỗi dòng duyệt tất cả các cột
 vd:
    dòng 0:
        cột 0: 4
        cột 1: 6
        cột 2: 3
    => tổng dòng 0 = 4 + 6 + 3 = 13
    dòng 1:
        cột 0: 4
        cột 1: 5
        cột 2: 6
    => tổng dòng 1 = 4 + 5 + 6 = 15
    dòng 2:
        cột 0: 7
        cột 1: 8
        cột 2: 9
    => tổng dòng 2 = 7 + 8 + 9 = 24
    => tổng tất cả các phần tử trong ma trận = 13 + 15 + 24 = 52
 2.1  tổng casc phần tử trong đường chéo chính
    + đường chéo chính của ma trận là các phần tử có chỉ số dòng = chỉ số cột
    vd:
        dòng 0, cột 0: 4
        dòng 1, cột 1: 5
        dòng 2, cột 2: 9
    => tổng đường chéo chính = 4 + 5 + 9 = 18
    => công thức tổng đường chéo chính: tổng += ma trận[i][i]
 2.2 tổng các phần tử trong đường chéo phụ
    + đường chéo phụ của ma trận là các phần tử có chỉ số dòng i + j = n - 1 => vị trí của phần tu nằm trên đưng chéo phụ thỏa mãn khi vị trí đó có i + j = n - 1
 i + j = n - 1 <=> j = n - 1 - i
    => có cosng thức tổng đường chéo phụ: tổng += ma trận[i][n - 1 - i]
    vd:
        dòng 0, cột 2: 3
        dòng 1, cột 1: 5
        dòng 2, cột 0: 7
    => tổng đường chéo phụ = 3 + 5 + 7 = 15
    => công thức tổng đường chéo phụ: tổng += ma trận[i][n - 1 - i]
 */

public class Bai15 {

    public static void main(String[] args) {

        System.out.println("Nhập vào chiều n của ma trận vuông n x n:");
        int n = new Scanner(System.in).nextInt();
        // khởi tạo ma trận vuông n x n
        int[][] matrix = new int[n][n];
        // nhập giá trị cho các phần tử trong ma trận
        enterMatrix(matrix, n);
        // in ma trận vừa nhập
        printMatrix(matrix);
        // tính tổng tất cả các phần tử trong ma trận
        sumAllElements(matrix);
        // tính tổng các phần tử trên đường chéo chính
        sumMainDiagonalV1(matrix);
        sumMainDiagonalV2(matrix);
        // tính tổng các phần tử trên đường chéo phụ
        sumSecondaryDiagonal(matrix);
        sumSecondarySecondaryDiagonalV2(matrix);

    }

    public static void enterMatrix(int[][] matrix, int n) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào các phần tử của ma trận:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("Phần tử [%d][%d]: ", i, j);
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        System.out.println("Ma trận vừa nhập:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void sumAllElements(int[][] maxTrix) {
        int sum = 0;
        // duyệt tất cả các hàng
        for (int i = 0; i < maxTrix.length; i++) {
            // ứng với mỗi hàng duyệt tất cả các cột
            for (int j = 0; j < maxTrix[i].length; j++) {
                // cộng dồn giá trị của phần tử vào biến sum
                sum += maxTrix[i][j];
            }
        }
        System.out.println("Tổng tất cả các phần tử trong ma trận là: " + sum);
    }

    /**
     O = n^2
     1. duyệt tất cả các hàng: O(n)
     2. với mỗi hàng duyệt tất cả các cột: O(n)
     3. tổng độ phức tạp: O(n) * O(n) = O(n^2)
     */
    public static void sumMainDiagonalV1(int[][] maxTrix) {
        int sum = 0;
        // duyệt tất cả các hàng
        for (int i = 0; i < maxTrix.length; i++) {
            // ứng với mỗi hàng duyệt tất cả các cột
            for (int j = 0; j < maxTrix[i].length; j++) {
                // kiểm tra nếu phần tử nằm trên đường chéo chính
                if (i == j) {
                    // cộng dồn giá trị của phần tử vào biến sum
                    sum += maxTrix[i][j];
                }
            }
        }
        System.out.println("Tổng các phần tử trên đường chéo chính là: " + sum);
    }

    /**
     O = n
     1. duyệt tất cả các hàng: O(n)
     2. với mỗi hàng lấy phần tử tại cột có chỉ số bằng chỉ số hàng: O(1)
     3. tổng độ phức tạp: O(n) * O(1) = O(n)
     */
    public static void sumMainDiagonalV2(int[][] maxTrix) {
        int sum = 0;
        for (int i = 0; i < maxTrix.length; i++) {
            sum += maxTrix[i][i];
        }
        System.out.println("Tổng các phần tử trên đường chéo chính là cách 2 là: " + sum);
    }

    public static void sumSecondaryDiagonal(int[][] maxTrix) {
        int sum = 0;
        int n = maxTrix.length;
        // duyệt tất cả các hàng
        for (int i = 0; i < maxTrix.length; i++) {
            // ứng với mỗi hàng duyệt tất cả các cột
            for (int j = 0; j < maxTrix[i].length; j++) {
                // kiểm tra nếu phần tử nằm trên đường chéo phụ
                if (i + j == n - 1) {
                    // cộng dồn giá trị của phần tử vào biến sum
                    sum += maxTrix[i][j];
                }
            }
        }
        System.out.println("Tổng các phần tử trên đường chéo phụ là: " + sum);
    }

    public static void sumSecondarySecondaryDiagonalV2(int[][] maxTrix) {
        int sum = 0;
        int n = maxTrix.length;
        for (int i = 0; i < maxTrix.length; i++) {
            sum += maxTrix[i][n - 1 - i];
        }
        System.out.println("Tổng các phần tử trên đường chéo phụ cách 2 là: " + sum);
    }
}
