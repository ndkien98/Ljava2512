package buoi5.baitap;

/**
 3.	Viết hàm findCheapest(Product[] list) để tìm sản phẩm có giá rẻ nhất.
 4.	Viết hàm calculateTotalValue(Product[] list) để tính tổng giá trị kho hàng, value = tổng (Price x Quantity)
 */
public class ProductManager {

    private Product[] products;
    private int productCount;

    public ProductManager() {
        products = new Product[100]; // Giả sử sức chứa tối đa là 100 sản phẩm
        productCount = 0;
    }

    public ProductManager(int capacity) {
        products = new Product[capacity];
        productCount = 0;
    }

    public Product findCheapest(Product[] list) {
        if (list == null || list.length == 0) {
            return null; // Trả về null nếu danh sách rỗng
        }
        Product cheapest = list[0];
        for (Product product : list) {
            if (product.getPrice() < cheapest.getPrice()) {
                cheapest = product;
            }
        }
        return cheapest;
    }

    public void calculateTotalValue(Product[] list) {
        double totalValue = 0;
        for (Product product : list) {
            totalValue += product.getPrice() * product.getQuantity();
        }
        System.out.println("Tổng giá trị kho hàng: " + totalValue);
    }

    public int countSameName(String name) {
        name = DataCleaner.formatName(name);
        int count = 0;
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equalsIgnoreCase(name)) {
                count++;
            }
        }
        return count;
    }

    public void add(Product product) {
        if (productCount >= products.length) {
            System.out.println("Không thể thêm sản phẩm mới, kho đã đầy.");
            return;
        }
        products[productCount++] = product;
    }

    public void displayAll() {
        for (int i = 0; i < productCount; i++) {
            System.out.println(products[i].toString());
        }
    }
}
