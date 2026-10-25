import java.util.Random;

public class SensorGridMonitorApp {
    public static void main(String[] args) throws InterruptedException {
        int size = 50000;
        int workers = 4;
        int chunk = size / workers;

        double[] data = new double[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            data[i] = rand.nextDouble() * 100;
        }

        ParallelMinSearch[] mins = new ParallelMinSearch[workers];
        ParallelMaxSearch[] maxs = new ParallelMaxSearch[workers];
        Thread[] threads = new Thread[workers * 2];

        long startTime = System.nanoTime();

        for (int w = 0; w < workers; w++) {
            int s = w * chunk;
            int e = (w == workers - 1) ? size : s + chunk;
            mins[w] = new ParallelMinSearch(data, s, e);
            maxs[w] = new ParallelMaxSearch(data, s, e);
            threads[w] = new Thread(mins[w], "Min-Worker-" + w);
            threads[w + workers] = new Thread(maxs[w], "Max-Worker-" + w);
        }

        for (Thread t : threads) t.start();   // all 8 start together
        for (Thread t : threads) t.join();    // wait for all

        double globalMin = Double.MAX_VALUE;
        double globalMax = -Double.MAX_VALUE;
        for (int w = 0; w < workers; w++) {
            System.out.println("Segment " + w + " -> min: " + mins[w].getMinResult()
                    + ", max: " + maxs[w].getMaxResult());
            globalMin = Math.min(globalMin, mins[w].getMinResult());
            globalMax = Math.max(globalMax, maxs[w].getMaxResult());
        }

        long endTime = System.nanoTime();
        System.out.println("Global minimum temperature : " + globalMin);
        System.out.println("Global maximum vibration   : " + globalMax);
        System.out.println("Execution time: " + (endTime - startTime) / 1000000.0 + " ms");
    }
}
