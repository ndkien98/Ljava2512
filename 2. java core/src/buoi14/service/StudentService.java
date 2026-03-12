package buoi14.service;

import buoi14.dao.StudentDao;
import buoi14.entity.StudentEntity;
import buoi14.utils.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentService {


    public void addStudent(){
        ClassService classService = new ClassService();
        System.out.println("Nhập vào tên lớp hoc sinh viên muốn thêm: ");
        classService.showAllClasses();
        System.out.println("Nhập vào mã lớp: ");
        int maLop = new java.util.Scanner(System.in).nextInt();
        System.out.println("Nhập vào họ tên sinh viên: ");
        String hoTen = new java.util.Scanner(System.in).nextLine();
        System.out.println("Nhập vào ngày sinh sinh viên (yyyy-MM-dd): ");
        String ngaySinhStr = new java.util.Scanner(System.in).nextLine();
        Date ngaySinh = Date.valueOf(ngaySinhStr);
        System.out.println("Nhập vào email sinh viên: ");
        String email = new java.util.Scanner(System.in).nextLine();
        System.out.println("Nhập vào học phí sinh viên: ");
        float hocPhi = new java.util.Scanner(System.in).nextFloat();
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setHoTen(hoTen);
        studentEntity.setNgaySinh(ngaySinh);
        studentEntity.setEmail(email);
        studentEntity.setHocPhi(hocPhi);
        studentEntity.setMaLop(maLop);
        StudentDao studentDao = new StudentDao();
        studentDao.insertStudent(studentEntity);
    }
}
