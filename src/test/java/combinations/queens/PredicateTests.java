package combinations.queens;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class PredicateTests {

    @Test
    public void testConsumerNoPredicatesWrapper() {
        AtomicInteger callCounter = new AtomicInteger();
        Consumer<int[]> counterConsumer = (a) -> callCounter.incrementAndGet();

        Consumer<int[]> pConsumer = Predicates.consumer(counterConsumer);

        int n = 10;
        for (int i = 0; i < n; i++) {
            pConsumer.accept(null);
        }

        assertEquals(n, callCounter.get());
    }

    @Test
    public void testConsumerNPredicatesWrapper() {
        AtomicInteger predicateCounter = new AtomicInteger();

        AtomicInteger consumerCounter = new AtomicInteger();
        Consumer<int[]> counterConsumer = (a) -> consumerCounter.incrementAndGet();

        Consumer<int[]> pConsumer = Predicates.consumer(counterConsumer,
                arr -> {
                    predicateCounter.incrementAndGet();
                    return true;
                },
                arr -> {
                    int v = predicateCounter.incrementAndGet();
                    return v % 3 == 0;
                });

        int n = 10;
        for (int i = 0; i < n; i++) {
            pConsumer.accept(new int[]{i});
        }

        assertEquals(3, consumerCounter.get());
        assertEquals(n * 2, predicateCounter.get());

    }
}
