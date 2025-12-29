package buoi4;

/**

 String

 1. Định nghĩa
    - là 1 kiểu dữ liệu tham chiếu   đặc biệt trong Java, dùng để lưu trữ chuỗi ký tự
    - lớp String trong Java được định nghĩa trong gói java.lang
    - chuỗi ký tự trong Java được biểu diễn bằng các ký tự Unicode, cho phép lưu trữ và xử lý các ký tự từ nhiều ngôn ngữ khác nhau trên thế giới

 2. Cách khai báo và khởi tạo chuỗi
    - có 2 cách khai báo và khởi tạo chuỗi trong Java
      + C1: sử dụng dấu ngoặc kép "" (literal)
        Ví dụ: String str1 = "Hello, World!";

      + C2: sử dụng từ khoá new và constructor của lớp String
        Ví dụ: String str2 = new String("Hello, World!");

 4. Vùng nhớ lưu trữ

    - String được lưu trữ trong String pool (vùng nhớ đặc biệt trong heap memory)
    - Khi khởi tạo chuỗi bằng literal (khởi tạo trực tiếp bằng dấu ngoặc kép), String đó sẽ được lưu trực tiếp trong String pool.
        + Java sẽ kiểm tra xem trong String pool đã có chuỗi giống như vậy chưa, nếu đã có thì sẽ không tạo mới mà chỉ tham chiếu đến chuỗi đã tồn tại.
        + Nếu chưa có, Java sẽ tạo một đối tượng String mới trong String pool.
    - Khi khởi tạo chuỗi bằng từ khoá new, một đối tượng String mới sẽ luôn được tạo trong heap memory, không quan tâm đến việc chuỗi đó đã tồn tại trong String pool hay chưa.
    - Việc sử dụng String pool giúp tiết kiệm bộ nhớ và tăng hiệu suất khi làm việc với nhiều chuỗi giống nhau.

    => Khuyến cáo nên sử dụng cách khởi tạo chuỗi bằng literal để tận dụng lợi ích của String pool.

 Lưu về tính chất của String trong Java:
    - Immutable (bất biến): Mọi String khi đã được tạo ra sẽ không thể thay đổi được, ếu thay đổi biến trỏ đến String đó bản chất nó sẽ tạo ra một String mới trong bộ nhớ\
        chứ không thay đổi được String ban đầu.
    - Có thể sử dụng toán tử + để nối chuỗi, nhưng thực chất mỗi lần nối chuỗi sẽ tạo ra một đối tượng String mới.
    ví dụ:
        String str1 = "Hello";
        str1 = str1 + ", World!"; // Tạo ra một đối tượng String mới và gán cho str1

 5. Các so sánh chuỗi
    Làm thế nào để so sánh 2 chuỗi trong Java ?

    Trong Java để so sánh có thể su dung 2 cách:
        + Sử dụng toán tử == : so sánh địa chỉ vùng nhớ của 2 chuỗi => nếu 2 chuỗi cùng trỏ đến 1 vùng nhớ thì kết quả trả về true, ngược lại trả về false
        + Sử dụng phương thức equals(): so sánh giá trị nội dung của 2 chuỗi, nếu giống nhau về giá trị trả về true, ngược lại trả về false
 6. Các phương thức thường dùng của lớp String

 7. String builder và String buffer
    + vi String là immutable nên mỗi lần thay đổi chuỗi sẽ tạo ra một đối tượng mới, điều này gây ra lãng phí bộ nhớ và hiệu suất kém khi thực hiện nhiều thao tác thay đổi chuỗi.
    + Để khắc phục vấn đề này, Java cung cấp hai lớp StringBuilder và StringBuffer,
    cả hải lớp này đều cho phép tạo và sửa đổi chuỗi một cách hiệu quả hơn
    + Sự khác biệt chính giữa StringBuilder và StringBuffer là StringBuffer là thread-safe (an toàn cho đa luồng)

    - StringBuilder: không đồng bộ, không an toàn cho đa luồng, nhưng hiệu suất cao hơn trong môi trường đơn luồng.
    - StringBuffer: đồng bộ, an toàn cho đa luồng, nhưng hiệu suất thấp hơn so với StringBuilder do có cơ chế đồng bộ.
    - Lam thế nào để StringBuilder và StringBuffer có thể thay đổi được ?
        + Cả hai lớp này đều cung cấp các phương thức như append(), insert(), delete(), replace() để thực hiện các thao tác thay đổi chuỗi mà không tạo ra đối tượng mới.
        + Bản chất khi tạo ra 1 StringBuilder hoặc StringBuffer, chúng sẽ tạo ra một mảng ký tự bên trong có kích thước ban đầu (mặc định là 16 ký tự). Khi thực hiện các thao tác thay đổi chuỗi,
            nếu kích thước của chuỗi vượt quá thì sẽ được tạo mảng mới van chuyển dữ liệu từ mảng cũ sang mảng mới.
        => khi nào sử dụng method toString() khi đó mới thật sự chuển đổi từ kiểu mảng ký tự bên trong thành đối tượng String.
 */

public class Main {

    public static void main(String[] args) {
        String str1 = "Hello, World!";
        String str2 = "Hello, World!";
        String str3 = new String("Hello, World!");

        System.out.println(str1 == str2);
        System.out.println(str2 == str3);
        System.out.println(str2.equals(str3));
        System.out.println(str1.equals(str2));
        /**
         58: true
         59: false
         true
         true
         */

        String str4 = null;
        System.out.println("Hello".equals(str4)); // false
        System.out.println(str4.equals("Hello")); // Lỗi NullPointerException


        StringBuilder sb = new StringBuilder("Hello");
        sb.append(", World!");
        System.out.println(sb.toString()); // Hello, World!
        StringBuffer sbf = new StringBuffer("Hello");
        sbf.append(", World!");
        System.out.println(sbf.toString()); // Hello, World!

    }


}
