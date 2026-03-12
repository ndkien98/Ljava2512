package buoi14.service;

import buoi14.dao.ClassDao;
import buoi14.entity.ClassEntity;
import buoi14.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClassService {
    public void showAllClasses() {
        ClassDao classDao = new ClassDao();
        List<ClassEntity> classes = classDao.getAllClasses();
        for (ClassEntity classEntity : classes) {
            System.out.println("Mã lớp: " + classEntity.getMaLop() + ", Tên lớp: " + classEntity.getTenLop());
        }
    }
}
