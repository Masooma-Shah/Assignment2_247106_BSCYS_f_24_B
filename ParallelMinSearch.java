public class ParallelMinSearch extends SensorSearchEngine {
    private double minResult = Double.MAX_VALUE;

    public ParallelMinSearch(double[] data, int start, int end) {
        super(data, start, end);
    }

    @Override
    public void processGrid() {
        for (int i = startIdx; i < endIdx; i++) {
            if (sensorData[i] < minResult) {
                minResult = sensorData[i];
            }
        }
    }

    public double getMinResult() {
        return minResult;
    }
}
