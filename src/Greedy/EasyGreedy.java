package Greedy;

import java.util.Arrays;

public class EasyGreedy {
    // 455. Assign Cookies
    // TC: O(N log N + M log M + M) | SC: O(1)
    public static int findContentChildren(int[] g, int[] s) {
        int m = g.length, n = s.length, l = 0, r = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        while(l < m && r < n){
            if(g[l] <= s[r]){
                l++;
            }
            r++;
        }
        return l;
    }

    // 860. Lemonade Change
    // TC: O(N) | SC: O(1)
    public static boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for(int i = 0; i < bills.length; i++){
            if(bills[i] == 5) five++;
            else if(bills[i] == 10){
                if(five != 0) {
                    five--;
                    ten++;
                }
                else return false;
            }
            else{
                if(ten != 0 && five != 0){
                    ten--; five--;
                }
                else if(five >= 3){
                    five -= 3;
                }
                else return false;
            }
        }
        return true;
    }

    // Fractional Knapsack
    // TC: O(N + N log N) | SC: O(1)
    public double fractionalKnapsack(int[] val, int[] wt, int capacity) {
        int n = val.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> Double.compare((double) val[b] / wt[b], (double) val[a] / wt[a]));

        double total = 0.0;
        int remaining = capacity;
        for (int i : idx) {
            if (remaining == 0) break;
            if (wt[i] <= remaining) {
                total += val[i];
                remaining -= wt[i];
            } else {
                total += (double) val[i] * remaining / wt[i];
                remaining = 0;
            }
        }
        return total;
    }

    // 55. Jump Game
    // TC: O(N) | SC: O(1)
    public static boolean canJump(int[] nums) {
        int maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i > maxIndex) return false;
            maxIndex = Math.max(maxIndex, i + nums[i]);
        }
        return true;
    }
}
