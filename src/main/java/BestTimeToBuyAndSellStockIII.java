package main.java;

public class BestTimeToBuyAndSellStockIII {

    public static int returnMaxProfit(int[] prices) {
        return solution1(prices);
    }

    public static int solution1(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        
        int length = prices.length;
        int leftMin = prices[0];
        int rightMax = prices[length - 1];

        int[] leftProfits = new int[length];
        int[] rightProfits = new int[length + 1]; // add 1 to length for convenience

        for (int l = 1; l < length; l++) {
            leftProfits[l] = Math.max(leftProfits[l-1], prices[l] - leftMin);
            leftMin = Math.min(leftMin, prices[l]);

            int r = length - 1 - l;
            rightProfits[r] = Math.max(rightProfits[r + 1], rightMax - prices[r]);
            rightMax = Math.max(rightMax, prices[r]);
        }

        int maxProfit = 0;
        for (int i = 0; i < length; i++) {
            maxProfit = Math.max(maxProfit, leftProfits[i] + rightProfits[i + 1]);
        }
        return maxProfit;
    }

    public static int solution2(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        
        int t1Cost = Integer.MAX_VALUE;
        int t2Cost = Integer.MAX_VALUE;
        int t1Profit = 0;
        int t2Profit = 0;

        for (int price : prices) {
            t1Cost = Math.min(t1Cost, price);
            t1Profit = Math.max(t1Profit, price - t1Cost);

            t2Cost = Math.min(t2Cost, price - t1Profit);
            t2Profit = Math.max(t2Profit, price - t2Cost);
        }

        return t2Profit;
    }

    public static void main(String[] args) {
        int[] test1 = new int[]{3,3,5,0,0,3,1,4};
        int[] test2 = new int[]{1,2,3,4,5};
        int[] test3 = new int[]{7,6,4,3,1};

        System.out.println(returnMaxProfit(test1));
        System.out.println(returnMaxProfit(test2));
        System.out.println(returnMaxProfit(test3));
    }
}
