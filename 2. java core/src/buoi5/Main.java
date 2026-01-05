package buoi5;

public class Main {


    public static void main(String[] args) {

        // Tạo đối tượng Car1 sử dụng constructor có tham số
        Car car1 = new Car("Toyota", "Camry", 2020, "White", 24000.0);

        //Tạo ra Car4 có thông tin giống Car1 để kiểm tra phương thức equals
        Car car4 = new Car("Toyota", "Camry", 2020, "White", 24000.0);
        /**
         Hiện tại car1 và car4 đang có thông tin giống nhau về các thuộc tính
         nếu so sánh 2 đối tượng nay băng == hoặc equals() thì sao ?
         */
        System.out.println(car1 == car4);
        System.out.println(car1.equals(car4));



    }

}
