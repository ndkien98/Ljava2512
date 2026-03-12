package buoi14;

import buoi14.service.ClassService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main2 {

    public static void main(String[] args) {

        ClassService classService = new ClassService();
        ExecutorService executor = Executors.newFixedThreadPool(500);
        for (int i = 0; i < 10000; i++) {
            executor.execute(() -> {
                classService.showAllWithConnectionPool();
            });
        }

        executor.shutdown();


    }
}
