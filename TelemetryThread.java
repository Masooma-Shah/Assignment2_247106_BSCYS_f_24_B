
public class TelemetryThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("[" + Thread.currentThread().getName() + "] System Metrics Capture (CPU/Memory) Iteration: " + i);
            try {
                Thread.sleep(800); // 800ms delay
            } catch (InterruptedException e) {
                System.out.println("Telemetry Thread interrupted.");
            }
        }
    }
}
