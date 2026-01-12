package buoi6.bai1;

public class EmployeeTechnical extends Employee{

    private int completedProjects;

    public EmployeeTechnical(String employeeId, String fullName, String address, String phoneNumber, int completedProjects) {
        super(employeeId,fullName,address,phoneNumber);
        this.completedProjects = completedProjects;
    }

    public EmployeeTechnical() {
        super();
        this.completedProjects = 0;
    }

    @Override
    public int calculateSalary() {
        System.out.println("Calculating salary for Technical Employee...");
        return completedProjects * 200000;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Completed Projects: " + completedProjects);
        System.out.println("Salary: " + calculateSalary());
    }

    public int getCompletedProjects() {
        return completedProjects;
    }

    public void setCompletedProjects(int completedProjects) {
        this.completedProjects = completedProjects;
    }
}
