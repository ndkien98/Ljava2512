package buoi12.demo.thread;

// Demo Concurrency: 2 tác vụ chạy luân phiên (có thể trên 1 hoặc nhiều thread), không nhất thiết đồng thời.
public class ConcurrencyDemo {
    public static void main(String[] args) {
        Runnable task1 = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Tác vụ 1 - bước " + i);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        };
        Runnable task2 = () -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Tác vụ 2 - bước " + i);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            }
        };
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t1.start();
        t2.start();
        // Main thread chờ 2 thread con kết thúc
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {}
        System.out.println("Hoàn thành Concurrency Demo.");
    }
}

