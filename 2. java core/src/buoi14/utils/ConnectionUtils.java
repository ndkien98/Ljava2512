package buoi14.utils;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtils {

    private static String url = "jdbc:mysql://localhost:3306/quanlysinhvien";
    private static String username = "root";
    private static String password = "root";
    /**
     Để kết nối đến MySQL, bạn cần có các thông tin sau:
        - URL của cơ sở dữ liệu: thường có dạng jdbc:mysql://hostname:port/db
        - Tên người dùng (username)
        - Mật khẩu (password)

     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
         connection = DriverManager.getConnection(
                    url,
                    username,
                    password
            );
            Class.forName("com.mysql.cj.jdbc.Driver");
            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
