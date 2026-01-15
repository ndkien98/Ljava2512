package buoi8.demo;

import buoi7.baitap.Developer;

/**
 interface:
    sử dụng từ khóa interface khai báo 1 interface
 */
public interface INhanVien extends ICongTac,INghiViec{

    public static final Integer LUONG_CO_BAN = 1500000;

    public abstract void lamViec();
}
