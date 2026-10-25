public class ReutersQuoteEvent extends TradeEvent {
    private double bidPrice;
    private double askPrice;

    public ReutersQuoteEvent(String senderId, long timestamp, double basePrice, double bidPrice, double askPrice) {
        super(senderId, timestamp, basePrice);
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
    }

    @Override
    public void dispatch() {
        double spread = askPrice - bidPrice;
        System.out.println("[Reuters Gateway] Analytics | Sender: " + senderId + 
                           " | Bid: $" + bidPrice + " | Ask: $" + askPrice + 
                           " | Spread: $" + String.format("%.2f", spread));
    }
}
