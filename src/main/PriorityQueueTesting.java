package main;
import java.util.Arrays;
import java.util.PriorityQueue;

public class PriorityQueueTesting {
    
    public static void main(String[] args) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> b[0] - a[0]);
        int[] arr1 = new int[]{1,0};
        int[] arr2 = new int[]{2,0};
        int[] arr3 = new int[]{3,0};

        pq.add(arr3);
        pq.add(arr2);
        pq.add(arr1);

        System.out.println(Arrays.toString(pq.poll()));
        System.out.println(Arrays.toString(pq.poll()));
        System.out.println(Arrays.toString(pq.poll()));
    }
}
