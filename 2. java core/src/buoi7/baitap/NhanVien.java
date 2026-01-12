package buoi7.baitap;

public class NhanVien {

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

    public void nhapThongTin() {
        System.out.println("Nhập thông tin nhân viên...");
        System.out.println("Nhập mã nhân viên: ");
        this.maNV = Utils.scanner.nextLine();
        System.out.println("Nhập họ tên nhân viên: ");
        this.hoTen = Utils.scanner.nextLine();
        System.out.println("Nhập lương cơ bản: ");
        this.lươngCoBan = Utils.scanner.nextDouble();
        Utils.scanner.nextLine();
    }

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
