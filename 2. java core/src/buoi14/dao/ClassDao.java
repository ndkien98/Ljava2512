package buoi14.dao;

import buoi14.entity.ClassEntity;
import buoi14.utils.ConnectionUtils;
import buoi14.utils.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClassDao {

    public List<ClassEntity> getAllClasses() {
        Connection connection = ConnectionUtils.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from lop_hoc");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ClassEntity> classes = new java.util.ArrayList<>();
            while (resultSet.next()){
                ClassEntity classEntity = new ClassEntity();
                classEntity.setMaLop(resultSet.getInt("ma_lop"));
                classEntity.setTenLop(resultSet.getString("ten_lop"));
                classes.add(classEntity);
            }
            return classes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionUtils.closeConnection(connection);
        }
    }

    public List<ClassEntity> getAllClassWithConnectionPool() {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from lop_hoc");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ClassEntity> classes = new java.util.ArrayList<>();
            while (resultSet.next()){
                ClassEntity classEntity = new ClassEntity();
                classEntity.setMaLop(resultSet.getInt("ma_lop"));
                classEntity.setTenLop(resultSet.getString("ten_lop"));
                classes.add(classEntity);
            }
            return classes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionUtils.closeConnection(connection); // khi đóng connection này nó sẽ trả connection về pool để tái sử dụng
        }
    }


    public List<ClassEntity> getAllClassesNotCloseConnection() {
        Connection connection = null;
        try {
            connection = DBConnectionPool.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from lop_hoc");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ClassEntity> classes = new java.util.ArrayList<>();
            while (resultSet.next()){
                ClassEntity classEntity = new ClassEntity();
                classEntity.setMaLop(resultSet.getInt("ma_lop"));
                classEntity.setTenLop(resultSet.getString("ten_lop"));
                classes.add(classEntity);
            }
            return classes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
