package buoi12.demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// Ví dụ minh họa Terminal Operation (Eager): collect, count, forEach.
// Các hàm này sẽ "kích hoạt" việc xử lý dữ liệu của Stream.

public class StreamTerminalDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Tung", "Hoa", "Binh", "An", "Tam", "Thao", "Huy");

        // collect: thu thập kết quả thành List mới
        List<String> upperNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Tên viết hoa (collect): " + upperNames);

        // count: đếm số lượng phần tử thỏa mãn điều kiện
        long count = names.stream()
                .filter(n -> n.length() > 3)
                .count();
        System.out.println("Số tên có hơn 3 ký tự (count): " + count);

        // forEach: thực hiện hành động với từng phần tử
        System.out.println("In ra từng tên (forEach):");
        names.stream()
                .forEach(System.out::println);
    }
}

