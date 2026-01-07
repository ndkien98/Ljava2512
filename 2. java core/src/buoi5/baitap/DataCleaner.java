package buoi5.baitap;

/**
 Bài 1: Module Chế biến Dữ liệu (String & Static Method)
 Mô tả: Xây dựng lớp DataCleaner để chuẩn hóa dữ liệu thô từ người dùng nhập vào. Bài này giúp bạn tái sử dụng hàm chuẩn hóa cho các bài sau.
 •	Yêu cầu: 1. Viết hàm formatName(String name): Xóa khoảng trắng thừa ở hai đầu, giữa các từ chỉ để lại một khoảng trắng, và viết hoa chữ cái đầu mỗi từ (nguyên tắc Camel Case).
 2. Viết hàm generateID(String name, int index): Lấy chữ cái đầu của các từ trong tên và nối với số thứ tự để tạo mã định danh (Ví dụ: "Nguyen Van A" -> "NVA1").
 •	Input: Một chuỗi họ tên bất kỳ và một số nguyên.
 •	Output: Chuỗi đã được làm sạch và mã ID tương ứng.

 */
public class DataCleaner {

    public static String formatName(String name){
        if (name == null || name.isEmpty()){
            return "";
        }

        // Xóa khoảng trắng thừa ở hai đầu và giữa các từ
        String[] names = name.trim().split("\\s+");
        if (names.length < 2){
            return "";
        }
        StringBuilder formattedName = new StringBuilder();
        String word = null;
        for (int i = 0; i < names.length; i++) {
            word = names[i];
            formattedName.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase()).append(" ");
        }
        return formattedName.toString().trim();
    }

    public static String generateID(String name,int index){
        name = formatName(name);
        if (name.isEmpty()){
            return "";
        }

        String[] names = name.split("\\s+");
        StringBuilder id = new StringBuilder();
        for (String word : names) {
            id.append(word.charAt(0));
        }
        id.append(index);
        return id.toString();
    }
    /**
     Nguyễn Đắc Kiên: kiennd1
     Nguyễn Đắc Kiên: kiennd2
     */

}
