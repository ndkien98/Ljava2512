package buoi3.btvn.bai2;

public class Main {

    public static void main(String[] args) {

        Computer computer = new Computer();
        computer.setName("Dell XPS 13");

        Computer computer2 = new Computer();
        computer2.setName("MacBook Pro");

        computer.changeDeviceName(computer, "Dell XPS 15");
        computer2.changeDeviceName(computer2, "MacBook Pro 2025");
        System.out.println("Tên máy tính 1: " + computer.getName());
        System.out.println("Tên máy tính 2: " + computer2.getName());
        System.out.println("Tổng số máy tính đã tạo: " + Computer.totalComputersCreated);


    }
}
