package concurrency.printfoobar;

public class PrintFooBarAlternativelyObjectWait {

    private final int n;
    private boolean isFoo = true;

    public PrintFooBarAlternativelyObjectWait(int n) {
        this.n = n;
    }

    public synchronized void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!isFoo) {
                wait();
            }
            printFoo.run();
            isFoo = false;
            notifyAll();
        }
    }

    public synchronized void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (isFoo) {
                wait();
            }
            printBar.run();
            isFoo = true;
            notifyAll();
        }
    }
}
