package Greedy;

public class HardGreedy {
    // 45. Jump Game II
    // TC: O(N) | SC: O(1)
    public static int jump(int[] nums) {
        int jumps = 0;
        int l = 0, r = 0;
        while(r < nums.length-1){
            int farthest = 0;
            for(int i = l; i <= r; i++){
                farthest = Math.max(i + nums[i], farthest);
            }
            l = r + 1;
            r = farthest;
            jumps++;
        }
        return jumps;
    }

    // 678. Valid Parenthesis String
    // TC: O(N) | SC: O(1)
    public boolean checkValidString(String s) {
        int min = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                min++;
                max++;
            } else if (s.charAt(i) == ')') {
                min--;
                max--;
            } else {
                min--;
                max++;
            }
            if(min < 0) min = 0;
            if(max < 0) return false;
        }
        return min == 0;
    }

    // TC: O(3^N) | SC: O(N)
    public static boolean checkValidStringRecursion(String s) {
        return parenthesisHelper(s, 0, 0);
    }
    private static boolean parenthesisHelper(String s, int idx, int count){
        if(count < 0) return false;
        if(idx == s.length()) return(count == 0);
        if(s.charAt(idx) == '(') return parenthesisHelper(s, idx+1, count + 1);
        if(s.charAt(idx) == ')') return parenthesisHelper(s, idx+1, count - 1);

        return parenthesisHelper(s, idx + 1, count + 1) ||
                parenthesisHelper(s, idx + 1, count - 1) ||
                parenthesisHelper(s, idx + 1, count);
    }
    // using DP TC: O(N^2) | SC: O(N^2)

    // 135. Candy
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        java.util.Arrays.fill(candies, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int total = 0;
        for (int c : candies) total += c;
        return total;
    }
}
