package buoi14.dao;

import buoi14.entity.StudentEntity;
import buoi14.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentDao {

    public void insertStudent(StudentEntity student) {
        Connection connection = ConnectionUtils.getConnection();
        if (connection == null){
            System.out.println("Kết nối thất bại!");
            return;
        }
        try {
            String sql = "insert into sinh_vien(ho_ten, ngay_sinh, email, hoc_phi, ma_lop) values (?, ?, ?, ?, ?)";
            connection.setAutoCommit(false); // khoogn để tự commit dữ liệu sau mỗi câu lệnh, mà sẽ commit thủ công sau khi thực hiện xong tất cả các câu lệnh
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getHoTen());
            preparedStatement.setDate(2, student.getNgaySinh());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setFloat(4, student.getHocPhi());
            preparedStatement.setInt(5, student.getMaLop());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Thêm sinh viên thành công!");
            } else {
                System.out.println("Thêm sinh viên thất bại!");
            }
            connection.commit(); // commit dữ liệu vào cơ sở dữ liệu sau khi đã thực hiện xong tất cả các câu lệnh
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionUtils.closeConnection(connection);
        }
    }

    public List<StudentEntity> getAllStudents() {
        Connection connection = ConnectionUtils.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from sinh_vien");
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            List<StudentEntity> students = new java.util.ArrayList<>();
            while (resultSet.next()){
                StudentEntity studentEntity = new StudentEntity();
                studentEntity.setMaSv(resultSet.getInt("ma_sv"));
                studentEntity.setHoTen(resultSet.getString("ho_ten"));
                studentEntity.setNgaySinh(resultSet.getDate("ngay_sinh"));
                studentEntity.setEmail(resultSet.getString("email"));
                studentEntity.setHocPhi(resultSet.getFloat("hoc_phi"));
                studentEntity.setMaLop(resultSet.getInt("ma_lop"));
                students.add(studentEntity);
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionUtils.closeConnection(connection);
        }
    }

}
