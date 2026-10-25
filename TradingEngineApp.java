public class TradingEngineApp {
    public static void main(String[] args) {
        System.out.println("=== QUESTION 1 OUTPUT ===");

        // Using base class reference for polymorphism
        TradeEvent fix = new FIXOrderEvent("FIX_01", System.currentTimeMillis(), 1500.00, "ORD_994");
        TradeEvent reuters = new ReutersQuoteEvent("REUTERS_02", System.currentTimeMillis(), 1500.00, 1498.50, 1502.25);

        // Runs the overridden method in each child class
        fix.dispatch();
        reuters.dispatch();
    }
}
