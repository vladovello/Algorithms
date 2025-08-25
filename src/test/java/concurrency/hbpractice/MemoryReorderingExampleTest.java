package concurrency.hbpractice;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Description;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.I_Result;

@JCStressTest
@Description("Triggers memory reordering")
@Outcome(id = "-1", expect = Expect.ACCEPTABLE, desc = "Not initialized yet")
@Outcome(id = "5", expect = Expect.ACCEPTABLE, desc = "Returned correct value")
@Outcome(id = "0", expect = Expect.ACCEPTABLE_INTERESTING, desc = "Initialized but returned default value")
public class MemoryReorderingExampleTest {

    @Actor
    public final void actor1(DataHolder dataHolder) {
        dataHolder.writer();
    }

    @Actor
    public final void actor2(DataHolder dataHolder, I_Result r) {
        r.r1 = dataHolder.reader();
    }

    @State
    public static class DataHolder {

        private volatile int x;
        private volatile boolean initialized = false;

        public void writer() {
            x = 5;
            initialized = true;
        }

        public int reader() {
            if (initialized) {
                return x;
            }
            return -1; // return mock value if not initialized
        }
    }
}
