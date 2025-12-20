package buoi2;

/**


 Các toàn tử
    ++:
        dùng để tự tăng 1 đơn vị của 1 biến
    --:
        dùng để tự giảm đơn vị của 1 biến
 vị trí sử dụng:
    nếu đặt trước biến:
        ++a: tăng giá trị của a lên 1 đơn vị trước khi sử dụng giá trị của a trong biểu thức
        --a: giảm giá trị của a xuống 1 đơn vị trước khi sử dụng giá trị của a trong biểu thức
    nếu đặt sau biến:
        a++: sử dụng giá trị hiện tại của a trong biểu thức, sau đó tăng giá trị của a lên 1 đơn vị
        a--: sử dụng giá trị hiện tại của a trong biểu thức, sau đó giảm giá trị của a xuống 1 đơn vị

    Toán tử so sánh
        ==: bằng
        !=: khác
        >: lớn hơn
        <: nhỏ hơn
        >=: lớn hơn hoặc bằng
        <=: nhỏ hơn hoặc bằng
        &&: so sánh logic     "và"
        ||: so sánh logic     "hoặc"
        & : toán tử bitwise   "và"
        | : toán tử bitwise   "hoặc"

        So sánh toán tử: && và toán tử &
            toán tử &&: nếu biểu thức đầu tiên là false thì không cần kiểm tra biểu thức thứ 2
            toán tử &: luôn kiểm tra cả 2 biểu thức dù biểu thức đầu tiên là false
        So sánh toán tử: || và toán tử |
            toán tử ||: nếu biểu thức đầu tiên là true thì không cần kiểm tra biểu thức thứ 2
            toán tử |: luôn kiểm tra cả 2 biểu thức dù biểu thức đầu tiên là true

 Các toàn tử
    A = A + B <=> A += B
    A = A - B <=> A -= B
    A = A * B <=> A *= B
    A = A / B <=> A /= B
    A = A % B <=> A %= B

 Toán tử 3 ngôi:


 */
public class Main3 {

    public static void main(String[] args) {


        int a = 10;
        int b = 5;

        int c = ++a  + (++b); // 17
        System.out.println(c); // 17
        System.out.println(a--); // 11
        System.out.println(++a); // 11
        System.out.println(--b); // 5
        System.out.println(a - b++); // 6
        System.out.println(b); // 6


        if(a < 10 && b++ < 5 && c > 100) {
            System.out.println("Điều kiện đúng");
        }

        if(a < 10 & b++ < 5 & c > 100) {
            System.out.println("Điều kiện đúng");
        }

        // bình thường sẽ viết thế này
        int e = 5;
        if (e > 10) {
            e = 1;
        }else {
            e = 2;
        }
        // Sử dụng biểu thức toàn tử 3 ngôi
        e = (e > 10) ? 1 : 2;
    }
}
