package buoi12.demo.concurrency;

public class ConcurrencyConceptsDemo {

    public static void main(String[] args) {
        System.out.println("Main thread is running: " + Thread.currentThread().getName());

        // 1. Creating a thread using Runnable
        Runnable task1 = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Task 1 - Count: " + i + " [" + Thread.currentThread().getName() + "]");
                try {
                    Thread.sleep(500); // Simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // 2. Creating a thread extending Thread class (Anonymous inner class style)
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Task 2 - Count: " + i + " [" + Thread.currentThread().getName() + "]");
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t1 = new Thread(task1);
        t1.start(); // Start thread 1
        thread2.start(); // Start thread 2

        System.out.println("Main thread finished starting other threads.");

        // Note: You will see output from Task 1, Task 2, and Main mixed together.
        // This demonstrates CONCURRENCY (independent execution).
    }
}
