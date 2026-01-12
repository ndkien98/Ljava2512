package buoi7.baitap;


import java.util.Scanner;

public class QuanLyNhanVien {

    private int soLuongNhanVien;
    private NhanVien[] danhSachNhanVien;

    public QuanLyNhanVien() {
        this.soLuongNhanVien = 0;
        this.danhSachNhanVien = new NhanVien[100];
        khoiTaoDanhSachNhanVienBanDau();
    }

    public void khoiTaoDanhSachNhanVienBanDau(){
        NhanVien nv1 = new Developer("DV001", "Nguyen Van A", 5000, 2);
        NhanVien nv2 = new Tester("TS001", "Le Thi B", 4000, 3);
        NhanVien nv3 = new Developer("DV002", "Tran Van C", 6000, 1);
        NhanVien nv4 = new Tester("TS002", "Pham Thi D", 4500, 4);
        danhSachNhanVien[soLuongNhanVien++] = nv1;
        danhSachNhanVien[soLuongNhanVien++] = nv2;
        danhSachNhanVien[soLuongNhanVien++] = nv3;
        danhSachNhanVien[soLuongNhanVien++] = nv4;

    }

    public void nhapDanhSachNhanVien() {
        System.out.println("Nhập số lượng nhân viên: ");
        int n = Utils.scanNumber();
        if (n > danhSachNhanVien.length){
            System.out.println("Số lượng nhân viên vượt quá sức chứa của danh sách.");
            return;
        }
        System.out.println("Nhập thông tin cho " + n + " nhân viên:");
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin cho nhân viên thứ " + (i + 1) + ":");
            NhanVien nv = null;
            System.out.println("Chức danh (1. Developer, 2. Tester): ");
            int choice = Utils.scanNumber();
            if (choice == 1) {
                nv = new Developer();
            } else if (choice == 2) {
                nv = new Tester();
            } else {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại.");
                i--;
                continue;
            }
            nv.nhapThongTin();
            danhSachNhanVien[soLuongNhanVien++] = nv;
        }
    }

    public void xuatDanhSachNhanVien() {
        System.out.println("Danh sách nhân viên:");
        for (int i = 0; i < soLuongNhanVien; i++) {
            System.out.println(danhSachNhanVien[i]);
        }
    }

    public void timKiemTheoMa(){
        System.out.println("Nhập mã nhân viên cần tìm: ");
        String maCanTim = Utils.scanner.nextLine();
        boolean found = false;
        for (int i = 0; i < soLuongNhanVien; i++) {
            if (danhSachNhanVien[i].getMaNV().equals(maCanTim)) {
                System.out.println("Thông tin nhân viên tìm thấy:");
                System.out.println(danhSachNhanVien[i]);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy nhân viên với mã: " + maCanTim);
        }
    }

    public void locTheoLuongVaLoaiNhanVien(){
        System.out.println("Nhập mức lương để lọc: ");
        double luongCanLoc = Utils.scanner.nextDouble();
        Utils.scanner.nextLine(); // consume newline
        System.out.println("Nhập loại nhân viên để lọc (1.Developer/2.Tester): ");
        int loaiNhanVien = Utils.scanner.nextInt();
        Utils.scanner.nextLine(); // consume newline
        System.out.println("Danh sách nhân viên lọc được:");
        for (int i = 0; i < soLuongNhanVien; i++) {
            boolean loaiDung = (loaiNhanVien == 1 && danhSachNhanVien[i] instanceof Developer) ||
                              (loaiNhanVien == 2 && danhSachNhanVien[i] instanceof Tester);
            if (danhSachNhanVien[i].tinhLuong() > luongCanLoc && loaiDung) {
                System.out.println(danhSachNhanVien[i]);
            }
        }

    }

    public void tinhTongLuongNhanVien() {
        double tongLuong = 0;
        for (int i = 0; i < soLuongNhanVien; i++) {
            tongLuong += danhSachNhanVien[i].tinhLuong();
        }
        System.out.println("Tổng lương của tất cả nhân viên: " + tongLuong);
    }

    public void themMoiNhanVien(){
        if (soLuongNhanVien >= danhSachNhanVien.length) {
            System.out.println("Danh sách nhân viên đã đầy, không thể thêm mới.");
            return;
        }
        NhanVien nv = getNhanVien();
        if (nv == null) return;
        nv.nhapThongTin();
        danhSachNhanVien[soLuongNhanVien++] = nv;
        System.out.println("Đã thêm nhân viên mới thành công.");
    }

    private static NhanVien getNhanVien() {
        System.out.println("Chọn chức danh nhân viên mới (1. Developer, 2. Tester): ");
        int choice = Utils.scanNumber();
        NhanVien nv = null;
        if (choice == 1) {
            nv = new Developer();
        } else if (choice == 2) {
            nv = new Tester();
        } else {
            System.out.println("Lựa chọn không hợp lệ.");
            return null;
        }
        return nv;
    }
}
