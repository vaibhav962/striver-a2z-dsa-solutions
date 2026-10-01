package stringProblems;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MediumStringProblems {
    // 451. Sort Characters By Frequency
    public String frequencySort(String s) {
        Map<Character, Integer> count = new HashMap<>();
        for(char c : s.toCharArray()){
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> count.get(b) - count.get(a));
        maxHeap.addAll(count.keySet());


        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll();
            int frequency = count.get(c);
            for (int i = 0; i < frequency; i++) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    // 1614. Maximum Nesting Depth of the Parentheses
    public int maxDepth(String s) {
        int depth = 0, maxDepth = 0;
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (current == '(') {
                depth++;
                maxDepth = Math.max(maxDepth, depth);
            } else if (current == ')') {
                depth--;
            }
        }
        return maxDepth;
    }

    // 13. Roman to Integer
    public int romanToInt(String s) {
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            int cur = value(s.charAt(i));
            if (i + 1 < s.length() && cur < value(s.charAt(i + 1))) {
                total -= cur;
            } else {
                total += cur;
            }
        }
        return total;
    }

    private int value(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    // 8. String to Integer (atoi)
    public int myAtoi(String s) {
        int index = 0, total = 0, sign = 1;

        if(s.isEmpty()) return 0;

        while(index < s.length() && s.charAt(index) == ' ') index++;

        if(index == s.length()) return 0;

        if(s.charAt(index) == '+' || s.charAt(index) == '-') {
            sign = s.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        while(index < s.length()){
            int digit = s.charAt(index) - '0';
            if(digit < 0 || digit > 9) break;
            if(Integer.MAX_VALUE / 10 < total || Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            total = total * 10 + digit;
            index++;
        }

        return total * sign;
    }

    // 5. Longest Palindromic Substring
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");

        StringBuilder result = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);

            if (i > 0) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    // 1781. Sum of Beauty of All Substrings
    public int beautySum(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'a']++;
                ans += beauty(freq);
            }
        }
        return ans;
    }

    private int beauty(int[] freq){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i : freq){
            if(i != 0){
                min = Math.min(min, i);
                max = Math.max(max, i);
            }
        }
        return max - min;
    }

    public static void main(String[] args) {

    }
}
