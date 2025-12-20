package buoi2;


import buoi1.MayTinh;

/**

 Bien tham tri
    + khi truy cap vao bien tham tri se tao ra 1 ban sao cua gia tri ban dau

 Bien tham chieu
    + Khi truy cap vao bien tham chieu se khong tao ra ban sao ma se tro den dia chi cua doi tuong trong vung nho heap

 Lưu ý trong java:
    Trong gia tất cả các biến đưc truyền vào trong method đều là biến tham trì, ngoài trừ các kiểu dữ liệu tối dượng, thực chất
     các kiểu dữ liệu đối tượng cũng được truyền vào theo kiểu tham trị, nhưng giá trị của bến tham trị làd địa chỉ của đối tượng
     trong vùng nhớ heap mà đối tượng tham chiếu đến.

 Lưu ý:
    Tất cả các biến khi được truyền vào method đều là biến tham trị, bao gồm cả biến kiểu nguyên thủy và biến tham chiếu đối tượng.
    Tức là:
        - Biến kiểu nguyên thủy: khi truyền vào method sẽ tạo ra 1 bản sao của giá trị ban đầu.
        - Biến kiểu đối tượng: khi truyền vào method sẽ tạo ra 1 bản sao của địa chỉ đối tượng trong vùng nhớ heap.

    Vậy nếu muốn thay đổi giá trị của biến bên ngoài method thì phải làm thế nào?
        - Đối với biến kiểu nguyên thủy: không thể thay đổi giá trị của biến bên ngoài method.
        - Đối với biến kiểu đối tượng: có thể thay đổi các thuộc tính của đối tượng bên ngoài method thông qua bản sao địa chỉ, hoặc nếu cần thay đổi
           tham chiều của biến đối tượng có thể tạo ra 1 object mới, có thuộc tính là bien tham chiếu đến object mới đó. Thì biến bên ngoài method sẽ tham chiếu đến object mới.

 */
public class Main {

    public static void main(String[] args) {

        Computer computer = new Computer();
        computer.BRAND = "A";



        final var A = 1;
        var C = "A";

        // Trường hợp 1 là biến kiểu nguyên thủy
        int a = 4;
        int b = 5;
        int tong = 0;
        sum(a,b,tong);
        System.out.println("tong ngoai sum: " + tong); // gia tri cua bien tong ngoai phuong thuc van la 0
        // Trường hợp 2 là biến kiểu đối tượng
        MayTinh mayTinh = new MayTinh();
        setNull(mayTinh);
        System.out.println("May tinh ngoai setNull: " + mayTinh.toString());

        // Truong hop 3: Sua du lieu bien tham chieu doi tuong
        MayTinh mayTinh2 = new MayTinh();
        mayTinh2.ten = "Laptop Acer Nitro Lite NL16-71G-71UJ (NH.D59SV.002) (i7 13620H/16GB/512GB SSD/RTX3050 6G/16.0FHD 165Hz/Win11/Trắng)";
        System.out.println("Ten may tinh truoc khi thay doi: " + mayTinh2.ten);
        changeName(mayTinh2);
        System.out.println("Ten may tinh sau khi thay doi: " + mayTinh2.ten);

        System.out.println("Trường hợp 4: Thay đổi tham chiếu biến đối tượng");
        MayTinh mayTinh3 = new MayTinh();
        WapperComputer wapperComputer = new WapperComputer(mayTinh3);
        setNull(wapperComputer);
        System.out.println("May tinh ngoai setNull: " + wapperComputer.mayTinh);
    }
    public static void setNull(WapperComputer wapperComputer){
        System.out.println("May tinh truoc khi set null: " + wapperComputer.mayTinh.toString());
        wapperComputer.mayTinh = null;
        System.out.println("May tinh sau khi set null: " + wapperComputer.mayTinh);
    }



    public static void sum(int a, int b,int tong){
        tong = a + b;
        System.out.println("tong trong sum: " + tong);
    }
    public static void setNull(MayTinh mayTinh){
        System.out.println("May tinh truoc khi set null: " + mayTinh.toString());
        mayTinh = null;
        System.out.println("May tinh sau khi set null: " + mayTinh);
    }

    public static void changeName(MayTinh mayTinh){
        mayTinh.ten = "A";
    }
}
