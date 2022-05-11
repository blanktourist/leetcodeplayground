package main.java;

public class BestTimeToBuyAndSellStockI {

    public static int returnMaxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int maxProfit = 0;
        int minPrice = prices[0];
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }

        return maxProfit;
    }
    
    public static void main(String[] args) {
        int[] test1 = new int[]{7,1,5,3,6,4};
        int[] test2 = new int[]{7,6,4,3,1};

        System.out.println(returnMaxProfit(test1));
        System.out.println(returnMaxProfit(test2));
    }
}
