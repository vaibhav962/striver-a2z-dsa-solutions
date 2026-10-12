package bitManipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problems {
    // 2220. Minimum Bit Flips to Convert Number
    public static int minBitFlips(int start, int goal) {
        int ans = start ^ goal;
        int count = 0;
        for(int i = 0; i < 31; i++){ // 31 since it is an int
            if((ans & (1 << i)) != 0){
                count++;
            }
        }
        return count;
    }

    public static int minBitFlipsBetter(int start, int goal) {
        int ans = start ^ goal;
        int count = 0;
        while (ans != 0) {
            ans &= (ans - 1); // removes the lowest set bit
            count++;
        }
        return count;
    }

    // 78. Subsets
    // TC: O(N * 2^n) | SC ≈ O(2^n * N)
    public static List<List<Integer>> subsets(int[] nums) {
        int subsets = 1 << nums.length;
        List<List<Integer>> answer = new ArrayList<>();
        for(int i = 0; i < subsets; i++){
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < nums.length; j++){
                if((i & (1 << j)) != 0) list.add(nums[j]);
            }
            answer.add(list);
        }
        return answer;
    }

    // 136. Single Number
    // TC: O(N) | SC: O(1)
    public static int singleNumber(int[] nums) {
        int result = 0;
        for(int i = 0; i < nums.length; i++){
            result ^= nums[i];
        }
        return result;
    }

    // 137. Single Number II
    // TC: O(N) | SC: O(1)
    public static int singleNumberII(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }
        return ones;
    }

    // TC: O(32N) | SC: O(1)
    public static int singleNumberIIBits(int[] nums) {
        int answer = 0;
        for(int bitIndex = 0; bitIndex < 31; bitIndex++){
            int count = 0;
            for(int i = 0; i < nums.length; i++){
                if((nums[i] & (1 << bitIndex)) != 0)
                    count++;
            }
            if(count % 3 == 1) answer = answer | (1 << bitIndex);
        }
        return answer;
    }

    // TC: O(N log N + N/3) | SC: O(1)
    public static int singleNumberIISort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i+=3) {
            if(nums[i] != nums[i-1])
                return nums[i-1];
        }
        return nums[nums.length-1];
    }

    // 260. Single Number III
    // TC: O(2N) | SC: O(1)
    public static int[] singleNumberIII(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int rightMostBit = Integer.lowestOneBit(xor);

        int b1 = 0, b2 = 0;
        for (int num : nums) {
            if ((num & rightMostBit) != 0) {
                b1 ^= num;
            } else b2 ^= num;
        }
        return new int[] {b1, b2};
    }

    // XOR of a Number Range
    public static int findXOR(int l, int r) {
        int left = (l == 0) ? 0 : findNXOR(l - 1);
        return left ^ findNXOR(r);
    }
    public static int findNXOR(int n){
        if(n % 4 == 1) return 1;
        else if(n % 4 == 2) return n + 1;
        else if(n % 4 == 3) return 0;
        else return n;
    }

    // 1486. XOR Operation in an Array
    // TC: O(N) | SC: O(1)
    public static int xorOperation(int n, int start) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result ^= start + 2 * i;
        }
        return result;
    }

    // 29. Divide Two Integers
    //  TC: O(log N)^2 | SC: O(1)
    public int divide(int dividend, int divisor) {
        if(dividend == divisor) return 1;
        boolean sign = true;
        if(dividend >= 0 && divisor < 0) sign = false;
        if(dividend < 0 && divisor >= 0) sign = false;

        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);
        divisor = Math.abs(divisor);
        long quotient = 0;

        while(n >= d){
            int count = 0;
            while(n >= (d << (count+1))){
                count++;
            }
            quotient += 1L << count;
            n = n - (d << count);
        }

        if(quotient == (1L <<31) && sign) return Integer.MAX_VALUE;
        if(quotient == (1L <<31) && !sign) return Integer.MIN_VALUE;

        return Math.toIntExact(sign ? quotient : -quotient);
    }
}
