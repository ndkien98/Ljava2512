package buoi6;

import java.util.Scanner;

public class Utils {

    // không được khai báo biến này ở đây, phải tạo ra class untils chuyên xử lý, class chỉ chứa cac phương thức thuộc tính của class đó
    public static Scanner sc = new Scanner(System.in);

    public static String formatName(String name) {
        name = name.trim();
        name = name.replaceAll("\\s+", " ");
        String[] words = name.split(" ");
        StringBuilder formattedName = new StringBuilder();
        for (String word : words) {
            formattedName.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1).toLowerCase())
                    .append(" ");
        }
        return formattedName.toString().trim();
    }

}
