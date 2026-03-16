package com.t3h.uniqlo.dao;

import com.t3h.uniqlo.config.DatabaseConnection;
import com.t3h.uniqlo.mapper.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @param <T> là kiểu dữ liệu mục tiêu mà class sẽ quản lý, ví dụ: User, Product, Order, v.v.
 */
public abstract class BaseDao<T> {

    /**
     * @param sql:       câu lệnh SQL để thực thi, có thể chứa các tham số được đại diện bởi dấu hỏi (?)
     * @param rowMapper: một đối tượng RowMapper<T> để ánh xạ từng dòng của ResultSet thành đối tượng T
     * @param params:    một mảng các tham số để thay thế cho các dấu hỏi trong câu lệnh SQL, có thể là các giá trị như int, String, v.v.
     * @return var: từ java 11, java cho phép sử dụng từ khóa var để khai báo biến maf không cần chỉ định kiểu
     */
    protected List<T> query(String sql, RowMapper<T> rowMapper, Object... params) {
        List<T> result = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             var ps = connection.prepareStatement(sql)) {
            setParams(params, ps);
            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                result.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected int update(String sql, Object... params) {
        int affectedRows = 0;
        try (Connection connection = DatabaseConnection.getConnection();
             var ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setParams(params, ps);
            affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (var generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    protected int insert(String sql, Object... params) {
        return update(sql, params);
    }

    protected void delete(String sql, Object... params) {
        update(sql, params);
    }

    private void setParams(Object[] params, PreparedStatement ps) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
    }
}
