package buoi8.demo;

import buoi7.baitap.Utils;

/*
Sử dụng từ khóa abstract để khai báo lớp NhanVien là lớp trừu tượng
-> khai báo lớp triu tượng NhanVien

- Phương thức trìu tượng(abstract method):
    + là phương thức chỉ có phần khai báo kiểu trả về, tên phương thức, danh sách tham số truyền vào (nếu có), và không có phần body triển khai logic của phương thức
    + sử dụng từ khóa abstract để khai báo phương thức trừu tượng
    + phương thức trừu tượng chỉ được khai báo trong lớp trừu tượng
- phương thức bình thường (non-abstract method):
    + là phương thức có đầy đủ phần khai báo và phần body triển khai logic của phương thức
    + phương thức bình thường có thể được khai báo trong cả lớp trừu tượng và lớp cụ thể(concrete class)

- về lý thuyết -> không thể khởi tạo trực tiếp đối tượng từ lớp trừng trượng, phải thông qua lớp kế thừa từ lớp trừu tượng mới có thể khởi tạo đối tượng
=> nhưng thực tế trong Java vẫn có thể khởi tạo đối tượng từ lớp trừu tượng thông qua cách sử dụng lớp ẩn danh (anonymous class)
    anonymous class: là một lớp không có tên, được khai báo và khởi tạo đối tượng và bắt buộc phải triển khai ghi đè tất cả các phương thức
                    của lớp trừu tượng cha (nếu có) ngay tại vị trí khai báo và khởi tạo đối tượng của lớp trừu tượng đó.
lưu ý:
    - không nên lạm dụng anonymous class để khởi tạo đối tượng từ lớp trừu tượng, vì sẽ làm cho code khó đọc, khó bảo trì
    - chỉ nên sử dụng anonymous class trong trường hợp cần nhanh chóng khởi tạo đối tượng
    - khi tạo đối tượng bằng anonymous class thì jvm sẽ phải load lại toàn bộ thông tin lớp ẩn danh đó vào bộ nhớ, và chỉ được sử dụng 1 lần duy nhất, mỗi 1 lần tạo đối tượng
        bằng lớp ẩn danh thì jvm sẽ phải load lại lớp ẩn danh đó vào bộ nhớ, điều này sẽ làm tốn tài nguyên hệ thống, vì class ẩn danh không được tái sử dụng lại và sẽ
        luôn được tạo ra 1 class mới trong bộ nhớ mỗi khi khởi tạo đối tượng.

 */
public abstract class NhanVien {

    private String maNV;
    private String hoTen;
    private Double lươngCoBan;

    public NhanVien() {
        super();
    }

    public NhanVien(String maNV, String hoTen, Double lươngCoBan) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.lươngCoBan = lươngCoBan;
    }

    /*
    ví dụ về phương thức trừu tượng
     */
    public abstract void nhapThongTin();

    public abstract void hienThiThongTin();

    @Override
    public String toString() {
        return "{" +
                "Mã NV='" + maNV + '\'' +
                ", Họ Tên='" + hoTen + '\'' +
                ", Lương Cơ Bản=" + lươngCoBan +
                '}';
    }

    public double tinhLuong() {
        System.out.println("Tính lương cho nhân viên...");
        return lươngCoBan;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Double getLươngCoBan() {
        return lươngCoBan;
    }

    public void setLươngCoBan(Double lươngCoBan) {
        this.lươngCoBan = lươngCoBan;
    }
}
