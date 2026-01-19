package buoi9.baitap;

import java.util.Scanner;

/**
 Hệ thống Quản lý Nhân sự IT
 Bai 1:
 Kiến thức: Encapsulation, Inheritance, Abstract, Interface, Mảng 1 chiều.
 •	Câu 1: Tạo Interface IWorkable với phương thức void doWork().
 •	Câu 2: Tạo lớp trừu tượng Employee triển khai IWorkable. Có thuộc tính id, name, baseSalary.
 •	Câu 3: Tạo lớp Developer (có mảng 1 chiều String[] skills) và lớp Tester (có int bugsFound). Cả hai kế thừa từ Employee.
 •	Câu 4: Ghi đè calculateSalary():
 o	Developer: baseSalary x 1.5.
 o	Tester: baseSalary + (bugsFound x 100,000).
 ________________________________________
 Bài 2:
 Kiến thức: Mảng 2 chiều, Custom Exception, Try-Catch-Finally, Throw/Throws.
 •	Câu 5: Khai báo Employee[][] company để quản lý N phòng ban.
 •	Câu 6: Tạo ngoại lệ InvalidDataException. Viết phương thức nhập lương, nếu lương <= 0 thì throw ngoại lệ này.
 •	Câu 7: Sử dụng try-catch-finally để đảm bảo khi nhập liệu sai, chương trình không bị "văng" mà yêu cầu nhập lại, đồng thời khối finally sẽ in ra trạng thái bộ nhớ sau mỗi phòng ban.
 ________________________________________
 Bài 3:
 Kiến thức: Functional Interface, Lambda, Predicate, Consumer.
 •	Câu 8 (Tự tạo Functional Interface): Tạo một Interface @FunctionalInterface tên là SalaryBonus có một phương thức double calculate(Employee e).
 •	Câu 9 (Sử dụng Lambda): Viết chương trình sử dụng Lambda để định nghĩa các loại thưởng khác nhau mà không cần tạo lớp mới:
 o	Thưởng Tết: Trả về 10% của calculateSalary().
 o	Thưởng dự án: Trả về một con số cố định (ví dụ: 5,000,000).
 •	Câu 10 (Sử dụng Interface có sẵn): Sử dụng java.util.function.Predicate<Employee> và Lambda để viết hàm lọc nhân viên:
 o	Lọc những nhân viên có lương > 20,000,000.
 o	Lọc những Developer có kỹ năng là "Java".
 ________________________________________
 Bài 4:
 Kiến thức: Duyệt mảng bằng Lambda (forEach), Tổng hợp kiến thức.
 •	Câu 11: Viết phương thức processEmployees(Employee[][] company, Consumer<Employee> action). Sử dụng phương thức này để duyệt qua mảng 2 chiều và thực hiện một hành động bất kỳ (ví dụ: in thông tin nhân viên hoặc tăng lương đồng loạt).
 •	Câu 12: Kết hợp xử lý ngoại lệ bên trong Lambda: Khi thực hiện hành động ở câu 11, nếu gặp một ô mảng bị null, hãy sử dụng try-catch để bỏ qua và tiếp tục xử lý các nhân viên khác thay vì dừng chương trình.


 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Chương trình Quản lý Nhân sự IT");
        System.out.println("Nhập vào số phòng ban trong công ty:");
        int n = new java.util.Scanner(System.in).nextInt();
        ICompanyManagement companyManagement = new CompanyManagement(n);
        int choice = 0;
        do {
            System.out.println("Chọn 1 trong các chức năng sau:");
            System.out.println("1. Thêm nhân viên vào phòng ban");
            System.out.println("2. Hiển thị thông tin nhân viên trong công ty");
            System.out.println("3. Tìm kiếm nhân viên theo lương và kỹ năng");
            System.out.println("4. Thoát chương trình");
            choice = new Scanner(System.in).nextInt();
            switch (choice) {
                case 1:
                    companyManagement.inputDepartmentData();
                    break;
                case 2:
                    companyManagement.displayCompanyInfo();
                    break;
                case 3:
                    companyManagement.filterEmployee();
                    break;
                case 4:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }


        }while (choice != 4);
    }
}
