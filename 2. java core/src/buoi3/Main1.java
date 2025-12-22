package buoi3;

/**
 Switch case trong Java
    cú pháp:
        switch (biểu thức) {
            case giá_trị_1:
                // khối lệnh thực thi nếu biểu thức == giá_trị_1
                break;
            case giá_trị_2:
                // khối lệnh thực thi nếu biểu thức == giá_trị_2
                break;
            ...
            default:
                // khối lệnh thực thi nếu biểu thức không khớp với bất kỳ giá trị nào ở trên
        }
    nếu biểu thức khớp với giá trị của một case nào đó, các lệnhtrong case đó sẽ được thực thi cho đến khi gặp từ khóa break.
    default là tùy chọn và sẽ được thực thi nếu không có case nào khớp với biểu thức.
    Lưu ý:
        - Biểu thức trong switch phải trả về một giá trị kiểu nguyên thủy
 */
public class Main1 {

    public static void main(String[] args) {
       int diem = 9;
         String xếpLoại;
         switch (diem) {
             case 9:
             case 10:
                 xếpLoại = "Xuất sắc";
                 break;
             case 8:
                 xếpLoại = "Giỏi";
                 break;
             case 7:
                 xếpLoại = "Khá";
                 break;
             case 6:
                 xếpLoại = "Trung bình khá";
                 break;
             case 5:
                 xếpLoại = "Trung bình";
                 break;
             default:
                 xếpLoại = "Yếu";
                 break;
         }

        System.out.println("Xếp loại: " + xếpLoại);

    }
}
