package concurrency.buildingh2o;

import java.util.concurrent.Semaphore;

public class H2OSemaphoreOptimized {

    private final Semaphore hydrogenSemaphore = new Semaphore(2);
    private final Semaphore oxygenSemaphore = new Semaphore(0);

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogenSemaphore.acquire();
        releaseHydrogen.run();
        oxygenSemaphore.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygenSemaphore.acquire(2);
        releaseOxygen.run();
        hydrogenSemaphore.release(2);
    }
}
