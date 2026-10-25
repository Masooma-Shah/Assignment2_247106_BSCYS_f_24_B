// Main driver checking bi-directional payload symmetry (palindrome check)
public class PacketSymmetryAuditor {
    public static void main(String[] args) {
        System.out.println("=== QUESTION 2: PACKET SYMMETRY AUDITOR ===");

        String payload = "RADAR"; // Test payload
        int length = payload.length();

        TransactionStack stack = new TransactionStack(length);
        RequestQueue queue = new RequestQueue(length);

        for (int i = 0; i < length; i++) {
            char ch = payload.charAt(i);
            stack.push(ch);
            queue.enqueue(ch);
        }

        boolean isSymmetric = true;
        while (!stack.isEmpty() && !queue.isEmpty()) {
            char fromStack = stack.pop();
            char fromQueue = queue.dequeue();

            if (fromStack != fromQueue) {
                isSymmetric = false;
                break;
            }
        }

        System.out.println("Auditing Payload: " + payload);
        if (isSymmetric) {
            System.out.println("VERIFICATION SUCCESS: Packet exhibits bi-directional symmetry (Palindrome Verified).");
        } else {
            System.out.println("VERIFICATION FAILED: Packet is asymmetrical.");
        }
    }
}