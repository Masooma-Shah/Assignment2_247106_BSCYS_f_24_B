public abstract class SensorSearchEngine implements Runnable {
    protected double[] sensorData;
    protected int startIdx;
    protected int endIdx;   // exclusive

    public SensorSearchEngine(double[] sensorData, int startIdx, int endIdx) {
        this.sensorData = sensorData;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
    }

    public abstract void processGrid();

    @Override
    public void run() {
        processGrid();
    }
}
