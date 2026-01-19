package buoi9.demo1;

public class Account {

    /*
    việc sử dụng từ khóa private để khai báo thuộc tính chính là triển khai tính đóng gói (encapsulation) trong lập trình hướng đối tượng.
    -> chỉ cho phép truy cập và sửa đổi giá trị thông qua getter và setter là public method
        -> giúp kiểm soát cách thức truy cập và sửa đổi dữ liệu một tùy tiện, bảo vệ tính toàn vẹn của dữ liệu bên trong đối tượng
        vì các method nghiệp vụ hoạt động dựa trên các giá trị thuộc tính này. Nếu không kiểm soát việc truy cập, các thuộc tính
        có thể bị sửa đổi dễ dàng từ bên ngoài dẫn đến các method nghiệp vụ có thể hoạt động sai lệch hoặc không đúng như mong đợi.
     */
    private double balance;
}
