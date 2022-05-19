package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayTesting {
    
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,75,88,99,101};

        System.out.println(array);
        System.out.println(Arrays.toString(array));

        // Doesn't work
        // List<Integer> arrayList = Arrays.asList(array);


        // Fancy streams
        System.out.println("");
        List<Integer> arrayList = Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println(arrayList);

        // Manual
        System.out.println("");
        List<Integer> arrayList2 = new ArrayList<>();
        for (int i : array) arrayList2.add(i);
        System.out.println(arrayList2);
    }
}
