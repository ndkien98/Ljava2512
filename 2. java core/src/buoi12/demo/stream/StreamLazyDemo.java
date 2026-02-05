// Ví dụ minh họa sức mạnh của Stream với các thao tác lazy, loop fusion, short-circuiting và infinite stream.

package buoi12.demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamLazyDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Tung", "Hoa", "Binh", "An", "Tam", "Thao", "Huy");

        // 1. Lazy evaluation & Loop fusion: chỉ duyệt 1 lần cho nhiều thao tác filter/map
        System.out.println("Demo lazy & loop fusion:");
        List<String> result = names.stream()
                .filter(name -> {
                    System.out.println("filter: " + name);
                    return name.length() > 3;
                })
                .map(name -> {
                    System.out.println("map: " + name);
                    return name.toUpperCase();
                })
                .toList();
        System.out.println("Kết quả: " + result);

        // 2. Short-circuiting: findFirst sẽ dừng ngay khi tìm thấy phần tử phù hợp
        System.out.println("\nDemo short-circuiting với findFirst:");
        Optional<String> found = names.stream()
                .filter(name -> {
                    System.out.println("filter: " + name);
                    return name.startsWith("T");
                })
                .findFirst();
        System.out.println("Phần tử đầu tiên bắt đầu bằng 'T': " + found.orElse("Không tìm thấy"));

        // 3. Infinite stream: tạo luồng vô hạn và lấy 5 số chẵn đầu tiên lớn hơn 100
        System.out.println("\nDemo infinite stream:");
        Stream<Integer> infinite = Stream.iterate(101, n -> n + 1);
        infinite.filter(n -> n % 2 == 0)
                .limit(5)
                .forEach(n -> System.out.println("Số chẵn >100: " + n));
    }
}

