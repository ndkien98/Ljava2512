package buoi12.demo.io2;

import java.io.*;

public class CharacterStreamExample {
    public static void main(String[] args) {
        String data = "Dòng 1: Học Java I/O\nDòng 2: Rất chi tiết!";
        // Ghi file với BufferedWriter
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("note.txt"))) {
            bw.write(data);
            System.out.println("Đã ghi văn bản.");
        } catch (IOException e) { e.printStackTrace(); }
        // Đọc file với BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader("note.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Đọc từ file: " + line);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}