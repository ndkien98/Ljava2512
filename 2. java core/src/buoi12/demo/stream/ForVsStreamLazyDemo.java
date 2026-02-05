// So sánh for loop truyền thống và stream (lazy):
// - For loop: bạn phải tự kiểm soát break, điều kiện, thao tác từng bước thủ công.
// - Stream: mô tả điều kiện, thao tác, Java sẽ tự tối ưu (ví dụ: chỉ duyệt đến khi tìm thấy phần tử thỏa mãn, không cần duyệt hết).
// - Stream hỗ trợ lazy evaluation: các thao tác filter/map chỉ thực sự thực hiện khi gặp toán tử kết thúc (terminal), giúp tối ưu hiệu suất.

package buoi12.demo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ForVsStreamLazyDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 7, 9, 12, 15, 18, 21);

        // Cách 1: For loop truyền thống
        System.out.println("For loop:");
        Integer found = null;
        for (Integer n : numbers) {
            System.out.println("Kiểm tra: " + n);
            if (n > 10 && n % 2 == 0) {
                found = n;
                break;
            }
        }
        System.out.println("Số chẵn đầu tiên >10 (for loop): " + found);

        // Cách 2: Stream (Lazy)
        System.out.println("\nStream (Lazy):");
        Optional<Integer> foundStream = numbers.stream()
                .peek(n -> System.out.println("peek: " + n))
                .filter(n -> n > 10)
                .peek(n -> System.out.println("sau filter >10: " + n))
                .filter(n -> n % 2 == 0)
                .peek(n -> System.out.println("sau filter chẵn: " + n))
                .findFirst();
        System.out.println("Số chẵn đầu tiên >10 (stream): " + foundStream.orElse(null));
    }
}
