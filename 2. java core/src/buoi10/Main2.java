package buoi10;

import java.util.*;

/**

Tại sao đã sử dụng method people.remove() nhưng vẫn chưa xóa được đối tượng có trùng thông tin trong collection?
    - Trong Array List:
        + Sử dụng phương thức equals() để so sánh và xác định đâu là 2 đối tượng trùng nhau.
        + Nếu không override phương thức equals() của đối tượng. Mặc định sẽ sử dụng địa chỉ ô nhớ để so sánh.
    - Trong Hash Set:
        + Sử dụng cả 2 phương thức hashcode() và equals() để xác định đâu là 2 đối tượng nhau.
        + Nếu chỉ override 1 trong 2 phương thức thì chưa đủ để xác định đâu là 2 đối tượng trùng nhau.
    - Cấu trúc lưu trữ và cơ chế hoạt động của HashMap trong Map Collection
        + HashMap có 1 danh sách các bucket để lưu trữ các cặp key-value.
        + Mỗi bucket sẽ có 1 danh sách Entry để lưu các các node.
        + Mỗi Node sẽ chưa 1 cặp key-value.
        + Khi thêm 1 phần tử vào HashMap. Hệ thống sẽ gọi phương thức hashcode() và dịch 16 bit để xác định index của buctket.
        => Khi xác định được index của bucket sẽ duyệt danh sách Entry trong bucket để kiểm tra có trùng với key nào hay không,
        nếu trùng key -> gọi phương thức equals() để so sánh 2 key với nhau
        nếu trùng cả key, equals() trả về true -> ghi đè value mới vào key đó
        nếu chỉ trùng key, equals() trả về false -> thêm mới 1 node vào danh sách Entry trong bucket đó
        nếu không trùng key -> thêm mới 1 node vào danh sách Entry trong bucket đó
            => nếu thêm 1 node mới vào entry vượt quá ngưỡng 8 node trong cùng 1 bucket -> Chuyển cấu trúc lưu trữ Entry từ danh sách liên kết sang TreeMap (cây đỏ-đen) để tối ưu hiệu suất tìm kiếm

 */
public class Main2 {

    public static void main(String[] args) {
        // Danh sách các person
        List<Person> people = new ArrayList<>();

        people.add(new Person(1,"Alice", 25));
        people.add(new Person(2,"Bob", 30));
        people.add(new Person(3,"Charlie", 35));
        people.remove(new Person(1,"Alice", 25));
        for (Person person : people) {
            System.out.println("Person: " + person);
        }

        // Set các person
        Set<Person> set = new HashSet<>();
        set.add(new Person(1,"Alice", 25));
        set.add(new Person(2,"Bob", 30));
        set.add(new Person(1,"Alice", 25)); // Thử thêm phần tử
        for (Person person : set) {
            System.out.println("Person trong Set: " + person);
        }

        Map<String,Person> map = new HashMap<>();
        map.put("1", new Person(1,"Alice", 25));
        map.put("2", new Person(2,"Bob", 30));
        for (String key : map.keySet()) {
            Person person = map.get(key);
            System.out.println("Key: " + key + ", Person: " + person);
        }
    }
}
