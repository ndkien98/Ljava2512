package buoi6;

import buoi6.bai1.Employee;
import buoi6.bai1.EmployeeOffice;
import buoi6.bai1.EmployeeSales;
import buoi6.bai1.EmployeeTechnical;

/**
I.
 Tính kế thừa trong Java
 Tại sao cần tính kế thừa:
    bài toán:
        xây dựng hệ thống quản lý nhân viên cho một công ty, trong đó có các loại nhân viên như
                Nhân viên văn phòng,
                nhân viên kỹ thuật,
                nhân viên bán hàng.
    Mỗi loại nhân viên có các thuộc tính và phương thức riêng biệt, nhưng cũng có những thuộc tính và phương thức chung.

     Các thuộc tính chung:
        - Mã nhân viên
        - Tên nhân viên
        - Địa chỉ
        - Số điện thoại
    Các phương thức chung:
        - Tính lương
        - Hiển thị thông tin nhân viên
    Thuộc tính riêng của từng nhân viên:
        - Nhân viên văn phòng: số ngày làm việc
        - Nhân viên kỹ thuật: số dự án đã hoàn thành
        - Nhân viên bán hàng: doanh số bán hàng
    Mỗi loại nhân viên sẽ có các cách tính lương khác nhau dựa trên các thuộc tính riêng của họ.
 Yêu cầu:
    Xây dựng các lớp để biểu diễn các loại nhân viên trên,
 Thay vì phải viết lại toàn bộ các thuộc tính và phương thức chung cho từng loại nhân viên, sẽ gây ra trùng lặp code rất nhiều và khó báo trì vì khi sửa sẽ phải sửa
 ở nhiều nới khác nhau, dẫn đến lỗi không đồng bộ.
 => Giải pháp: Áp dụng tính kế thừa trong lập trình hướng đối tượng

 Supper class (lớp cha):
        Employee:
            thuộc tính: employeeId, fullName, address, phoneNumber
            phương thức: calculateSalary(), displayInfo()
 Sub class (lớp con):
        EmployeeOffice (nhân viên văn phòng): kế thừa từ Employee
            thuộc tính riêng: workingDays
            phương thức riêng: calculateSalary() (tính lương dựa trên số ngày làm việc)
        EmployeeTechnical (nhân viên kỹ thuật): kế thừa từ Employee
            thuộc tính riêng: completedProjects
            phương thức riêng: calculateSalary() (tính lương dựa trên số dự án)
        EmployeeSales (nhân viên bán hàng): kế thừa từ Employee
            thuộc tính riêng: salesAmount
            phương thức riêng: calculateSalary() (tính lương dựa trên doanh số bán hàng)

 yêu cầu:
    1. Hoàn thiện các class lớp con
    2. Tạo các đối tượng từ các lớp con và gọi các phương thức để hiển thị thông tin và tính lương cho từng loại nhân viên

 1. Định nghĩa:
    - Là mối quan hệ giữa các class với nhau trong Java, trong đó sẽ có 1 class là lớp cha(supper class) và lớp con (sub class)
    nếu lớp con kế thừa từ lớp cha thì lớp con sẽ có tất cả các phương thức, thuộc tính của lớp cha, đồng thời lớp con có thể triển khai thêm các phương thức,
    thuộc tính riêng của nó. Giúp giảm thiểu trùng lặp code, tăng tái sử dụng và bảo trì mã nguồn.

 2. Tính chất
    - sub class kế thừa tất cả các thuộc tính và phương thức của supper class
    - sub class có thể thêm các thuộc tính và phương thức riêng của nó
    - sub class có thể triển khai lại(ghi đè) các phương thức của supper class đề phù hợp với nghiệp vụ của mình
    - sub class có thể gọi lại các phương thức của lớp cha qua tư khóa supper
    - nếu các phương thức và thuộc tính của lớp cha la private thì lớp con không thể truy cập trực tiếp được
    - Java chỉ hỗ trợ kế thừa đơn, tức là một lớp con chỉ được kế thừa tư 1 lớp cha duy nhất, có hỗ trợ đa kế thừa không chính thức thông qua interface
 3. Cách triển khai
    - Sử dụng tư khóa extends để khai báo lớp con kế thừa từ lớp cha
    cú pháp:
        class SubClass extends SuperClass {
            // các thuộc tính và phương thức riêng của lớp con
        }

 4. Ghi đè phường thức
    - La việc lớp con triển khai lại các phương thức của lớp cha để phù hợp với nghiệp vụ của lớp con
    - Sử dụng tư khóa @Override để đánh dấu việc ghi đè phương thức, có thể sử dụng hoặc không sử dụng tư khóa này\
    - Các method ghi đè có đặc điểm:
        + có cùng tên phương thức với phương thức của lớp cha
        + có cùng kiểu trả về với phương thức của lớp cha
        + có cùng danh sách tham số với phương thức của lớp cha
    - Sảy ra giữa 2 class có tính kế thừa với nhau

 5. Nạp chồng phương thức
    - Là việc 1 class có thể có nhiều phương thức cùng tên nhưng khác nhau vê danh sách tham số truyền vào( số lươợng tham số hoặc kiểu dữ liệu tham số)
    - Việc nạp chồng phương thức giúp tăng tính linh hoạt và khả năng mở
    - Chỉ sảy ra trong cùng 1 class, không liên quan đến kế thừa giữa các class

 II. Tính đa hinh

    1. định nghĩa

    2. Bản chất

    3. Điều kiện để sử dụng

    4. Các cách đa hình
 */



public class Main {

    public static void main(String[] args) {

        Employee empOffice = new EmployeeOffice("E001", "Nguyen Van A", "123 Le Loi", "0123456789", 22);
        empOffice.display();
        System.out.println("Lương nhân viên văn phòng: " + empOffice.calculateSalary());
        System.out.println("------------------------------");
        Employee empTechnical = new EmployeeTechnical("E002", "Tran Thi B", "456 Tran Hung Dao", "0987654321", 5);
        empTechnical.display();
        System.out.println("Lương nhân viên kỹ thuật: " + empTechnical.calculateSalary());
        System.out.println("------------------------------");
        Employee empSales = new EmployeeSales("E003", "Le Van C", "789 Nguyen Trai", "0912345678", 50000000);
        empSales.display();
        System.out.println("Lương nhân viên bán hàng: " + empSales.calculateSalary());




    }

}
