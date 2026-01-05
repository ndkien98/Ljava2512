package buoi5;

import java.util.Objects;

// Tạo class Car đại diện cho một chiếc ô tô
public class Car {

    // Thuộc tính của ô tô
    private String make;
    private String model;
    private int year;
    private String color;
    private double price;


    // Constructor để khởi tạo đối tượng Car
    // Hàm khởi tạo không tham số, sử dụng để khởi tạo đối tượng với giá trị mặc định, và báo với JVM cấp phát vùng nhớ cho đối tượng trong heap, để có thể sử dụng được
    // và tránh lỗi NullPointerException
    public Car(){

    }
    // Hàm khởi tạo có tham số, sử dụng để khởi tạo đối tượng với giá trị cụ thể
    public Car(String make, String model, int year, String color, double price) {
        /**
         Để có thể sử dụng được các phương thức, các thuộc tính của 1 đối tượng, cần phải sử dụng cú pháp đối tượng.thuộc tính/method()
         Ví dụ: car1.getMake();
         Đại diện cho việc truy cập vào phương thức getMake() của đối tượng
         Nhưng nếu thao tác ở bên trong class sẽ không thể khởi tạo đối tượng là chính nó và truy cập vao các thuộc tính/method của nó được
         vì vậy trong Java có từ khoá this đại diện cho chính đối tượng hiện tại đang được khởi tạo hoặc đang gọi phương thức, sử dụng bên trong class
         */
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.price = price;
    }

    // Phương thức hiển thị thông tin ô tô
    public void run () {
        System.out.println(String.format("Car %s %s is running.", make, model));
    }

    public void makeSound() {
        System.out.println(String.format("Car %s is making a sound.", make));
    }

    /**
     ghi đè lại phương thức toString từ lớp Object để hiển thị các thông tin của đối tượng Car
     */
    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }

    /**
     Đây là 1 phương thức đặc biệt được cung cấp sẵn trong lớp Object, được sử dụng để so sánh 2 đối tượng có giống nhau về mặt logic nghiệp vụ hoặc dữ liệu 2 không
     nếu không được ghi đè (custom) lại thì phương thức này mặc định sẽ hoạt động như so sánh ==, tức là sánh dịa chỉ ô nhớ được trỏ vào của 2 đối tượng
     => vậy nếu muốn định nghĩa lại cách số sánh 2 đối tượng trong Java, bắt buộc phải ghi đè, định nghĩa lại logic method equals này
     */
    @Override
    public boolean equals(Object obj) {
        // code logic so sánh 2 đối tượng Car dựa trên các thuộc tính make, model, year, color, price
        if (obj == null){
            return false;
        }
        // Nếu đối tượng không phải là 1 kiểu Car(Nếu đối tượng không được tạo ra từ class Car) thì trả về false, instanceof sử dụng để kiểm tra kiểu dữ liệu động của đối tượng tại thời điểm
        if (!(obj instanceof Car)){
            /*
            khi kiểm tra dữ liệu bằng instanceof thì nếu obj là null thì kết quả trả về luôn là false, nên không cần kiểm tra null ở trên nữa cũng được
            và sẽ tránh được lỗi ép kiểu tại dòng số 73 nếu object truyền vào là null hoặc không phải kiểu Car
             */
            return false;
        }
        // ép kiểu đối tượng obj về kiểu Car để so sánh các thuộc tính
        Car car = (Car) obj;
        return this.make.equals(car.make) &&
               this.model.equals(car.model) &&
               this.year == car.year &&
               this.color.equals(car.color) &&
               this.price == car.price;
    }

    /*
    Method hashCode():
        - được sử dụng để trả về một giá trị số nguyên( bằng hàm băm) đại diện cho đối tượng hiện tại.
        - Method được sử dụng trong các cấu trúc dữ liệu quan trọng như HashMap, HashSet để xác định vị trí lưu trữ và truy xuất đối tượng một cách nhanh chóng.
        - Khi ghi đè phương thức equals(), bắt buộc ghi đè cả phương thức hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(make, model, year, color, price);
    }

    // Các method getter và setter

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
