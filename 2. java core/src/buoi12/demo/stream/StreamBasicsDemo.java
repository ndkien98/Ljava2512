package buoi12.demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamBasicsDemo {
    public static void main(String[] args) {
        // 1. Tạo nguồn dữ liệu (List)
        List<String> names = Arrays.asList("Tung", "Hoa", "Binh", "An", "Tam", "Thao", "Huy");

        System.out.println("Original List: " + names);

        // 2. Luồng xử lý Stream: Lọc -> Sắp xếp -> Biến đổi -> Thu thập kết quả

        // Yêu cầu: Tìm các tên bắt đầu bằng 'T', chuyển thành chữ hoa, sắp xếp lại.
        List<String> result = names.stream() // Nguồn dữ liệu
                .filter(name -> name.startsWith("T")) // Trung gian: Lọc
                .map(String::toUpperCase) // Trung gian: Biến đổi
                .sorted() // Trung gian: Sắp xếp
                .collect(Collectors.toList()); // Kết thúc: Thu thập kết quả

        System.out.println("Filtered & Sorted (Starts with T): " + result);

        // 3. Toán tử kết thúc: forEach
        System.out.println("In ra tất cả các tên có hơn 3 ký tự:");
        names.stream()
                .filter(n -> n.length() > 3)
                .forEach(System.out::println); // Kết thúc: Thực hiện hành động phụ (in ra)

        // 4. Toán tử kết thúc: reduce (Tổng hợp)
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = numbers.stream()
                .filter(n -> n % 2 == 0) // Chỉ lấy số chẵn
                .reduce(0, Integer::sum); // 0 là giá trị khởi tạo, sau đó cộng dồn

        System.out.println("Tổng các số chẵn: " + sum);
    }
}
