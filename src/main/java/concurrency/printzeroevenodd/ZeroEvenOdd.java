package concurrency.printzeroevenodd;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;

class ZeroEvenOdd {

    private final Semaphore zeroSemaphore = new Semaphore(1);
    private final Semaphore evenSemaphore = new Semaphore(0);
    private final Semaphore oddSemaphore = new Semaphore(0);
    private final int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        try (var executor = Executors.newFixedThreadPool(3)) {
            var zeroEvenOdd = new ZeroEvenOdd(12);
            IntConsumer printNumber = System.out::print;
            Callable<Void> zero = () -> {
                try {
                    zeroEvenOdd.zero(printNumber);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            };
            Callable<Void> odd = () -> {
                try {
                    zeroEvenOdd.odd(printNumber);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            };
            Callable<Void> even = () -> {
                try {
                    zeroEvenOdd.even(printNumber);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            };
            executor.invokeAll(List.of(zero, odd, even));

            executor.shutdown();
            var isTerminated = executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
            System.out.println();
            System.out.println("Is all terminated: " + isTerminated);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zeroSemaphore.acquire();
            printNumber.accept(0);
            if (i % 2 == 0) {
                oddSemaphore.release();
            } else {
                evenSemaphore.release();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            oddSemaphore.acquire();
            printNumber.accept(i);
            zeroSemaphore.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenSemaphore.acquire();
            printNumber.accept(i);
            zeroSemaphore.release();
        }
    }
}