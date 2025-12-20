package buoi2;

/**

 Biến local:
    Biến local là biến được khai báo bên trong một phương thức,
    khối lệnh hoặc constructor và chỉ có phạm vi sử dụng trong phạm vi đó.

 Biến instance(globe):
    + Là biến được khai báo bên trong một lớp nhưng bên ngoài bất kỳ phương thức nào
    + Có thể coi các thuộc tính của class chính là các biến instance.
    => Vì
        + chúng được tạo ra khi một đối tượng của lớp được khởi tạo và tồn tại trong suốt vòng đời của đối tượng đó
            vd:
                Computer computer1 = new Computer();
                Computer computer2 = new Computer();
                    khi tạo 2 đối tượng computer1 và computer2 thì mỗi đối tượng sẽ có bộ nhớ riêng để lưu trữ các biến instance của nó
        + Có thể được truy cập tại bất cứ phương thức nào trong lớp đó thông qua this, hoặc thông qua đối tượng của lớp đó nếu ở ngoài class

 Biến static:
    + Cũng là 1 biến nhưng nằm trong vùng nhớ method area, khác với với vùng nhớ head, và stack
    + Không thuộc về bất kỳ đối tượng nào của lớp mà chỉ thuộc về chính lớp đó
    + Để truy cập vào biên static, không cần khởi tạo đối tượng của lớp, mà có thể truy cập trực tiếp thông qua tên lớp đó
    + Được chia sẻ chung cho tất cả các đối tượng của lớp đó
    + Được khai báo bằng từ khoá static

- method static:
    + Cũng là 1 method nằm trong vùng nhớ method area
    + Chỉ lên truy cập thông qua tên lớp, không cần khởi tạo đối tượng để truy cập
    + Method static chỉ có thể truy cập vào các biến static khác trong cùng lớp, không thể truy cập trực tiếp vào các biến instance
      vì biến instance thuộc về đối tượng cụ thể, trong khi method static không thuộc về bất kỳ đối tượng nào.
 */

public class Computer {

    public static String BRAND; // Biến static

    private String name; // Vừa là thộc tính của class, vừa là biến instance
    private int ram; // Vừa là thộc tính của class, vừa là biến instance

    public static void main(String[] args) {

        Computer computer1 = new Computer();
        computer1.exampleMethod();;
        Computer computer2 = new Computer();
        Computer.BRAND = "A";;
    }

    // Phương thức ví dụ sử dụng biến local
    public void exampleMethod() {
        int localVariable = 10; // Biến local
        System.out.println("Giá trị của biến local: " + localVariable);
        System.out.println(this.name);
        // truy câp biến static
    }

    Computer() {
        this.name = "Default Computer";
        this.ram = 8;
    }
}
