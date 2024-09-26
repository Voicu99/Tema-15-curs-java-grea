import java.util.*;

public class ShoppingCartAnalyzer {

    private Map<String, Integer> productFrequency;

    public ShoppingCartAnalyzer() {
        productFrequency = new HashMap<>();
    }

    // Method to add new purchases to the user's shopping history
    public void addNewPurchases(List<List<String>> shoppingCarts) {
        for (List<String> cart : shoppingCarts) {
            for (String product : cart) {
                productFrequency.put(product, productFrequency.getOrDefault(product, 0) + 1);
            }
        }
    }

    // Method to find the K most frequently purchased items
    public List<String> getKFrequentItems(int K) {
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> a.getValue().equals(b.getValue()) ?
                        a.getKey().compareTo(b.getKey()) : Integer.compare(a.getValue(), b.getValue()));

        for (Map.Entry<String, Integer> entry : productFrequency.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > K) {
                minHeap.poll();
            }
        }

        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        Collections.reverse(result);  // Reverse the list to get the items in descending order of frequency
        return result;
    }

    public static void main(String[] args) {
        ShoppingCartAnalyzer analyzer = new ShoppingCartAnalyzer();

        // Example shopping carts
        List<List<String>> shoppingCarts = new ArrayList<>();
        shoppingCarts.add(Arrays.asList("apple", "banana", "orange"));
        shoppingCarts.add(Arrays.asList("banana", "apple", "grape"));
        shoppingCarts.add(Arrays.asList("apple", "grape", "orange"));
        shoppingCarts.add(Arrays.asList("apple", "orange"));

        // Adding purchases to the history
        analyzer.addNewPurchases(shoppingCarts);

        // Find and print the top 2 most frequent items
        int K = 2;
        List<String> topKItems = analyzer.getKFrequentItems(K);
        System.out.println("Top " + K + " most frequent items: " + topKItems);
    }
}
