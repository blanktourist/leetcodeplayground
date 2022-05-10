// Original source: https://leetcode.com/discuss/interview-question/483660/Google-or-Phone-or-Currency-Conversion/528577
package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CurrencyConversion {
    
    static class CurrencyPair {
        String start;
        String end;
        double ratio;
        public CurrencyPair(String start, String end, double ratio) {
            this.start = start;
            this.end = end;
            this.ratio = ratio;
        }
    }

    public static double getBestConversionRate(String start, String end, List<CurrencyPair> currencyPairs) {
        // Build graph structure
        // Node A -> (Node B -> ratio)
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (CurrencyPair node : currencyPairs) {
            if (!graph.containsKey(node.start)) {
                graph.put(node.start, new HashMap<>());
            }
            graph.get(node.start).put(node.end, node.ratio);

            if (!graph.containsKey(node.end)) {
                graph.put(node.end, new HashMap<>());
            }
            graph.get(node.end).put(node.start, 1.0/node.ratio);
        }

        // BFS + HashSet
        Queue<String> q = new LinkedList<>();
        Queue<Double> val = new LinkedList<>();
        q.add(start);
        val.add(1.0);

        Set<String> visited = new HashSet<>();

        while (!q.isEmpty()) {
            String cur = q.poll();
            double num = val.poll();

            if (visited.contains(cur)) continue;

            visited.add(cur);

            Map<String, Double> neighbors = graph.get(cur);
            for (String neighbor : neighbors.keySet()) {
                if (!visited.contains(neighbor)) {
                    if (neighbor.equals(end)) return num * neighbors.get(neighbor);

                    q.offer(neighbor);
                    val.offer(num*neighbors.get(neighbor));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        List<CurrencyPair> currencyPairs = new ArrayList<>();
        currencyPairs.add(new CurrencyPair("USD", "JPY", 110));
        currencyPairs.add(new CurrencyPair("USD", "AUD", 1.45));
        currencyPairs.add(new CurrencyPair("JPY", "GBP", 0.0070));
        System.out.println(getBestConversionRate("GBP", "AUD", currencyPairs));
    }
}
