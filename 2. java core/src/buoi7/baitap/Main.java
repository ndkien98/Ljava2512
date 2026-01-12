package buoi7.baitap;

public class Main {

    public static void main(String[] args) {
        QuanLyNhanVien quanLyNhanVien = new QuanLyNhanVien();

        int choice = 0;
        do {
            System.out.println("Quản lý nhân viên công ty:");
            System.out.println("-------------------------");
            System.out.println("Danh sách chức năng:");
            System.out.println("1. Nhập danh sách nhân viên ");
            System.out.println("2. Xuất danh sách nhân viên đã nhập ra màn hình");
            System.out.println("3. Cho phép thêm mới nhân viên có thể là Dev hoặc Tester");
            System.out.println("4. Tính tổng số tiền lương công ty phải trả cho toàn bộ nhân viên");
            System.out.println("5. Tìm kiếm: Tìm nhân viên theo maNV");
            System.out.println("6. Lọc theo loại nhân viên: In ra danh sách nhân viên là Dev hoặc Tester");
            System.out.println("7. Thoat chương trình");
            choice = Utils.scanNumber();
            switch (choice) {
                case 1:
                    quanLyNhanVien.nhapDanhSachNhanVien();
                    break;
                case 2:
                    quanLyNhanVien.xuatDanhSachNhanVien();
                    break;
                case 3:
                    quanLyNhanVien.themMoiNhanVien();
                    break;
                case 4:
                    quanLyNhanVien.tinhTongLuongNhanVien();
                    break;
                case 5:
                    quanLyNhanVien.timKiemTheoMa();
                    break;
                case 6:
                    quanLyNhanVien.locTheoLuongVaLoaiNhanVien();
                    break;
                case 7:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }while (choice != 7);
    }
}
