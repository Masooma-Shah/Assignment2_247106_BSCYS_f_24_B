
public class AsyncWebEngineApp {
    public static void main(String[] args) {
        System.out.println("=== QUESTION 3: MULTI-THREADED TELEMETRY ENGINE ===");
        System.out.println("Master thread starting execution: " + Thread.currentThread().getName());

       
        TelemetryThread telemetryWorker = new TelemetryThread();
        telemetryWorker.setName("Telemetry-Worker");

        
        LogProcessorTask logTask = new LogProcessorTask();
        Thread logWorker = new Thread(logTask, "Log-Worker");

        
        telemetryWorker.start();
        logWorker.start();

        System.out.println("Active threads initialized. Execution running asynchronously...");
    }
}