package concurrency.diningphilosophers;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class DiningPhilosophers {

    private final Semaphore semaphore = new Semaphore(4, true); // the number of philosophers that is able to eat simultaneously
    private final Lock[] forks = IntStream.range(0, 5)
        .mapToObj(i -> new ReentrantLock())
        .toArray(Lock[]::new);

    public void wantsToEat(
        int philosopher,
        Runnable pickLeftFork,
        Runnable pickRightFork,
        Runnable eat,
        Runnable putLeftFork,
        Runnable putRightFork
    ) throws InterruptedException {
        var leftFork = forks[philosopher];
        var rightFork = forks[(philosopher + 4) % 5];

        semaphore.acquire();

        leftFork.lock();
        rightFork.lock();

        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putRightFork.run();
        putLeftFork.run();

        leftFork.unlock();
        rightFork.unlock();

        semaphore.release();
    }
}
