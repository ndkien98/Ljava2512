package buoi7.demo2;

import java.util.Date;

/**

 */
public class Main {
    public static void main(String[] args) {

        DateUtils dateUtils = new DateUtils();
        String dateStr = "01/12/2023";
        String dateStr2 = "2024/06/15";
        Date date1 = dateUtils.stringToDate(dateStr);
        Date date2 = dateUtils.stringToDate(dateStr2,"yyyy/MM/dd");
        System.out.println("Chuyển chuỗi " + dateStr + " thành Date: " + date1);
        System.out.println("Chuyển chuỗi " + dateStr + " thành Date với định dạng khác: " + date2);

    }

}
