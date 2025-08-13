package concurrency.printfoobar;

import java.util.concurrent.atomic.AtomicBoolean;

public class PrintFooBarAlternatively {

    private final AtomicBoolean firstInProgress = new AtomicBoolean(true);
    private final AtomicBoolean secondInProgress = new AtomicBoolean(false);

    private final int n;

    public PrintFooBarAlternatively(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) {

        for (int i = 0; i < n; i++) {
            while (secondInProgress.get() || !firstInProgress.get()) {
                Thread.onSpinWait();
            }
            printFoo.run();
            firstInProgress.set(false);
            secondInProgress.set(true);
        }
    }

    public void bar(Runnable printBar) {

        for (int i = 0; i < n; i++) {
            while (firstInProgress.get() || !secondInProgress.get()) {
                Thread.onSpinWait();
            }
            printBar.run();
            secondInProgress.set(false);
            firstInProgress.set(true);
        }
    }
}
