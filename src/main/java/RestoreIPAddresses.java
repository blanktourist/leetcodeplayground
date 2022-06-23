package main.java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RestoreIPAddresses {

    /*
     * A valid IP address consists of exactly four integers separated by single
     * dots.
     * Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
     * 
     * Given a string s containing only digits,
     * return all possible valid IP addresses that can be formed by inserting dots
     * into s.
     * You are not allowed to reorder or remove any digits in s. You may return the
     * valid IP addresses in any order.
     * 
     * Example 1:
     * Input: s = "25525511135"
     * Output: ["255.255.11.135","255.255.111.35"]
     * 
     * Example 2:
     * Input: s = "0000"
     * Output: ["0.0.0.0"]
     * 
     * Example 3:
     * Input: s = "101023"
     * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
     * 
     */
    public List<String> restoreIpAddresses(String s) {
        LinkedList<String> segments = new LinkedList<String>();
        ArrayList<String> output = new ArrayList<String>();
        backtrack(s, -1, 3, segments, output);
        return output;
    }

    private void backtrack(String s, int prev_pos, int dots, LinkedList<String> segments, ArrayList<String> output) {
        /*
         * prev_pos : the position of the previously placed dot
         * dots : number of dots to place
         */
        // The current dot curr_pos could be placed
        // in a range from prev_pos + 1 to prev_pos + 4.
        // The dot couldn't be placed
        // after the last character in the string.
        int max_pos = Math.min(s.length() - 1, prev_pos + 4);
        for (int curr_pos = prev_pos + 1; curr_pos < max_pos; curr_pos++) {
            String segment = s.substring(prev_pos + 1, curr_pos + 1);
            if (isSegmentValid(segment)) {
                segments.add(segment); // place dot
                if (dots - 1 == 0) // if all 3 dots are placed
                    update_output(s, curr_pos, segments, output); // add the solution to output
                else
                    backtrack(s, curr_pos, dots - 1, segments, output); // continue to place dots
                segments.removeLast(); // remove the last placed dot
            }
        }
    }

    private void update_output(String s, int curr_pos, LinkedList<String> segments, ArrayList<String> output) {
        /*
         * Append the current list of segments
         * to the list of solutions
         */
        String segment = s.substring(curr_pos + 1, s.length());
        if (isSegmentValid(segment)) {
            segments.add(segment);
            output.add(String.join(".", segments));
            segments.removeLast();
        }
    }

    private boolean isSegmentValid(String segment) {
        /*
         * Check if the current segment is valid :
         * 1. less or equal to 255
         * 2. the first character could be '0'
         * only if the segment is equal to '0'
         */
        int m = segment.length();
        if (m > 3)
            return false;
        return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (m == 1);
    }


    public static void main(String[] args) {
        RestoreIPAddresses r = new RestoreIPAddresses();
        System.out.println(r.restoreIpAddresses("25525511135"));
        System.out.println(r.restoreIpAddresses("0000"));
        System.out.println(r.restoreIpAddresses("101023"));
    }
}