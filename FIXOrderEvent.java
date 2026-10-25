public class FIXOrderEvent extends TradeEvent {
    private String clOrdId;

    public FIXOrderEvent(String senderId, long timestamp, double basePrice, String clOrdId) {
        super(senderId, timestamp, basePrice);
        this.clOrdId = clOrdId;
    }

    @Override
    public void dispatch() {
        double fee = basePrice * 0.0015; // 0.15% fee
        double total = basePrice + fee;
        System.out.println("[FIX Gateway] Purchase Order | Order ID: " + clOrdId + 
                           " | Base: $" + basePrice + " | Fee: $" + String.format("%.2f", fee) + 
                           " | Total: $" + String.format("%.2f", total));
    }
}