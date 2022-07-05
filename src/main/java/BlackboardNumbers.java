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
    public static class Bag<Integer> {
        private Integer[] array;
        private int size;

        public Bag() {
            array = (Integer[]) new Object[100];
            size = 0;
        }

        public void add(Integer item) {
            array[size++] = item;
        }

        public Integer remove(int index) {
            Integer item = array[index];
            for (int i = index; i < size - 1; i++) {
                array[i] = array[i + 1];
            }
            size--;
            return item;
        }

        public int size() {
            return size;
        }

        public Integer get(int index) {
            return array[index];
        }

        public boolean isDone() {
            for (int i = 0; i < size - 1; i++) {
                int value = (int) array[i];
                int nextValue = (int) array[i + 1];
                if (value + 1 == nextValue) {
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

     /*
      * Is the board done?
      */
    public static boolean isDone(boolean[] set) {
        for (int i = 0; i < set.length - 1; i++) {
            if (set[i] && set[i + 1]) {
                return false;
            }
        }
        return true;
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
