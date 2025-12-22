package buoi3.baitap;

/**

 Bài 1: Cấu trúc rẽ nhánh & Hàm (Kiểm tra năm nhuận)
 Yêu cầu: Viết một hàm có tên là checkLeapYear(int year) nhận vào một số nguyên đại diện cho năm.
 •	Hàm trả về kiểu void và in ra màn hình thông báo năm đó là năm nhuận hay năm thường.
 •	Gợi ý logic: Năm nhuận là năm chia hết cho 400 hoặc (chia hết cho 4 nhưng không chia hết cho 100). Học sinh sử dụng cấu trúc if-else hoặc switch-case để xử lý


 */

public class Bai1 {

    public static void main(String[] args) {

       checkLeapYear(2020);
         checkLeapYear(1900);
            checkLeapYear(2000);
            checkLeapYear(2025);

    }

    public static void checkLeapYear(int year) {
        if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)) {
            System.out.println(year + " là năm nhuận");
        } else {
            System.out.println(year + " là năm thường");
        }
    }
}
