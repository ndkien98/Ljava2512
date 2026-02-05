package buoi12.demo.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class ParallelStreamDemo {
    public static void main(String[] args) {
        // Part 1: Performance Comparison
        long n = 10_000_000;
        System.out.println("Testing sum of " + n + " numbers...");

        // Sequential Check
        long start = System.currentTimeMillis();
        long sumSeq = LongStream.rangeClosed(1, n).sum();
        long end = System.currentTimeMillis();
        System.out.println("Sequential Sum: " + sumSeq + " | Time: " + (end - start) + "ms");

        // Parallel Check
        start = System.currentTimeMillis();
        long sumPara = LongStream.rangeClosed(1, n).parallel().sum();
        end = System.currentTimeMillis();
        System.out.println("Parallel Sum:   " + sumPara + " | Time: " + (end - start) + "ms");

        System.out.println("-------------------------------------------------");

        // Part 2: The Danger of Shared State (Race Condition)
        System.out.println("Testing Race Condition in Parallel Stream...");
        List<Integer> synchronizedList = new ArrayList<>();
        List<Integer> raceConditionList = new ArrayList<>();

        // Parallel stream adding to a non-thread-safe list (BAD PRACTICE)
        LongStream.rangeClosed(1, 1000).parallel().forEach(i -> {
            // ArrayList is NOT thread-safe
            try {
                raceConditionList.add((int) i);
            } catch (Exception e) {
                // Ignore concurrent modification exceptions just to show size mismatch
            }
        });

        System.out.println("Expected size: 1000");
        System.out.println("Actual size (Race Condition): " + raceConditionList.size());
        System.out.println(
                "NOTE: The size is often < 1000 because multiple threads overwrote the same index or lost updates.");
    }
}
