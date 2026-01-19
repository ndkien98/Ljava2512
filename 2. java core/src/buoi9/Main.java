package buoi9;

/**

 Interface khác gì abstact class?
    + interface chỉ có thể khai báo các phương thức trừu tượng (abstract method) và các hằng số (constant)
    + abstract class có thể khai báo cả các phương thức trừu tượng và các phương thức cụ thể (concrete method)
    + interface hỗ trợ đa kế thừa (1 class có thể implement nhiều interface cùng lúc)
    + abstract class chỉ hỗ trợ kế thừa đơn (1 class chỉ có thể kế thừa)
 Cách sử dụng:
 -> interface thường được sử dụng để định nghĩa các hành vi chung mà các class khác nhau có thể triển khai theo cách của riêng các class
    + vì khi interface định nghĩa các phương thức trừu tượng sẽ tạo ra 1 bộ khung chung mà tất cả các class implement interface đó phải tuân theo
    như vậy sẽ giúp đảm bảo tính nhất quán trong các class khác nhau, sẽ có cùng hành vi chùng(tên method, tham số, kiểu trả về) giống nhau
    giúp dễ mở rộng và bảo trì hệ thống, tăng cường tính đã hinh và trìu tượng. sử dụng rất nhiều trong các frameworks và thư viện lớn
        ví dụ:
            - trong Java Collections Framework, các interface như List, Set, Map định nghĩa các hành vi chung cho các cấu trúc dữ liệu khác nhau
            - trong  framework Spring, các interface như Repository, Service định nghĩa các hành vi chung cho các lớp xử lý dữ liệu và logic nghiệp vụ
 -> abstract class thường được sử dụng để định nghĩa các thuộc tính và hành vi chung mà các class con kế thừa có thể sử dụng và mở rộng thêm từ class cha

 + Làm thế nào để tạo ra lamda expression ?

 */
public class Main {
}
