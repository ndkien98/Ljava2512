package com.t3h.uniqlo.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class DatabaseConnection {

    private static HikariDataSource dataSource;
    // đại diện cho file config application.properties, chứa các thông tin cấu hình như URL, username, password
    private static Properties properties;

    // load config database từ khi khơi tạo application, đảm bảo chỉ load 1 lần duy nhất
    private static void init(){
        properties = new Properties();
        try(InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                log.error("Unable to find application.properties");
                throw new RuntimeException("application.properties not found");
            }
            properties.load(input);
            Class.forName(properties.getProperty("database.driver-class-name"));
            log.info("Database driver class loaded");
        }catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to load database configuration: {}", e.getMessage());
            throw new RuntimeException("application.properties not found");
        }
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(properties.getProperty("database.url"));
        dataSource.setUsername(properties.getProperty("database.username"));
        dataSource.setPassword(properties.getProperty("database.password"));
//        dataSource.setMaximumPoolSize(properties.getProperty("database.max-pool-size") != null ? Integer.parseInt(properties.getProperty("database.maximum-pool-size")) : 10);
//        dataSource.setMinimumIdle(properties.getProperty("database.min-idle") != null ? Integer.parseInt(properties.getProperty("database.maximum-pool-size")) : 10);
//        dataSource.setIdleTimeout(properties.getProperty("database.idle-timeout") != null ? Integer.parseInt(properties.getProperty("database.idle-timeout")) : 10);
        log.info("Database connection established: {}",dataSource.toString());
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            synchronized (DatabaseConnection.class) {
                init();
            }
        }
        return dataSource.getConnection();
    }
}
