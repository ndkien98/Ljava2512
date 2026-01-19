package buoi9.baitap;


import java.util.Arrays;

public class Developer extends Employee {
    private String[] skills;

    public Developer(String id, String name, double baseSalary, String[] skills) {
        super(id, name, baseSalary);
        this.skills = skills;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    @Override
    public double calculateSalary() {
        return baseSalary * 1.5;
    }

    @Override
    public void doWork() {
        System.out.println(name + " đang lập trình với các kỹ năng: " + Arrays.toString(skills));
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Skills: " + Arrays.toString(skills));
        System.out.println("Position: Developer");
    }
}