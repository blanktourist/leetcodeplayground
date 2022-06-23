package main.java;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class TreeSetHashMapTesting {

    public static void main(String[] args) {
            
        Map<String, Integer> countMap = new HashMap<String, Integer>();

        // Note that TreeSet maintains uniqueness of the elements in the set.
        // This means the comparator returning 0 will count the elements as duplicates.
        // So in the case where the count is equal, distinguish using the string values of a, b
        TreeSet<String> treeSet = new TreeSet<String>((a,b) -> {
            if (countMap.getOrDefault(a, 0) - countMap.getOrDefault(b, 0)  != 0) {
                return countMap.getOrDefault(a, 0) - countMap.getOrDefault(b, 0);
            } else {
                return a.compareTo(b);
            }
        });

        countMap.put("a", 1);
        countMap.put("b", 2);
        countMap.put("c", 3);
        countMap.put("d", 4);
        countMap.put("e", 5);
        countMap.put("z", 5); // This will not be counted as a duplicate since "e" != "z"
        
        treeSet.add("c"); // Order maintained by the TreeSet is determined by the comparator
        treeSet.add("b");
        treeSet.add("d");
        treeSet.add("e");
        treeSet.add("a");
        treeSet.add("z");

        System.out.println(treeSet);

        // Changing the map used for comparison doesn't re-sort the TreeSet.
        countMap.remove("c");
        System.out.println(treeSet);

        // Inserting unrelated elements doesn't re-sort the TreeSet.
        countMap.put("c", 4);
        countMap.put("d", 3);
        countMap.put("f", 4);
        treeSet.add("f");
        System.out.println(treeSet);

        // Removing the elements and re-adding them DOES re-sort the TreeSet according to the intended order in the Map.
        treeSet.remove("c");
        treeSet.remove("d");
        treeSet.add("c");
        treeSet.add("d");
        System.out.println(treeSet);
    }
}
