package buoi12.demo.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class NioFileDemo {
    public static void main(String[] args) {
        // NIO uses 'Path' objects instead of 'File'
        Path path = Paths.get("nio_test.txt");

        // 1. Write content to file
        String content = "Hello NIO. This is modern Java File I/O.";
        List<String> lines = Arrays.asList(
                "Line 1: NIO is cool.",
                "Line 2: Reads all lines easily.",
                "Line 3: Handles paths better.");

        try {
            // Write string
            // Files.writeString(path, content); // Java 11+

            // Write lines (Java 7/8+)
            System.out.println("Writing to " + path.toAbsolutePath());
            Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println("File exists? " + Files.exists(path));

            // 2. Read all lines
            System.out.println("--- Reading File ---");
            List<String> readLines = Files.readAllLines(path);
            readLines.forEach(System.out::println);

            // 3. Other utility methods
            System.out.println("File size: " + Files.size(path) + " bytes");

            // Delete if needed
            // Files.delete(path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
