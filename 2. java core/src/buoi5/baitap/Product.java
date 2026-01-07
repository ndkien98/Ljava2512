package buoi5.baitap;

/**
 Bài 2: Quản lý Kho hàng Sản phẩm (Class & Array)
 Mô tả: Tạo lớp Product và quản lý danh sách sản phẩm trong mảng. Bài tập này áp dụng kiến thức về thuộc tính, phương thức khởi tạo và vòng lặp xử lý mảng đối tượng.
 •	Yêu cầu:
 1.	Tạo lớp Product có: id (String), name (String), price (double), quantity (int).
 2.	Trong lớp Main, tạo một mảng Product[] để lưu tối đa 100 sản phẩm.
 3.	Viết hàm findCheapest(Product[] list) để tìm sản phẩm có giá rẻ nhất.
 4.	Viết hàm calculateTotalValue(Product[] list) để tính tổng giá trị kho hàng, value = tổng (Price x Quantity)
 •	Lưu ý: Trước khi gán tên cho Product, phải gọi hàm formatName từ Bài 1 để chuẩn hóa.

 */
public class Product {

    // Thuộc tính
    String id;
    String name;
    double price;
    int quantity;

    // Phương thức khởi tạo
    public Product(String name, double price, int quantity,int index) {
        this.name = DataCleaner.formatName(name);
        this.id = DataCleaner.generateID(name,index);
        this.price = price;
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public Product() {

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
}
