package buoi12.demo.io2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

public class NIO2Example {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("data.txt");

        // 1. Ghi file cực gọn
        List<String> lines = Arrays.asList("Java NIO", "Xử lý file nhanh hơn", "Cú pháp gọn hơn");
        Files.write(path, lines);

        // 2. Kiểm tra sự tồn tại
        if (Files.exists(path)) {
            // 3. Đọc toàn bộ file vào bộ nhớ
            List<String> readLines = Files.readAllLines(path);
            readLines.forEach(System.out::println);
        }

        // 4. Copy/Move file
        Files.copy(path, Paths.get("data_backup.txt"), StandardCopyOption.REPLACE_EXISTING);
    }
}
