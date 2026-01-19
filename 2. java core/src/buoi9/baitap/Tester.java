package buoi9.baitap;


public class Tester extends Employee {
    private int bugsFound;

    public Tester(String id, String name, double baseSalary, int bugsFound) {
        super(id, name, baseSalary);
        this.bugsFound = bugsFound;
    }

    public int getBugsFound() {
        return bugsFound;
    }

    public void setBugsFound(int bugsFound) {
        this.bugsFound = bugsFound;
    }

    @Override
    public double calculateSalary() {
        return baseSalary + (bugsFound * 100_000);
    }

    @Override
    public void doWork() {
        System.out.println(name + " đang kiểm thử phần mềm. Đã tìm thấy " + bugsFound + " bugs.");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Bugs Found: " + bugsFound);
        System.out.println("Position: Tester");
    }
}
