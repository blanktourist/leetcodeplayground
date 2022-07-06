package main.java;

public class BlackboardNumbers {
    
    /*
     * Imagine you write the integers from 1 to 100 on a blackboard. 
     * At every time step you erase a number at random, 
     * each with equal probability from the board. 
     * You do this until there are no longer any consecutive sequences on the board. 
     * What the expected number of steps this takes?
     */
    public static int simulate() {
        Bag<Integer> bag = new Bag<Integer>();
        for (int i = 1; i <= 100; i++) {
            bag.add(i);
        }

        int steps = 0;
        while (bag.size() > 0 && !bag.isDone()) {
            // System.out.println(bag);
            steps++;
            int index = (int) (Math.random() * bag.size());
            bag.remove(index);
        }
        return steps;
    }

    /*
     * Implements a bag of 100 integers.
     */
    public static class Bag<T> {
        private int[] array;
        private int size;

        public Bag() {
            array = new int[100];
            size = 0;
        }

        public void add(int item) {
            array[size++] = item;
        }

        public int remove(int index) {
            int item = array[index];
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            size--;
            return item;
        }

        public int size() {
            return size;
        }

        public int get(int index) {
            return array[index];
        }

        public boolean isDone() {
            for (int i = 0; i < size - 1; i++) {
                if (array[i] + 1 == array[i + 1]) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < size; i++) {
                sb.append(array[i]);
                if (i < size - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }

    }

    public static void main(String[] args) {
        Double sum = 0.0;
        int totalItrs = 10000;
        for (int itr = 0; itr < totalItrs; itr++) {
            int steps = simulate();
            sum += steps;
        }
        System.out.println("Average steps: " + sum /(totalItrs * 1.0));
    }
}
