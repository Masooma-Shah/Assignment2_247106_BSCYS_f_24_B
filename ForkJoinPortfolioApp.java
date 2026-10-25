import java.util.Random;
import java.util.concurrent.ForkJoinPool;


public class ForkJoinPortfolioApp {
    public static void main(String[] args) {
        System.out.println("=== QUESTION 4: FORK/JOIN PARALLEL PORTFOLIO ENGINE ===");

        int size = 10000;
        double[] assets = new double[size];
        Random random = new Random(42);

        for (int i = 0; i < size; i++) {
            assets[i] = 100 + random.nextDouble() * 900;
        }

        ForkJoinPool pool = new ForkJoinPool();

        // 1. Compute total risk parallelly
        long startTime = System.nanoTime();
        ParallelRiskCalculator riskTask = new ParallelRiskCalculator(assets, 0, size);
        double totalRisk = pool.invoke(riskTask);
        long endTime = System.nanoTime();

        System.out.println("Total Portfolio Risk Score: " + String.format("%.2f", totalRisk));
        System.out.println("Risk Calculation Parallel Time: " + (endTime - startTime) / 1e6 + " ms");

        // 2. Apply stress test penalty parallelly
        PortfolioThresholdTask stressTask = new PortfolioThresholdTask(assets, 0, size, 500.0);
        pool.invoke(stressTask);
        System.out.println("Volatility stress-test penalty applied successfully to high-risk assets.");
    }
}