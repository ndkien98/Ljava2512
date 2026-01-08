package buoi6.bai1;

public class EmployeeOffice extends Employee{

    private int workingDays;

    // khung tao constructor khong doi so
    public EmployeeOffice() {
        // gọi constructor của lớp cha
        super();
    }

    public EmployeeOffice(String employeeId, String fullName, String address, double phoneNumber, int workingDays) {
        // gọi constructor của lớp cha truyền vào các tham số
        super(employeeId, fullName, address, phoneNumber);
        this.workingDays = workingDays;
    }

    /**
     ghi đè lại phương thức calculateSalary từ lớp Employee
     Công thức tính lương cho nhân viên văn phòng:
        Lương = Số ngày làm việc * 100000
     */
    @Override
    public int calculateSalary() {
        return workingDays * 100000;
    }

    @Override
    public void display() {
        // gọi phương thức display của lớp cha để hiển thị thông tin chung của lớp cha
        super.display();
        // hiển thị thông tin riêng của lớp con
        System.out.println("Working Days: " + workingDays);
        System.out.println("Salary: " + calculateSalary());
    }

    public int getWorkingDays() {
        return workingDays;
    }
    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

}
