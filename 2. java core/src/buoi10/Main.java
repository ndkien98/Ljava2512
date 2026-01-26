package buoi10;

import java.util.*;

/**

 - Tại sao lại bị lỗi StackoverflowError:
    vô hạn đệ quy vì mỗi lần tạo stack frame mới cho hàm khởi tạo mà không có điều kiện dừng


 - Collection
    + Cấu trúc triển khai collection bao gồm 3 thành phần:
        - Interface: định nghĩa các phương thức chung cho các cấu trúc dữ liệu khác nhau
                + Bắt buộc các class triển khai phải tuần thủ theo các phương thức đã được thiết kế sẵn trong interface
                + Tạo ra sự nhất quán trong quá trình sử dụng các method của collection

        - Class triển khai: cung cấp các cài đặt cụ thể cho các interface
                + Các class implement các interface để triển khai cụ thể các method được thiết kế trong interface phù hợp với
                từng cấu trúc dữ liệu mà class đó đại diện
        - Algorithms: cung cấp các thuật toán để thao tác với các cấu trúc dữ liệu
                + cung cấp các thuật toán sẵn như sắp xếp, tìm kiếm, lọc, biến đổi dữ liệu trong các collection
                + giúp người lập trình dễ dàng thao tác và xử lý dữ liệu trong các collection tập trung vào nghiệp vụ hơn là việc triển khai lại các giải thuật cơ bản
 2. Các interface chính trong collection framework:
    + Collection: là interface gốc đại diện cho một tập hợp các phần tử
        - List: đại diện cho một danh sách các phần tử có thứ tự, cho phép trùng lặp
            các class implement List:
                + ArrayList: danh sách mảng động, truy cập nhanh, chậm khi thêm/xóa ở giữa
                            + Truy cập bằng index nhanh O(1) -> rất nhanh
                            + Thêm/xóa phần tử nếu ở giữa mảng s phải đánh lại index các phần tử sau nó -> chậm O(n)
                + LinkedList: danh sách liên kết, truy cập chậm hơn, nhanh khi thêm/xóa ở giữa
                            + Truy cập bằng index chậm O(n) -> lưu trữ phân tán, phải duyệt từ đầu list đến phần tử cần quy cập. Nếu tệ nhất có thể phải n lần duyệt
                            + Thêm/xóa phần tử: nếu lý tưởng nhất sẽ chỉ cần thay đổi liên kết tại phân tử đó với các phần tử phía trước và sau nó -> nhanh hơn phải đánh lại toàn bộ index của mảng
                + Vector: giống ArrayList nhưng đồng bộ (synchronized), ít dùng hơn
                            + synchronized: tại 1 thời điểm chỉ cho phép duy nhất 1 thread truy cập vào các phần tử của Vector
        - Set: đại diện cho một tập hợp các phần tử không có thứ tự, không cho phép trùng lặp
            Cấu trúc lưu trữ gồm 2 phần:
                + bảng băm (hash table): để kiểm tra trùng lặp phần tử nhanh
                + danh sách liên kết (linked list) hoặc cây đỏ-đen (red-black tree): để lưu trữ các phần tử
            Các class implement Set:
                + HashSet: sử dụng bảng băm để lưu trữ phần tử, truy cập nhanh, không đảm bảo thứ tự
                + LinkedHashSet: giống HashSet nhưng duy trì thứ tự chèn phần tử
                + TreeSet: sử dụng cây đỏ-đen để lưu trữ phần tử, tự động sắp xếp theo thứ tự tự nhiên hoặc theo Comparator
        - Queue: đại diện cho một hàng đợi các phần tử, thường sử dụng trong các cấu trúc dữ liệu như hàng đợi FIFO (First In First Out)
            Các class implement Queue:
                + LinkedList: có thể sử dụng như một hàng đợi
                + PriorityQueue: hàng đợi ưu tiên, phần tử có độ ưu tiên cao hơn sẽ được xử lý trước
        - Map: đại diện cho một tập hợp các cặp key-value, cho phép ánh xạ từ khóa đến giá trị tương ứng
            + Với mối phần tử của Map sẽ gồm 2 thành phần:
                - key: khóa duy nhất để xác định phần tử trong Map
                - value: giá trị tương ứng với khóa
            -> truy xuất phân tử rất nhanh thông qua key O(1)
            Các class implement Map:
                + HashMap: sử dụng bảng băm để lưu trữ cặp key-value, truy cập nhanh, không đảm bảo thứ tự
                + LinkedHashMap: giống HashMap nhưng duy trì thứ tự chèn cặp key-value
                + TreeMap: sử dụng cây đỏ-đen để lưu trữ cặp key-value, tự động sắp xếp theo thứ tự tự nhiên của key hoặc theo Comparator
        Lưu ý:
            Để làm việc với collection tất cả các dữ liệu đều phải là kiểu đối tượng, không thể sử dụng kiểu nguyên thủy
           với các kiểu dữ liệu nguyên thủy (int, float, double, char, boolean...) cần sử dụng các lớp bao (Integer, Float, Double, Character, Boolean...) để thay thế
            ví dụ:
                + int -> Integer
                + float -> Float
                + double -> Double
                + char -> Character
                + boolean -> Boolean

    3. Cách duyệt các phần tử của collection
        3.1 Sử dụng vòng lặp for-each
            cú pháp:
                for (KiểuPhầnTử phầnTử : collection) {
                    // Thao tác với phầnTử
                }
        3.2 Sử dụng Iterator
            cú pháp:
                Iterator<KiểuPhầnTử> iterator = collection.iterator();
                while (iterator.hasNext()) {
                    KiểuPhầnTử phầnTử = iterator.next();
                    // Thao tác với phầnTử
                }

 */
public class Main {

    public static void main(String[] args) {

        Collection<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(20);
        integers.add(30);
        System.out.println("Collection: " + integers);
        for (Integer integer : integers) {
            System.out.println("Phần tử: " + integer);
        }

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.remove(Integer.getInteger("10"));
        System.out.println("List: " + list);

        list.remove(1);
        // gộp 2 danh sách lớn với nhau thành 1
        list.addAll(Arrays.asList(10, 20, 30));

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            System.out.println("Phần tử trong List: " + integer);
        }

        // Set
        Set<String> set = new HashSet<>();
        set.add("Java");
        set.add("Python");
        set.add("C++");
        set.add("Java"); // Thử thêm phần tử trùng lặp

        Iterator<String> iterator2 = set.iterator();
        while (iterator2.hasNext()) {
            String language = iterator2.next();
            System.out.println("Ngôn ngữ lập trình: " + language);
        }
        // Map: Map có key = String, value = Integer
        Map<String, Integer> map = new HashMap<>();
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }



    }
}
