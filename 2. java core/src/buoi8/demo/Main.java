package buoi8.demo;

public class Main {

    public static void main(String[] args) {

//        NhanVien nv = new NhanVien("NV01", "Nguyen Van A", 5000000);
        // khởi tạo đối tượng class abstract NhanVien thông qua class con Developer
        NhanVien nv = new Developer("NV02", "Tran Thi B", 6000000d);
        nv.nhapThongTin();
        nv.hienThiThongTin();
        // khởi tạo đối tượng thông qua anonymous class, không cần tạo class con, bắt buộc phải override tất cả các phương thức trừu tượng
        NhanVien nv2 = new NhanVien() {
            @Override
            public void nhapThongTin() {
                System.out.println(" Nhập thông tin nhân viên lớp ẩn danh...");
            }

            @Override
            public void hienThiThongTin() {
                System.out.println(" Hiển thị thông tin nhân viên lớp ẩn danh...");
            }
        };
        nv2.nhapThongTin();
        nv2.hienThiThongTin();
        for(int i = 0; i < 4; i++){
            NhanVien nvn = new NhanVien() {
                @Override
                public void nhapThongTin() {
                    System.out.println(" Nhập thông tin nhân viên lớp ẩn danh...");
                }

                @Override
                public void hienThiThongTin() {
                    System.out.println(" Hiển thị thông tin nhân viên lớp ẩn danh...");
                }
            };
            nvn.nhapThongTin();
            nvn.hienThiThongTin();
        }

    }
}
