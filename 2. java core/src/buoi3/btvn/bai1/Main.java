package buoi3.btvn.bai1;

public class Main {


    public static void main(String[] args) {

        Product product = new Product("Smartphone XYZ", 8000000, 15);
        Product product1 = new Product("Headphones ABC", 2000000, 5);
        Product product2 = new Product("Laptop DEF", 15000000, 8);

        caculateTotalPrice(product);
        caculateTotalPrice(product1);
        caculateTotalPrice(product2);
    }

    public static void caculateTotalPrice(Product product) {
        double discount = product.getQuantity() > 10 ? 0.10 : 0.05;
        double totalPrice = (product.getPrice() * product.getQuantity()) * (1 - discount);
        System.out.println("Sản phẩm: " + product.getProductName());
        System.out.println("Số lượng: " + product.getQuantity());
        System.out.println("Giảm giá: " + (discount * 100) + "%");
        System.out.println("Tổng tiền sau giảm giá: " + totalPrice + " VND");

    }
}
