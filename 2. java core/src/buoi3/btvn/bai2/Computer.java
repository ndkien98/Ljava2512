package buoi3.btvn.bai2;

public class Computer {

    private String name;

    public static int totalComputersCreated = 0;

    public Computer() {
        totalComputersCreated++;
    }

    public void changeDeviceName(Computer computer, String newName) {
        computer.setName(newName);
        System.out.println("Đã đổi tên thiết bị thành: " + newName);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
