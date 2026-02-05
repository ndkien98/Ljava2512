package buoi12.demo.io;

import java.io.*;

public class FileBasicIODemo {
    public static void main(String[] args) {
        String filePath = "basic_io_test.txt";
        String binaryPath = "basic_io_test.bin";

        // 1. Character Streams (FileReader / FileWriter) - Good for Text
        System.out.println("--- Character Streams (Text) ---");
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Hello Java Class 12!\n");
            writer.write("Trying out Input/Output streams.");
            System.out.println("Successfully wrote to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader reader = new FileReader(filePath)) {
            int character;
            System.out.print("Reading content: ");
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
            System.out.println("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. Byte Streams (FileInputStream / FileOutputStream) - Good for Binary
        // (Images, etc.)
        // We will just write some raw bytes here for demonstration
        System.out.println("--- Byte Streams (Binary) ---");
        try (FileOutputStream fos = new FileOutputStream(binaryPath)) {
            String data = "Binary data test";
            fos.write(data.getBytes()); // Convert string to bytes
            System.out.println("Successfully wrote bytes to " + binaryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(binaryPath)) {
            int byteData;
            System.out.print("Reading bytes (as chars): ");
            while ((byteData = fis.read()) != -1) {
                System.out.print((char) byteData);
            }
            System.out.println("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
