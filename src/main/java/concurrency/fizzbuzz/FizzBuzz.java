package concurrency.fizzbuzz;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class FizzBuzz {

    private final AtomicInteger counter = new AtomicInteger(1);
    private final int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) {
        var i = counter.get();
        while (i <= n) {
            if (i % 3 == 0 && i % 5 != 0) {
                printFizz.run();
                counter.incrementAndGet();
            } else {
                Thread.onSpinWait();
            }
            i = counter.get();
        }
    }

    public void buzz(Runnable printBuzz) {
        var i = counter.get();
        while (i <= n) {
            if (i % 3 != 0 && i % 5 == 0) {
                printBuzz.run();
                counter.incrementAndGet();
            } else {
                Thread.onSpinWait();
            }
            i = counter.get();
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) {
        var i = counter.get();
        while (i <= n) {
            if (i % 3 == 0 && i % 5 == 0) {
                printFizzBuzz.run();
                counter.incrementAndGet();
            } else {
                Thread.onSpinWait();
            }
            i = counter.get();
        }
    }

    public void number(IntConsumer printNumber) {
        var i = counter.get();
        while (i <= n) {
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
                counter.incrementAndGet();
            } else {
                Thread.onSpinWait();
            }
            i = counter.get();
        }
    }
}
