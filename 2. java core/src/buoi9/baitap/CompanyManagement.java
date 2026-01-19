package buoi9.baitap;


import java.util.Scanner;
import java.util.function.Predicate;

public class CompanyManagement implements ICompanyManagement{
    private Employee[][] company;
    private int numberOfDepartments;

    public CompanyManagement(int numberOfDepartments) {
        this.numberOfDepartments = numberOfDepartments;
        this.company = new Employee[numberOfDepartments][];
    }

    public CompanyManagement() {
        this.numberOfDepartments = 3; // Mặc định 3 phòng ban
        this.company = new Employee[numberOfDepartments][];
    }

    // Phương thức nhập lương với validation
    public double inputSalary() throws InvalidDataException {
        try {
            double salary = new Scanner(System.in).nextDouble();

            // Validate lương
            if (salary <= 0) {
                throw new InvalidDataException("Lương phải lớn hơn 0!");
            }
            return salary;
        }catch (Exception e) {
            throw new InvalidDataException("Định dạng lương không hợp lệ!");
        }
    }

    // Phương thức nhập thông tin nhân viên cho từng phòng ban
    public void inputDepartmentData() {
        System.out.println("\n=== NHẬP THÔNG TIN CÔNG TY ===");
        System.out.println("Nhập vào tên phòng ban muốn thêm nhân viên:");
        int departmentIndex = new Scanner(System.in).nextInt() - 1;
        System.out.print("Nhập số lượng nhân viên trong phòng ban này: ");
        int numberOfEmployees = new Scanner(System.in).nextInt();
        Scanner scanner = new Scanner(System.in);
        company[departmentIndex] = new Employee[numberOfEmployees];

        System.out.println("=== NHẬP THÔNG TIN PHÒNG BAN " + (departmentIndex + 1) + " ===");

        for (int i = 0; i < numberOfEmployees; i++) {
            System.out.println("\n--- Nhân viên " + (i + 1) + " ---");

            try {
                System.out.print("Loại nhân viên (1-Developer, 2-Tester): ");
                int type = scanner.nextInt();
                scanner.nextLine();

                System.out.print("ID: ");
                String id = scanner.nextLine();

                System.out.print("Tên: ");
                String name = scanner.nextLine();

                System.out.print("Lương cơ bản: ");
                double salary = inputSalary();

                Employee employee;
                if (type == 1) {
                    System.out.print("Số lượng kỹ năng: ");
                    int numSkills = scanner.nextInt();
                    scanner.nextLine();

                    String[] skills = new String[numSkills];
                    for (int j = 0; j < numSkills; j++) {
                        System.out.print("Kỹ năng " + (j + 1) + ": ");
                        skills[j] = scanner.nextLine();
                    }
                    employee = new Developer(id, name, salary, skills);
                } else {
                    System.out.print("Số bug đã tìm thấy: ");
                    int bugsFound = scanner.nextInt();
                    employee = new Tester(id, name, salary, bugsFound);
                }

                company[departmentIndex][i] = employee;
                System.out.println("Thêm nhân viên thành công!");

            } catch (InvalidDataException e) {
                System.out.println("Lỗi: " + e.getMessage());
                System.out.println("Vui lòng nhập lại nhân viên này.");
                i--;
            } catch (Exception e) {
                System.out.println("Lỗi nhập liệu: " + e.getMessage());
                System.out.println("Vui lòng nhập lại.");
                scanner.nextLine();
                i--;
            } finally {
                // In trạng thái bộ nhớ
                Runtime runtime = Runtime.getRuntime();
                long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
                System.out.println("Bộ nhớ đang sử dụng: " + usedMemory + " MB");
            }
        }
    }


    // Hiển thị thông tin toàn bộ công ty
    public void displayCompanyInfo() {
        SalaryBonus bonusNewYear = e -> e.calculateSalary() * 0.1;
        SalaryBonus bonusProject = e -> 5000000;

        System.out.println("\n========== THÔNG TIN CÔNG TY ==========");
        for (int i = 0; i < numberOfDepartments; i++) {
            if (company[i] != null) {
                System.out.println("\n--- PHÒNG BAN " + (i + 1) + " ---");
                for (int j = 0; j < company[i].length; j++) {
                    if (company[i][j] != null) {
                        System.out.println("\nNhân viên " + (j + 1) + ":");
                        company[i][j].displayInfo();
                        System.out.println("Tiền thưởng Tết: " + bonusNewYear.calculate(company[i][j]));
                        System.out.println("Tiền thưởng Dự án: " + bonusProject.calculate(company[i][j]));
                        System.out.println("---");
                    }
                }
            }
        }
    }

    public void filterEmployee(){
        System.out.println("Nhập vào lương muốn lọc: ");
        double salaryFilter = new Scanner(System.in).nextDouble();
        System.out.println("Nhập vào kỹ năng muốn lọc: ");
        String skillFilter = new Scanner(System.in).nextLine();

        // Predicate để lọc theo lương
        Predicate<Employee> salaryPredicate = e -> e.calculateSalary() > salaryFilter;

        // Predicate để lọc Developer có kỹ năng cụ thể
        Predicate<Employee> skillDev = e -> {
            if (!(e instanceof Developer)) {
               return false;
            }
            Developer dev = (Developer) e;
            for (String skill : dev.getSkills()) {
                if (skill.equalsIgnoreCase(skillFilter)) {
                    return true;
                }
            }
            return false;
        };

        // Lọc nhân viên có lương cao
        System.out.println("\n1. Nhân viên có lương > " + String.format("%,.0f", salaryFilter) + " VND:");
        for (Employee[] department : company) {
            if (department == null) {
                continue;
            }
            for (Employee emp : department) {
                if (emp != null && salaryPredicate.test(emp)) {
                    System.out.println("   - " + emp.getName() + " - Lương: " + String.format("%,.0f", emp.calculateSalary()) + " VND");
                }
            }
        }

        // Lọc Developer có kỹ năng cụ thể
        System.out.println("\n2. Developer có kỹ năng \"" + skillFilter + "\":");
        for (Employee[] department : company) {
            if (department == null) {
                continue;
            }
            for (Employee emp : department) {
                if (emp != null && skillDev.test(emp)) {
                    Developer dev = (Developer) emp;
                    System.out.println("   - " + dev.getName() + " - Kỹ năng: " + String.join(", ", dev.getSkills()));
                }
            }
        }
    }
}