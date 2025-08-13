package concurrency.buildingh2o;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class H2OTest {

    @Test
    void test() throws InterruptedException {
        var executor = Executors.newFixedThreadPool(2);
        var water = "OOHHHH";
        var h2o = new H2O();
        var tasks = getTasks(water, h2o);

        executor.invokeAll(tasks);
        executor.shutdown();
        var isTerminated = executor.awaitTermination(2000, TimeUnit.MILLISECONDS);
        if (!isTerminated) {
            System.out.println("Tasks is not terminated");
        }
    }

    private static @NotNull ArrayList<Callable<Void>> getTasks(String water, H2O h2o) {
        Runnable releaseHydrogen = () -> System.out.print('H');
        Runnable releaseOxygen = () -> System.out.print('O');
        var tasks = new ArrayList<Callable<Void>>();

        for (var element : water.toCharArray()) {
            Callable<Void> task;
            if (element == 'H') {
                task = () -> {
                    h2o.hydrogen(releaseHydrogen);
                    return null;
                };
            } else {
                task = () -> {
                    h2o.oxygen(releaseOxygen);
                    return null;
                };
            }
            tasks.add(task);
        }
        return tasks;
    }
}