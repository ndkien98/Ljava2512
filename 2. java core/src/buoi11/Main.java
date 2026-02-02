package buoi11;

import buoi11.ex1.CustomArrayList;
import buoi11.ex1.CustomList;
import buoi2.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 Generic class
    + Là 1 cơ chế cho phép tham số hóa kiểu dữ liệu của thuộc tính, phương thức, class trong 1 class Java
    Ví dụ:
        Interface List<E> trong Collection framework:
            + để có thể xây dựng được 1 class triển khai lưu trữ cho mọi loại kiểu dữ liệu (Integer, String, Person, Product...)
          => không thể xây dựng 1 class với cố định kiểu dữ liệu như ListOfInteger, ListOfString, ListOfPerson...
         => Phải áp dụng Generic class để tham số hóa kiểu dữ liệu
    + Cú pháp:
        public class ClassName<T> {
            // Thuộc tính
            private T attribute;

            // Phương thức
            public T getAttribute() {
                return attribute;
            }

            public void setAttribute(T attribute) {
                this.attribute = attribute;
            }
        }
    Trong đó:
        T : type parameter (Tham số kiểu dữ liệu )
        Thường được cài đặt với các tên sau:
            + E : Element (phần tử)
            + K : Key (khóa)
            + V : Value (giá trị)
            + N : Number (số)
            + T : Type (kiểu)
    Bài tập ví dụ: Tạo class CustomerList<T> sử dụng generic để lưu trữ tất cả các loại kiểu dữ liệu khác nhau, mục tiêu
         thay thế 2 method add(), get() để làm việc với mọi loại kiểu dữ liệu

 Optional class
    + Cơ chế đóng gói giá trị của 1 class hoặc 1 phần tử được trả về. Cung cấp các phương thức để kiểm tra dữ liệu, tính hợp
    lệ của dữ liệu đó trước khi sử dụng. Mục tiêu loại bỏ NullPointerException trong Java
    + Cú pháp:
        Optional<Type> optionalValue = Optional.of(value); // value không được null
        Optional<Type> optionalValue = Optional.ofNullable(value); // value có thể là null
        Optional<Type> emptyOptional = Optional.empty(); // Tạo 1 Optional rỗng
    + Các phương thức chính:
        - isPresent(): kiểm tra xem giá trị có tồn tại hay không (trả về true/false)
        - get(): lấy giá trị bên trong Optional (nếu giá trị là null sẽ lỗi NoSuchElementException)
        - orElse(T other): lấy giá trị bên trong Optional, nếu giá trị là null sẽ trả về 1 bản ghi thay thế other được truyền vào
        - orElseGet(Supplier<? extends T> other): tương tự orElse nhưng sử dụng Supplier để tạo giá trị thay thế
        - ifPresent(Consumer<? super T> action): nếu giá trị tồn tại thì thực hiện hành động action được truyền vào


 Thread, Multithreading

 */

public class Main {

    public static void main(String[] args) {

        List<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(20);
        integers.add(30);
        List<String> strings = new ArrayList<>();
        strings.add("Java");
        strings.add("Python");
        strings.add("C++");
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice"));
        people.add(new Person("Bob"));
        people.add(new Person("Charlie"));


        CustomList<Person> personList = new CustomArrayList<>();
        personList.add(new Person("Alice"));
        personList.add(new Person("Bob"));
        personList.showAll();

        Optional<Person> optionalPerson = personList.get(0);
        optionalPerson.orElse(new Person("Default Person"));
        optionalPerson.get().displayInfo();

        Optional<Person> optional = personList.get(10);
        optional.orElse(new Person("Default Person")).displayInfo();
        if (optional.isPresent()) {
            optional.get().displayInfo();
        }
    }
}
