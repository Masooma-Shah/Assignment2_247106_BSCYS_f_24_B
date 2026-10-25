public class ParallelMaxSearch extends SensorSearchEngine {
    private double maxResult = -Double.MAX_VALUE;

    public ParallelMaxSearch(double[] data, int start, int end) {
        super(data, start, end);
    }

    @Override
    public void processGrid() {
        for (int i = startIdx; i < endIdx; i++) {
            if (sensorData[i] > maxResult) {
                maxResult = sensorData[i];
            }
        }
    }

    public double getMaxResult() {
        return maxResult;
    }
}