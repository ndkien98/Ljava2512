package buoi6.bai1;

public class EmployeeSales extends Employee{

    private int salesAmount;

    public EmployeeSales(String employeeId, String fullName, String address, String phoneNumber, int salesAmount){
        super(employeeId, fullName, address, phoneNumber);
        this.salesAmount = salesAmount;
    }

    public EmployeeSales(){
        super();
        this.salesAmount = 0;
    }

    @Override
    public int calculateSalary() {
        System.out.println("Calculating salary for Sales Employee...");
        return super.calculateSalary();
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Sales Amount: " + salesAmount);
        System.out.println("Salary: " + calculateSalary());
    }

    public int getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(int salesAmount) {
        this.salesAmount = salesAmount;
    }

}
