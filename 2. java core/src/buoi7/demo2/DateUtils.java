package buoi7.demo2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
    * DateUtils
        có 2 methods:
            method 1: chuyển đổi từ String sang Date, mặc định định dạng "dd/MM/yyyy"
            method 2: chuyển đổi từ String sang Date, nhưng định dạng do người dùng truyền vào
    => đang thực đa hình bằng 2 phương thức nạp chồng (method overloading) là stringToDate
 */
public class DateUtils {

    public Date stringToDate(String dateString) {
        Date date = null;
        // code chuyển đổi từ String sang Date, mặc định định dạng "dd/MM/yyyy"
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = dateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public Date stringToDate(String dateString, String format) {
        Date date = null;
        // code chuyển đổi từ String sang Date, định dạng do người dùng truyền vào
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            date = dateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
