package buoi7.baitap;

public class Developer extends NhanVien{

    private double tineOvertime;

    public Developer(String maNhanVien, String hoTen, double luongCoBan, double tineOvertime) {
        super(maNhanVien, hoTen,luongCoBan);
        this.tineOvertime = tineOvertime;
    }

    public Developer() {
        super();
        this.tineOvertime = 0;
    }


    @Override
    public String toString() {
        return "Developer{" + super.toString() + ", tineOvertime=" + tineOvertime + '}';
    }

    @Override
    public double tinhLuong() {
        return tineOvertime + super.getLươngCoBan();
    }

    @Override
    public void nhapThongTin() {
        super.nhapThongTin();
        System.out.print("Nhập số giờ làm thêm: ");
        this.tineOvertime = Utils.scanner.nextDouble();
    }

    public double getTineOvertime() {
        return tineOvertime;
    }

    public void setTineOvertime(double tineOvertime) {
        this.tineOvertime = tineOvertime;
    }
}
