package concurrency.buildingh2o;

import java.util.concurrent.Semaphore;

public class H2O {

    private final Semaphore hydrogenSemaphore = new Semaphore(2);
    private final Semaphore oxygenSemaphore = new Semaphore(1);

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hydrogenSemaphore.acquire();
        releaseHydrogen.run();
        if (hydrogenSemaphore.availablePermits() == 0 && oxygenSemaphore.availablePermits() == 0) {
            oxygenSemaphore.release();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oxygenSemaphore.acquire();
        releaseOxygen.run();
        if (hydrogenSemaphore.availablePermits() == 0 && oxygenSemaphore.availablePermits() == 0) {
            hydrogenSemaphore.release(2);
        }
    }
}
