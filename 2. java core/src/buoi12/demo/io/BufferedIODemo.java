package buoi12.demo.io;

import java.io.*;

public class BufferedIODemo {
    public static void main(String[] args) {
        String filePath = "buffered_io_test.txt";

        // Writing with BufferedWriter (more efficient)
        System.out.println("Starting write...");
        long startWrite = System.currentTimeMillis();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < 10000; i++) {
                writer.write("Line " + i + ": Java Buffered I/O is fast.");
                writer.newLine(); // Adds \n or \r\n depending on OS
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long endWrite = System.currentTimeMillis();
        System.out.println("Write complete in " + (endWrite - startWrite) + "ms");

        // Reading with BufferedReader
        System.out.println("Starting read...");
        long startRead = System.currentTimeMillis();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                // Process line if needed
                count++;
            }
            System.out.println("Read " + count + " lines.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        long endRead = System.currentTimeMillis();
        System.out.println("Read complete in " + (endRead - startRead) + "ms");
    }
}
