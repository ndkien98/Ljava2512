package buoi7;

import buoi6.bai1.Employee;
import buoi6.bai1.EmployeeOffice;
import buoi6.bai1.EmployeeSales;
import buoi6.bai1.EmployeeTechnical;

/**
 Đa hình trong Java OOP (Polymorphism)

 1. Định nghĩa:


 2. Cách triển khai

 3. Các loại đa hình:
    1. Đa hình thời gian biên dịch (Compile-time Polymorphism) sử dụng chồng phương thức (Method Overloading)
        + Compile-time:
            + là thời điểm trình biên dịch bắt đầu biên dịch mã nguồn java thành mã máy bytecode( từ file .java thành file .class)
            + là thời điểm xác định kiểu dữ liệu tĩnh (Static typing) của biến,
            + là thời điểm xác định phương thức nào sẽ được gọi dựa trên kiểu dữ liệu tĩnh của biến

    2. Đa hình thời gian chạy (Run-time Polymorphism) sử dụng ghi đè phương thức (Method Overriding)
        + Run-time:
            + là thời điểm chương trình đang chạy (sau khi đã biên dịch thành mã máy bytecode và được JVM thực thi)
            + là thời điểm xác định kiểu dữ liệu động (Dynamic typing) của biến,
            + là thời điểm xác định phương thức nào sẽ được gọi dựa trên kiểu dữ liệu động của biến
    Toán tử instanceof:
        - được sử dụng để kiểm tra kiểu dữ liệu động (dynamic type) của một đối tượng tại thời điểm chạy
        - cú pháp: object instanceof ClassName
        - trả về true nếu đối tượng là một thể hiện của lớp ClassName hoặc lớp con của nó, ngược lại trả về false

 5. kiểu wrapper
    + các kiểu dữ liệu nguyên thủy trong Java:
        int, byte, short, long, float, double, char, boolean
    => tồn tại các lớp wrapper tương ứng để hỗ trợ OOP:
        Integer, Byte, Short, Long, Float, Double, Character, Boolean

 */

public class Main {

    public static void main(String[] args) {


        /**
         Class
         Employee empOffice
         sẽ có phương thức tính lương: calculateSalary()
         nhưng
         + khi employee được khởi tạo từ lớp con EmployeeOffice thì phương thức calculateSalary() sẽ được gọi là phương thức được ghi đè của lớp EmployeeOffice
         + khi employee được khởi tạo từ lớp con EmployeeTechnical thì phương thức calculateSalary() sẽ được gọi là phương thức được ghi đè của lớp EmployeeTechnical
         + khi employee được khởi tạo từ lớp con EmployeeSales thì phương thức calculateSalary() sẽ được gọi là phương thức được ghi đè của lớp EmployeeSales
         => Đây là tính đa hình trong OOP (Polymorphism)
         */
        // ex đa hình động:
        Employee employee = new Employee();
        System.out.println("Lương nhân viên (Employee): " + employee.calculateSalary());
        employee = new EmployeeOffice("E004", "Pham Thi D", "321 Le Lai", "0909123456", 20);
        System.out.println("Lương nhân viên (EmployeeOffice): " + employee.calculateSalary());
        employee = new EmployeeTechnical("E005", "Hoang Van E", "654 Phan Chu Trinh", "0934567890", 3);
        System.out.println("Lương nhân viên (EmployeeTechnical): " + employee.calculateSalary());
        employee = new EmployeeSales("E006", "Dang Thi F", "987 Le Duan", "0956789012", 30000000);
        System.out.println("Lương nhân viên (EmployeeSales): " + employee.calculateSalary());
    }
}
