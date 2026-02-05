package buoi12.demo.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

// Demo Parallelism: sử dụng parallelStream để xử lý đồng thời trên nhiều core (nếu có).

public class ParallelismDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Tung", "Hoa", "Binh", "An", "Tam", "Thao", "Huy");
        System.out.println("Xử lý song song với parallelStream:");
        names.parallelStream().forEach(name -> {
            System.out.println("Thread: " + Thread.currentThread().getName() + " xử lý " + name);
            try { Thread.sleep(200); } catch (InterruptedException e) {}
        });
        System.out.println("Hoàn thành Parallelism Demo.");

        // --- Ví dụ Fork/Join Framework ---
        System.out.println("\nDemo Fork/Join Framework: Tính tổng mảng lớn song song");
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) arr[i] = i + 1;
        ForkJoinPool pool = new ForkJoinPool();
        int sum = pool.invoke(new SumTask(arr, 0, arr.length));
        System.out.println("Tổng từ 1 đến 100 (Fork/Join): " + sum);

        // --- Ví dụ Parallel Stream với số ---
        System.out.println("\nDemo parallelStream với số:");
        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        int tong = nums.parallelStream()
                .mapToInt(i -> {
                    System.out.println("Thread: " + Thread.currentThread().getName() + " xử lý " + i);
                    return i;
                })
                .sum();
        System.out.println("Tổng các số (parallelStream): " + tong);
    }
}

// Lớp tính tổng mảng sử dụng Fork/Join Framework
class SumTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 10;
    private int[] arr;
    private int start, end;

    public SumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) sum += arr[i];
            return sum;
        } else {
            int mid = (start + end) / 2;
            SumTask left = new SumTask(arr, start, mid);
            SumTask right = new SumTask(arr, mid, end);
            left.fork();
            int rightResult = right.compute();
            int leftResult = left.join();
            return leftResult + rightResult;
        }
    }
}
