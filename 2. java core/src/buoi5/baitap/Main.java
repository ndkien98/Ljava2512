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

 Unit Test Demo:
 •	Input: * SP1: id="A1", name=" tivi ", price=5000, quantity=2
 o	SP2: id="A2", name=" chuot ", price=200, quantity=10
 •	Output: * Tên chuẩn hóa: Tivi, Chuot
 o	Sản phẩm rẻ nhất: Chuot (200.0)
 o	Tổng giá trị kho: (5000*2) + (200*10) = 12000.0

 */
public class Main {

    public static void main(String[] args) {
        ProductManager manager = new ProductManager();

        String name1 = "   DOi moi   Tivi   ";
        String name2 = " chuoi      may TINH          ";
        String name3 = "   DOI MOI   Tivi   ";
        Product product1 = new Product(name1, 5000, 2,manager.countSameName(name1));
        manager.add(product1);
        Product product2 = new Product(name2, 200, 10,manager.countSameName(name2));
        manager.add(product2);
        Product product3 = new Product(name3, 200, 10,manager.countSameName(name3));
        manager.add(product3);
        manager.displayAll();
    }


}
