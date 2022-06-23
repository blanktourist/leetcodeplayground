package main.java;

public class MultiplyStrings {
    /*
     * Given two non-negative integers num1 and num2 represented as strings, 
     * return the product of num1 and num2, also represented as a string.
     * 
     * Note: Solve this problem without using built-in BigInteger library 
     * or converting the inputs to integer directly.
     * 
     * Example:
     * Input: num1 = "2", num2 = "3"
     * Output: "6"
     * 
     * Example:
     * Input: num1 = "123", num2 = "456"
     * Output: "56088"
     * 
     * Constraints:
     * 1. The length of both num1 and num2 is < 110.
     * 2. Both num1 and num2 contain only digits 0-9.
     * 3. Both num1 and num2 do not contain any leading zero, except the number 0 itself.
     */
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null)
            return null;
        if (num1.length() == 0 || num2.length() == 0)
            return "";
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = mul + res[i + j + 1];
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0)
                continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MultiplyStrings ms = new MultiplyStrings();
        System.out.println(ms.multiply("2", "3"));
        System.out.println(ms.multiply("123", "456"));
    }

}
