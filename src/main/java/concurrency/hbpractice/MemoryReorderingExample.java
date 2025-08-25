package concurrency.hbpractice;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MemoryReorderingExample {

    private int x;
    private boolean initialized = false;

    public void writer() {
        x = 5; /* W1 */
        initialized = true; /* W2 */
    }

    public void reader() {
        boolean r1 = initialized; /* R1 */
        if (r1) {
            int r2 = x; /* R2, may read default value (0) */
        }
    }

    public static void main(String[] args) throws InterruptedException {
        try (var executor = Executors.newFixedThreadPool(2)) {
            var mre = new MemoryReorderingExample();
            executor.invokeAll(List.of(() -> {
                mre.reader();
                return null;
            }, () -> {
                mre.writer();
                return null;
            }));

            System.out.println("Waiting for tasks to terminate...");
            executor.shutdown();
            while (!executor.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                System.out.println("Tasks is not terminated, waiting again");
            }
        }
    }
}
