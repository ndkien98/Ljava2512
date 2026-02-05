package buoi12.demo.io2;

import java.io.*;

class Student implements Serializable {
    String name;
    int age;
    public Student(String name, int age) { this.name = name; this.age = age; }
}
public class ObjectStreamExample {
    public static void main(String[] args) {
        Student s1 = new Student("Nguyen Van A", 20);

        // Ghi đối tượng xuống file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.txt"))) {
            oos.writeObject(s1);
        } catch (IOException e) { e.printStackTrace(); }

        // Đọc đối tượng từ file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.txt"))) {
            Student savedStudent = (Student) ois.readObject();
            System.out.println("Đọc lại: " + savedStudent.name + " - " + savedStudent.age);
        } catch (Exception e) { e.printStackTrace(); }
    }
}