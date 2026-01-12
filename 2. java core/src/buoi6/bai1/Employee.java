package buoi6.bai1;

public class Employee {

    private String employeeId;
    private String fullName;
    private String address;
    private String phoneNumber;

    public Employee(String employeeId, String fullName, String address, String phoneNumber) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Employee() {
    }
    /*
        * Phương thức tính lương cho nhân viên mặc định trả về 0
        * Các lớp con sẽ ghi đè phương thức này để tính lương theo cách riêng của mình
     */
    public int calculateSalary(){
        return 0;
    }

    public void display(){
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Full Name: " + fullName);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
    }


    public void display(String extraInfo){
        display();
        System.out.println("Extra Info: " + extraInfo);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
