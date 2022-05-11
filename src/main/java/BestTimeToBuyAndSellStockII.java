package main.java;

public class BestTimeToBuyAndSellStockII {
    
    public static int returnMaxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i<prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                maxProfit += (prices[i] - prices[i-1]);
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] test1 = new int[]{7,1,5,3,6,4};
        int[] test2 = new int[]{1,2,3,4,5};
        int[] test3 = new int[]{7,6,4,3,1};

        System.out.println(returnMaxProfit(test1));
        System.out.println(returnMaxProfit(test2));
        System.out.println(returnMaxProfit(test3));
    }
}
