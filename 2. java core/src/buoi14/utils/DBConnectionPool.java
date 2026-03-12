package buoi14.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DBConnectionPool {

    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/quanlysinhvien?connectTimeout=2000");
        config.setUsername("root");
        config.setPassword("root");
        config.setMaximumPoolSize(20);   // tối đa 20 connection trong pool, để các request sử dụng lần lượt
        config.setMinimumIdle(2);        // tối thiểu 2 connection idle, tức là nếu không có request nào sử dụng thì luôn có ít nhất 2 connection sẵn sàng để phục vụ request mới
        config.setIdleTimeout(2000);    // sau 2 giấy nếu có connection không sử dụng trong pool thì sẽ bị đóng để giải phóng tài nguyên
        config.setConnectionTimeout(100000); // nếu sau 2s không có connection được các request khác trả connection về pool để tái sử dụng thì sẽ bị timeout tránh nghẽn thread
        dataSource = new HikariDataSource(config);
    }
    public static DataSource getDataSource() {
        return dataSource;
    }

}