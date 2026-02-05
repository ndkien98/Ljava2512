package buoi12.demo.io2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamExample {
    public static void main(String[] args) {
        // Copy một file ảnh
        try (FileInputStream in = new FileInputStream("image.jpg");
             FileOutputStream out = new FileOutputStream("image_copy.jpg")) {

            int c;
            // Đọc từng byte cho đến khi hết file (-1)
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            System.out.println("Copy file thành công!");
        } catch (IOException e) {
            System.err.println("Lỗi: " + e.getMessage());
        }
    }
}