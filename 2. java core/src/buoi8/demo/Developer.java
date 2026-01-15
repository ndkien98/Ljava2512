package buoi8.demo;

/**
 class Develop khi kế thừa từ class abstract NhanVien
 sẽ bắt buộc phải implement (cài đặt) lại tất cả các phương thức trừu tượng (abstract method) của class NhanVien
 nếu không sẽ bị lỗi biên dịch

 => implement:
        phải override (ghi đè) lại tất cả các phương thức trừu tượng của class cha
        thêm từ khóa @Override phía trên phương thức để thể hiện rõ ràng việc ghi đè phương thức, và
        triển khai các logic cụ thể bên trong phương thức đó phù hợp với nghiệp vụ của class con
 */
public class Developer extends NhanVien implements INhanVien{


    public Developer(String maNV, String hoTen, Double lươngCoBan) {
        super(maNV, hoTen, lươngCoBan);
    }

    @Override
    public void nhapThongTin() {
        System.out.println("Nhập thông tin Developer");
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("Hiển thị thông tin Developer");
    }

    /**
     Bắt buộc các class con phải triển khai phương thức của interface
     */
    @Override
    public void lamViec() {

    }

    /**
     triển khai đầy đủ các phương thức trừu tượng của interface cha của INhanVien
     */
    @Override
    public double tinhThuNhap() {
        return 0;
    }

    @Override
    public double tinhLuong() {
        return 0;
    }

    @Override
    public void xinNghiViec() {

    }
}
