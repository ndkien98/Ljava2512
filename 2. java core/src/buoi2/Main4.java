package buoi2;

/**
 Cấu trúc if else

    cấu trúc if else được sử dụng để thực hiện các khối lệnh khác nhau dựa trên điều kiện đúng hoặc sai.
    Cú pháp:
        if( điều kiện ){
            // khối lệnh thực hiện nếu điều kiện đúng
        } else {
            // khối lệnh thực hiện nếu điều kiện sai
        }
        vd: 1

 Cấu trúc if else liền nhau
    Cú pháp:
        if( điều kiện 1 ){
            // khối lệnh thực hiện nếu điều kiện 1 đúng
        } else if( điều kiện 2 ){
            // khối lệnh thực hiện nếu điều kiện 2 đúng
        } else {
            // khối lệnh thực hiện nếu tất cả các điều kiện trên đều sai
        }
        vd: 2
        Lưu ý:
            - Tuy có nhiều khối else if nhưng sẽ kiểm tra tuần tự từ trên xuống dưới,
            khi gặp điều kiện đúng sẽ thực hiện khối lệnh tương ứng và thoát khỏi cấu trúc if else liền nhau
            khác với dạng nhiều if độc lập, sẽ kiểm tra tất cả các điều kiện
            Cú pháp nhiều if độc lập:
                if( điều kiện 1 ){
                    // khối lệnh thực hiện nếu điều kiện 1 đúng
                }
                if( điều kiện 2 ){
                    // khối lệnh thực hiện nếu điều kiện 2 đúng
                }
            => với khối lệnh độc lập này, tất cả cc khối if else đều được kiểm tra và thực hiện nếu điều kiện đúng
            vd 3


 */
public class Main4 {

    public static void main(String[] args) {
        System.out.println("vd 1");
        int soA = 10;
        int soB = 20;

        if( soA > soB ){
            System.out.println("Số A lớn hơn số B");
        } else {
            System.out.println("Số A không lớn hơn số B");
        }

        System.out.println("vd 2");
        int diem = 85;
        if( diem >= 90 ){
            System.out.println("Xếp loại A");
        } else if( diem >= 80 ){
            System.out.println("Xếp loại B");
        } else if( diem >= 70 ){
            System.out.println("Xếp loại C");
        } else {
            System.out.println("Xếp loại D");
        }
        System.out.println("vd 3");
        int number = 15;
        if( number % 3 == 0 ){
            System.out.println(number + " là bội số của 3");
        }
        if( number % 5 == 0 ){
            System.out.println(number + " là bội số của 5");
        }
    }

    /*
    validate số nhập vào phải là số chẵn và nằm trong khoảng từ 10 đến 50
     */
    public boolean validateSoChan(int so){
        if( so % 2 == 0 ){
            if( so >= 10 && so <= 50 ){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    /*
    v2:
     */
    public boolean validateSoChan2(int so){
        if (so % 2 != 0 ){
            return false;
        }
        if (!(so >= 10 && so <= 50)){
            return false;
        }
        return true;
    }
}
