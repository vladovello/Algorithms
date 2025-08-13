package concurrency.printinorder;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintInOrderWithReentrantLock {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition firstC = lock.newCondition();
    private final Condition secondC = lock.newCondition();
    private final AtomicBoolean firstDone = new AtomicBoolean(false);
    private final AtomicBoolean secondDone = new AtomicBoolean(false);

    public void first(Runnable printFirst) {
        lock.lock();
        try {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstDone.set(true);
            firstC.signal();
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            while (!firstDone.get()) {
                firstC.await();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            secondDone.set(true);
            secondC.signal();
        } finally {
            lock.unlock();
        }

    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        try {
            while (!secondDone.get()) {
                secondC.await();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        } finally {
            lock.unlock();
        }
    }
}
