package buoi14.service;

import buoi14.dao.ClassDao;
import buoi14.entity.ClassEntity;

import java.util.List;

public class ClassService {
    public void showAllClasses() {
        ClassDao classDao = new ClassDao();
        List<ClassEntity> classes = classDao.getAllClasses();
        for (ClassEntity classEntity : classes) {
            System.out.println("Mã lớp: " + classEntity.getMaLop() + ", Tên lớp: " + classEntity.getTenLop());
        }
    }

    public void showAllWithConnectionPool() {
        ClassDao classDao = new ClassDao();
        List<ClassEntity> classes = classDao.getAllClassWithConnectionPool();
        for (ClassEntity classEntity : classes) {
            System.out.println("Mã lớp: " + classEntity.getMaLop() + ", Tên lớp: " + classEntity.getTenLop());
        }
    }

    public void getAllClassNotCloseConnection() {
        ClassDao classDao = new ClassDao();
        List<ClassEntity> classes = classDao.getAllClassesNotCloseConnection();
        for (ClassEntity classEntity : classes) {
            System.out.println("Mã lớp: " + classEntity.getMaLop() + ", Tên lớp: " + classEntity.getTenLop());
        }
    }
}
