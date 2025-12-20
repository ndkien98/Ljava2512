package buoi1;

import java.util.Scanner;

// khai báo class, la khung, bản thiết kế để tạo ra đối tượng
public class MayTinh {

    // khai báo các thuộc tính của class, giá trị của thuộc tính biểu thị trạng thái của đối tưng
    public String ten;
    public Double giaCu;
    Double giaMoi;
    String trangThai;

    // khai báo các method, các method thể hiện các hành động của đối tượng
    public void hienThiThongTin(){
        System.out.println("Tên: " + ten);
        System.out.println("Giá cũ: " + giaCu);
        System.out.println("Giá mới: " + giaMoi);
        System.out.println("Trạng : " + trangThai);
    }

    public void phatNhac(){
        System.out.println("Đang phát nhạc Sơn Tùng MTP ");
    }

    public void nhapThongTinMayTinh(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên: ");
        this.ten = sc.nextLine();
        System.out.println("Nhập giá cũ: ");
        this.giaCu = sc.nextDouble();
        System.out.println("Nhập giá mới: ");
        this.giaMoi = sc.nextDouble();
        sc.nextLine();
        System.out.println("Nhập trạng thái: ");
        this.trangThai = sc.nextLine();
    }

    /**
     Constructor: là method đặc biệt được sử dụng để khởi tao các đối tượng
        - có 2 loại constructor
            1. Không có tham số
                mặc định sẽ luôn được jvm tự động tạo ra dù không khai báo
            2. Có tham số
                Chỉ tồn tại khi được khai báo, nếu khai báo constructor có tham số thì bắt buộc phải khai báo thêm constructor không tham số, nếu không trình
                biên dịch sẽ báo lỗi khi sử dụng constructor không tham số, vi không hiểu trong quá trình compile time nên sử dụng loại nào
     */
    //1. constructor không tham số
    public MayTinh(){
        System.out.println("Khởi tạo đối tượng máy tính ");
    }
    //2. constructor có tham số
    public MayTinh(String ten,Double giaCu){
        this.ten = ten;
        this.giaCu = giaCu;
    }

    // khối lệnh
    {
        System.out.println(" Khối lệnh trong class Máy Tính ");
    }
    /**
     Từ khóa this:
        Được sử dụng để đại diện cho đối tượng bên trong class, giúp trình
        biên dịch hiểu đang cần sử dụng các thuộc tính, method của class
     */
}
