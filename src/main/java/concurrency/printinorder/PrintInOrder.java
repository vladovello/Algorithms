package concurrency.printinorder;

public class PrintInOrder {

    private volatile boolean isFirstExecuted = false;
    private volatile boolean isSecondExecuted = false;

    public void first(Runnable printFirst) {
        printFirst.run();
        isFirstExecuted = true;
    }

    public void second(Runnable printSecond) {
        while (!isFirstExecuted) {
            Thread.onSpinWait();
        }
        printSecond.run();
        isSecondExecuted = true;
    }

    public void third(Runnable printThird) {
        while (!isSecondExecuted) {
            Thread.onSpinWait();
        }
        printThird.run();
    }
}
