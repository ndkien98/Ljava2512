package buoi6;


/**
 - Trong 1 file chỉ chứa 1 class
 */
class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;

    public Product() {
    }

    public Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void showInfo() {
        double total = price * quantity;
        System.out.printf("[ID: %s| Tên: %s| Giá: %.2f| SL: %d| Tổng: %.2f]\n",
                id, name, price, quantity, total);
    }
}

/**
 - Tên file phải viết hóa các chữ cái đầu của mỗi từ
    - Tên class phải trùng với tên file
 - từ khóa static:
    - thuộc về class chứ không thuộc về đối tượng
    - có thể truy cập trực tiếp thông qua tên class mà không cần tạo đối tượng
    - Chỉ có thể có duy nhất 1 bản sao của biến, method static trong bộ nhớ

 có class QuanLyKhoHang:
    - mục tiêu lập trình hướng đối tượng là trìu tượng hóa các đối tượng trong thực tế thành các đối tượng trong lập trình
    trong bài tập này class QuanLyKhoHang sẽ đóng vai trò là class bản sao để tạo ra nhiều đối tượng Quản lý kho hàng khác nhau
      ví dụ:
        + Quản lý kho hàng điện tử
        + Quản lý kho hàng thực phẩm
        + Quản lý kho hàng thời trang
    và trong đó tất cả các kho hàng này đều sẽ có các thuộc tính giống nhau ví dụ :\
        * thuộc tính:
            danh sách sản phẩm: products
            số lượng sản phẩm hiện tại: count
        + nếu trong class các thuộc tính này đang để static thì tất cả các đối tượng kho hàng được tạo ra từ class này đều sẽ dùng chung 1 danh sách sản phẩm và số lượng sản phẩm cũng sẽ dùng chung
         nhưng thực tế mỗi kho hàng sẽ có danh sách sản phẩm và số lượng sản phẩm khác nhau
    => các thuộc tính này không được để static
        * Các method:
            các method của class thuộc về nghiệp vụ không lên để static vì các method hoạt động có thể sẽ dựa vào giá trị riêng của từng đối tượng, nếu
            các method để static thì các method thì các method sẽ không truy cập được vào các thuộc tính giá trị riêng của từng đối tượng'
   Chỉ lên tạo ra các class với các thuộc tính, phương thức static với các class theo hướng xử lý các method tiện ích(utils), không thuộc nghiệp vụ của hệ
    thống.
   ví dụ:
        các class utils xử lý các phương thức tiện ích như:
            + class StringUtils: xử lý các phương thức tiện ích liên quan đến chuỗi
            + class MathUtils: xử lý các phương thức tiện ích liên quan đến toán học
            + class DateUtils: xử lý các phương thức tiện ích liên quan đến ngày tháng năm
            + các class dùng chung khác


 */
public class QuanLyKhoHang {
    private Product[] products = new Product[100];
    private int count = 0;


    public static void main(String[] args) {

        QuanLyKhoHang khoHangDienTu = new QuanLyKhoHang();
        int choice;
        do {
            System.out.println("==================MENU=================");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Hiển thị và thống kê");
            System.out.println("3. Tìm kiếm sản phẩm rẻ nhất");
            System.out.println("4. Cập nhật số lượng(sửa)");
            System.out.println("5. Xóa sản phẩm");
            System.out.println("6. Sắp xếp sản phẩm theo giá giảm dần");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            choice = Utils.sc.nextInt();
            Utils.sc.nextLine();
            System.out.println("======================================");

            switch (choice) {
                case 1:
                    khoHangDienTu.addProduct();
                    break;
                case 2:
                    khoHangDienTu.displayProduct();
                    double totalValue = khoHangDienTu.calculateTotalValue();
                    System.out.printf("Tổng giá trị kho hàng: %.2f\n", totalValue);
                    break;
                case 3:
                    khoHangDienTu.findCheapestProduct();
                    break;
                case 4:
                    khoHangDienTu.updateProductQuantity();
                    break;
                case 5:
                    khoHangDienTu.deleteProduct();
                    break;
                case 6:
                    khoHangDienTu.sortProductsByPriceDeUtils();
                    khoHangDienTu.displayProduct();
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        } while (choice != 0);
    }


    /**
     giả sử:
        tất cả sản phẩm đều được lưu trữ vào trong cơ sở dữ liệu
        khi thêm sản phẩm mới vào kho hàng cần kiểm tra mã sản phẩm đã tồn tại trong kho hàng chưa
        nếu đã tồn tại thì không được thêm mới và yêu cầu nhập mã sản
     method này đang query vào trong cơ sở dữ liệu để kiểm tra mã sản phẩm đã tồn tại hay chưa
     */
    public boolean isExist(String id) {
        for (int i = 0; i < count; i++) {
            if (products[i].getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public void addProduct() {
        if (count >= 100) {
            System.out.println("Kho hàng đã đầy, không thể thêm sản phẩm mới.");
            return;
        }

        /*
        nếu trường hợp phải kiểm tra mã sản phẩm có tồn tại trong database hay không, không lên dùng vòng lặp for để query
        trong database vì sẽ rất tốn thời gian và tài nguyên hệ thống. Vì phải tạo nhiều query nhiều lần để kiểm tra từng mã sản phẩm
        cách tối ưu lên lấy danh sách các id của sản phẩm lên trước, sau đó sẽ chỉ kiểm tra trên danh sách id đó, không query lại hay kiểm tra với object toàn bộ sản phẩm
         */
        String id;
        do {
            System.out.print("Nhập mã sản phẩm: ");
            id = Utils.sc.nextLine();
            if (isExist(id)) {
                System.out.println("Mã sản phẩm đã tồn tại. Vui lòng nhập mã khác.");
            }
        } while (isExist(id));

        System.out.print("Nhập tên sản phẩm: ");
        String name = Utils.formatName(Utils.sc.nextLine());

        System.out.print("Nhập giá sản phẩm: ");
        double price = Utils.sc.nextDouble();

        System.out.print("Nhập số lượng sản phẩm: ");
        int quantity = Utils.sc.nextInt();
        Utils.sc.nextLine();

        products[count++] = new Product(id, name, price, quantity);
        System.out.println("Thêm sản phẩm thành công");
    }

    public void displayProduct() {
        if (count == 0) {
            System.out.println("Kho hàng trống.");
            return;
        }
        for (int i = 0; i < count; i++) {
            products[i].showInfo();
        }
    }

    public double calculateTotalValue() {
        double totalValue = 0;
        for (int i = 0; i < count; i++) {
            totalValue += products[i].getPrice() * products[i].getQuantity();
        }
        return totalValue;
    }

    public void findCheapestProduct() {
        if (count == 0) {
            System.out.println("Kho hàng trống.");
            return;
        }
        Product cheapest = products[0];
        for (int i = 1; i < count; i++) {
            if (products[i].getPrice() < cheapest.getPrice()) {
                cheapest = products[i];
            }
        }
        System.out.println("Sản phẩm rẻ nhất:");
        System.out.printf("Mã SP: %s, Tên SP: %s, Giá: %.2f, Số lượng: %d\n",
                cheapest.getId(),
                cheapest.getName(),
                cheapest.getPrice(),
                cheapest.getQuantity());
    }

    public void updateProductQuantity() {
        System.out.print("Nhập mã sản phẩm cần cập nhật: ");
        String id = Utils.sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (products[i].getId().equalsIgnoreCase(id)) {
                System.out.print("Nhập số lượng mới: ");
                int newQuantity = Utils.sc.nextInt();
                products[i].setQuantity(newQuantity);
                System.out.println("Cập nhật số lượng thành công");

                System.out.print("Nhập giá mới của sản phẩm: ");
                double newPrice = Utils.sc.nextDouble();
                Utils.sc.nextLine();
                products[i].setPrice(newPrice);
                System.out.println("Cập nhật giá thành công!");
                return;
            }
        }
        System.out.println("Sản phẩm không tồn tại.");
    }

    public void deleteProduct() {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String id = Utils.sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (products[i].getId().equalsIgnoreCase(id)) {
                for (int j = i; j < count - 1; j++) {
                    products[j] = products[j + 1];
                }
                count--;
                System.out.println("Xóa sản phẩm thành công!");
                return;
            }
        }
        System.out.println("Không tìm thấy sản phẩm cần xóa.");
    }

    public void sortProductsByPriceDeUtils() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (products[i].getPrice() < products[j].getPrice()) {
                    Product temp = products[i];
                    products[i] = products[j];
                    products[j] = temp;
                }
            }
        }
        System.out.println("Sản phẩm đã được sắp xếp theo giá giảm dần.");
    }
}
