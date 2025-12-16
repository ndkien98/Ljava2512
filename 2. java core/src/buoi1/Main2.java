package buoi1;

/**
 Vùng nhớ Stack:
    + Lưu trữ các lời gọi hàm, các biến local được sinh ra trong các method, các biến tham chiếu đến
    các đối tượng nam trong vùng nhớ heap
    + Hoạt động theo co chế vao trước ra sau (last in first out)
 Vùng nhớ heap:
    + Lưu trữ các đối tượng được khởi tạo bằng từ khoá new và constructor trong Java

 */
public class Main2 {

    /**
     Các thuộc tính của class ngoài laf các thuộc tính sẽ có thể coi là các biến toàn cục, có thể sử dụng ở trong
     tất cả các method nam trong thuộc tính
     */
    String tenClass;

    public static void main(String[] args) {
        Main2 main2 = new Main2();
        main2.tenClass = "Class Main2";
        int tong = main2.congHaiSo(1,2);
        System.out.println(tong);
    }

    public int congHaiSo(int soA,int soB){
        /**
         Biến được khai báo trong 1 method được gọi là biến local, chỉ có phạm vi sử dụng ở trong method
         */
        int tong = soA + soB;
        return tong;
    }
}
