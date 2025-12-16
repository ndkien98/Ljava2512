package buoi1;

public class HelloWorld {

    public static void main(String[] args) {

        // khởi tạo đối từ class
        MayTinh mayTinh1 = new MayTinh();
        // truy cập vào thuộc tính của đối tượng và gasn giá trị cho thuộc tính
        mayTinh1.ten = "Laptop Acer Nitro Lite NL16-71G-71UJ (NH.D59SV.002) (i7 13620H/16GB/512GB SSD/RTX3050 6G/16.0FHD 165Hz/Win11/Trắng)";
        mayTinh1.giaCu = 23999000d;
        mayTinh1.giaMoi = 22999000d;
        mayTinh1.trangThai = "Sẵn hàng";
        // Truy vập vào method thực gọi các method của đối tượng
        mayTinh1.hienThiThongTin();
        mayTinh1.phatNhac();

        System.out.println("--------------------------------------------");

        MayTinh mayTinh2 = new MayTinh();
        mayTinh2.ten = "Laptop Acer Nitro Lite NL16-71G-56WQ (NH.D59SV.001) (i5 13420H/16GB/512GB SSD/RTX3050 6G/15.6FHD 165Hz/Win11/Trắng)";
        mayTinh2.giaCu = 21999000d;
        mayTinh2.giaMoi = 20999000d;
        mayTinh2.trangThai = "Sẵn sàng";
        mayTinh2.hienThiThongTin();
        mayTinh2.phatNhac();

        System.out.println("---------------------------------------------");
        System.out.println("Nhập thông tin máy tính mới ");
        MayTinh mayTinh3 = new MayTinh();
        mayTinh3.nhapThongTinMayTinh();
        mayTinh3.hienThiThongTin();
    }

}
