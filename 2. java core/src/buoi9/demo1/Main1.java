package buoi9.demo1;

/**
 Nó còn là một tiến trình đóng gói code và dữ liệu lại với nhau vào trong một đơn vị unit

 ví có 1 bài toán tính giá trị hình chữ nhật
    + thông thường để tính giá trị hình chữ nhật chỉ cần 2 biến là chiều dài và chiều rộng
    => Nhưng sẽ chỉ thuận lợi khi bài toán độ tính toàn đơn giản và chỉ dành cho 1 hình chữ nhật tại 1 thời điểm và địa điểm
 thay vì vậy sẽ đóng gói các thuộc tính, phương thức liên quan đến hình chữ nhật vào 1 class tên là HinhChuNhat
    + Trong class HinhChuNhat sẽ có các thuộc tính như chiều dài, chiều rộng
    + Trong class HinhChuNhat sẽ có các phương thức như tính diện tích, tính chu vi, in thông tin hình chữ nhật
 => khi đã đóng gói như vậy sẽ có thể tái sử dụng lại class HinhChuNhat ở bất cứ đâu và lúc nào trong chương trình mà không cần quan tâm đến việc
    + Chiều dài, chiều rộng được lấy từ đâu
    + Cách tính diện tích, chu vi như thế nào
  Chỉ cần biết rằng khi có 1 đối tượng HinhChuNhat thì có thể gọi các phương thức để tính diện tích, chu vi hoặc in thông tin
 */
public class Main1 {

    public static void main(String[] args) {


        int chieuDai = 5;
        int chieuRong = 10;
        int dienTich = tinhDienTich(chieuDai, chieuRong);
        System.out.println("Diện tích hình chữ nhật: " + dienTich);

    }

    public static int tinhDienTich(int dai, int rong){
        return dai * rong;
    }
}
