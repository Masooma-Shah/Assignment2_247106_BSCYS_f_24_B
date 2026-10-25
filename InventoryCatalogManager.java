import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class InventoryCatalogManager {
    private static final int THREADS = 10;
    private static final int TOTAL_OPS = 50000;
    private static final int PRODUCTS = 100;

    // Runs the same test on any map we give it
    private static void runBenchmark(Map<String, Integer> catalog, String name) {
        // fill the catalog with starting stock
        for (int i = 0; i < PRODUCTS; i++) {
            catalog.put("P" + i, 1000);
        }

        int opsPerThread = TOTAL_OPS / THREADS;
        Thread[] workers = new Thread[THREADS];

        for (int t = 0; t < THREADS; t++) {
            workers[t] = new Thread(() -> {
                for (int i = 0; i < opsPerThread; i++) {
                    String id = "P" + ThreadLocalRandom.current().nextInt(PRODUCTS);
                    if (i % 2 == 0) {
                        catalog.get(id);                      // lookup
                    } else {
                        catalog.put(id, ThreadLocalRandom.current().nextInt(1000)); // update
                    }
                }
            });
        }

        long start = System.nanoTime();
        for (Thread w : workers) w.start();
        for (Thread w : workers) {
            try {
                w.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.nanoTime();

        double seconds = (end - start) / 1_000_000_000.0;
        double throughput = TOTAL_OPS / seconds;
        System.out.println("=== " + name + " ===");
        System.out.println("Execution time : " + seconds * 1000 + " ms");
        System.out.println("Throughput     : " + (long) throughput + " operations/second");
    }

    public static void benchmarkSynchronizedMap() {
        Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());
        runBenchmark(map, "Collections.synchronizedMap");
    }

    public static void benchmarkConcurrentMap() {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        runBenchmark(map, "ConcurrentHashMap");
    }

    public static void main(String[] args) {
        benchmarkSynchronizedMap();
        benchmarkConcurrentMap();
    }
}