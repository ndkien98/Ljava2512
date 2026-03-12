package buoi14;

import buoi14.service.ClassService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main3 {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(500);
        for (int i = 0; i < 200; i++) {
            executor.submit(() -> {
                System.out.println("Thread: " + Thread.currentThread().getName());
                ClassService classService = new ClassService();
                classService.getAllClassNotCloseConnection();
            });

        }
    }
}
