package buoi7.baitap;

public class Tester extends NhanVien{

    public int soLoiPhatHien;

    public Tester() {
        super();
        this.soLoiPhatHien = 0;
    }

    public Tester(int soLoiPhatHien) {
        this.soLoiPhatHien = soLoiPhatHien;
    }

    public Tester(String maNV, String hoTen, double lươngCoBan, int soLoiPhatHien) {
        super(maNV, hoTen, lươngCoBan);
        this.soLoiPhatHien = soLoiPhatHien;
    }

    @Override
    public double tinhLuong() {
        return getLươngCoBan() + (soLoiPhatHien * 50000);
    }

    @Override
    public void nhapThongTin() {
        super.nhapThongTin();
        System.out.print("Nhập số lỗi phát hiện: ");
        this.soLoiPhatHien = Utils.scanner.nextInt();
    }

    @Override
    public String toString() {
        return "Tester{" + super.toString() +
                "soLoiPhatHien=" + soLoiPhatHien +
                '}';
    }

    public int getSoLoiPhatHien() {
        return soLoiPhatHien;
    }

    public void setSoLoiPhatHien(int soLoiPhatHien) {
        this.soLoiPhatHien = soLoiPhatHien;
    }
}
