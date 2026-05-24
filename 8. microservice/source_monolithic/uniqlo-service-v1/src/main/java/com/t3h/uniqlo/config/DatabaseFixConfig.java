package com.t3h.uniqlo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Lop cau hinh tam thoi de sua loi thieu AUTO_INCREMENT cho bang products.
 * Sau khi chay thanh cong mot lan, co the xoa file nay.
 */
@Configuration
public class DatabaseFixConfig {

    @Bean
    public CommandLineRunner fixDatabase(JdbcTemplate jdbcTemplate) {
        return args -> {
            try {
                // Kiem tra va sua loi thieu AUTO_INCREMENT cho cot id trong bang products
                jdbcTemplate.execute("ALTER TABLE products MODIFY COLUMN id INT AUTO_INCREMENT;");
                System.out.println("DA SUA LOI DATABASE: Da them AUTO_INCREMENT cho bang products");
            } catch (Exception e) {
                // Neu da co hoac khong the sua, bo qua
                System.out.println("THONG BAO DATABASE: Bang products co the da duoc cau hinh dung hoac co loi: " + e.getMessage());
            }
        };
    }
}
