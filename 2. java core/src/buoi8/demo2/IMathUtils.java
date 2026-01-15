package buoi8.demo2;

/*
interface IMathUtils
    có 1 method:
        - int add(int a, int b): trả về tổng của a và b
 => đây chính là funtional interface vì chỉ có 1 method trừu tượng

 - Từ java 8 trở đi, chúng ta có thể thêm các method mặc định (default method) vào trong interface
    - default int subtract(int a, int b): trả về hiệu của a và b
 */
@FunctionalInterface
public interface IMathUtils {

    int add(int a, int b);

    default int subtract(int a, int b) {
        return a - b;
    }
}
