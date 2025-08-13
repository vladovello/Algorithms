package concurrency.fizzbuzz;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzzSemaphore {

    private final int n;
    private final Semaphore fizzSemaphore = new Semaphore(0);
    private final Semaphore buzzSemaphore = new Semaphore(0);
    private final Semaphore fizzBuzzSemaphore = new Semaphore(0);
    private final Semaphore numberSemaphore = new Semaphore(1);

    public FizzBuzzSemaphore(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 || i % 5 == 0) {
                continue;
            }
            fizzSemaphore.acquire();
            printFizz.run();
            numberSemaphore.release();
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 || i % 5 != 0) {
                continue;
            }
            buzzSemaphore.acquire();
            printBuzz.run();
            numberSemaphore.release();
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 || i % 5 != 0) {
                continue;
            }
            fizzBuzzSemaphore.acquire();
            printFizzBuzz.run();
            numberSemaphore.release();
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            numberSemaphore.acquire();

            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
                numberSemaphore.release();
            } else if (i % 5 != 0) {
                fizzSemaphore.release();
            } else if (i % 3 != 0) {
                buzzSemaphore.release();
            } else {
                fizzBuzzSemaphore.release();
            }
        }
    }
}
