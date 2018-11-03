import org.jetbrains.annotations.Contract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;


public class LRUCacheTest {
    LRUCache cache;

    @Contract(pure = true)
    private int dumbSlowFunction(int x) {
        int result = 1;
        final int mod = 10003;
        for (int j = 0; j < 10; j++) {
            for (int i = 2; i <= x % mod; i++) {
                result *= i;
                result %= mod;
            }
        }
        return result;
    }


    @BeforeEach
    void setUp() {
        cache = new LRUCacheReal();
    }

    @Test
    void smallGet() {
        for (int i = 0; i < 10; i++) {
            int res = cache.get(i);
            Assertions.assertEquals(res, dumbSlowFunction(i));
        }
    }

    @Test
    void largeGet() {
        for (int i = 0; i < 100000; i++) {
            int res = cache.get(i);
            Assertions.assertEquals(res, dumbSlowFunction(i));
        }
    }

    @Test
    void testTime() {
        final int SIZE = 10000;
        Random rand = new Random();
        int test[] = new int[SIZE];
        int results[][] = new int[2][SIZE];
        for (int i = 0; i < SIZE; i++) {
            test[i] = rand.nextInt(100);
        }
        long startTimeWCache = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            results[0][i] = cache.get(test[i]);
        }
        long endTimeWCache = System.nanoTime();
        for (int i = 0; i < SIZE; i++) {
            results[1][i] = dumbSlowFunction(test[i]);
        }
        long endTimeWOCache = System.nanoTime();
        Assertions.assertTrue((endTimeWCache - startTimeWCache) < (endTimeWOCache - endTimeWCache));

        for (int i = 0; i < SIZE; i++) {
            Assertions.assertEquals(results[0][i], results[1][i]);
        }
    }
}
