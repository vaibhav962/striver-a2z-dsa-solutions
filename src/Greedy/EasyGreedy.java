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
