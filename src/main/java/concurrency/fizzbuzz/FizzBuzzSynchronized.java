package concurrency.fizzbuzz;

import java.util.function.IntConsumer;

public class FizzBuzzSynchronized {

    private final int n;
    private int counter = 1;

    public FizzBuzzSynchronized(int n) {
        this.n = n;
    }

    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (counter <= n) {
            if (counter % 3 != 0 || counter % 5 == 0) {
                wait();
                continue;
            }
            printFizz.run();
            ++counter;
            notifyAll();
        }
    }

    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (counter <= n) {
            if (counter % 3 == 0 || counter % 5 != 0) {
                wait();
                continue;
            }
            printBuzz.run();
            ++counter;
            notifyAll();
        }
    }

    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (counter <= n) {
            if (counter % 3 != 0 || counter % 5 != 0) {
                wait();
                continue;
            }
            printFizzBuzz.run();
            ++counter;
            notifyAll();
        }
    }

    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (counter <= n) {
            if (counter % 3 == 0 || counter % 5 == 0) {
                wait();
                continue;
            }
            printNumber.accept(counter++);
            notifyAll();
        }
    }
}
