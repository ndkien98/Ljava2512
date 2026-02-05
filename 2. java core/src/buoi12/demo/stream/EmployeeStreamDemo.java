// Ví dụ này minh họa cách lọc nhân viên trên 30 tuổi, sắp xếp theo tên và lấy email của họ.
// Thực hiện bằng cả cách truyền thống (for-each) và sử dụng Stream API để thấy sự ngắn gọn, rõ ràng của Stream.

package buoi12.demo.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeStreamDemo {
    static class Employee {
        private String name;
        private int age;
        private String email;

        public Employee(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
        public String getEmail() { return email; }
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Tung", 28, "tung@gmail.com"),
                new Employee("Hoa", 32, "hoa@gmail.com"),
                new Employee("Binh", 35, "binh@gmail.com"),
                new Employee("An", 25, "an@gmail.com"),
                new Employee("Tam", 40, "tam@gmail.com")
        );
        // 1. Cách truyền thống (for-each)
        List<String> emailsTraditional = new ArrayList<>();
        List<Employee> filtered = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getAge() > 30) {
                filtered.add(e);
            }
        }
        filtered.sort(Comparator.comparing(Employee::getName));
        for (Employee e : filtered) {
            emailsTraditional.add(e.getEmail());
        }
        System.out.println("Email nhân viên >30 tuổi (truyền thống): " + emailsTraditional);
        // 2. Sử dụng Stream API
        List<String> emailsStream = employees.stream()
                .filter(e -> e.getAge() > 30) // Lọc tuổi > 30
                .sorted(Comparator.comparing(Employee::getName)) // Sắp xếp theo tên
                .map(Employee::getEmail) // Lấy email
                .collect(Collectors.toList());
        System.out.println("Email nhân viên >30 tuổi (Stream API): " + emailsStream);
    }
}
