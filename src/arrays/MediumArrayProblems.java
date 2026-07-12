package arrays;

import java.util.*;

public class MediumArrayProblems {

    // 1. Twp Sum
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> mp = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            int complement = target - nums[i];

            if(mp.containsKey(complement)){
                return new int[] {mp.get(complement), i};
            }

            mp.put(nums[i], i);
        }
        return new int[] {};
    }

    // 75. Sort Colors
    public void sortColors(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        int mid = 0;

        while(mid <=high){
            if(nums[mid] == 0){
                swap(nums, low, mid);
                low++;
                mid++;
            }
            else if(nums[mid] == 1){
                mid++;
            }
            else{
                swap(nums, mid, high);
                high--;
            }
        }
    }

    // 169. Majority Element
    public int majorityElementSlow(int[] nums) {
        // Good not the best.
        int n = nums.length - 1;
        Arrays.sort(nums);
        if(n > 1)
            return nums[n/2];
        else
            return nums[0];
    }

    // Boyer-Moore Voting
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(count == 0){
                candidate = nums[i];
                count++;
            }
            else{
                if(nums[i] == candidate){
                    count++;
                }
                else{
                    count--;
                }
            }
        }
        return candidate;
    }

    // 53. Maximum Subarray
    // Kadane's Algorithm
    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];
        for(int i = 1; i < nums.length; i++){
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    // 121. Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;

        for(int i = 1; i < prices.length; i++){
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;

    }
    public int maxProfitFaster(int[] prices) {
        int min = prices[0];
        int ans = 0;

        for (int p : prices) {
            if (p < min)
                min = p;
            else if (p - min > ans)
                ans = p - min;
        }
        return ans;
    }

    // 2149. Rearrange Array Elements by Sign
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        int nextPos = 0;
        int nextNeg = 1;
        for(int i = 0; i < n; i++){
            if(nums[i] > 0){
                answer[nextPos] = nums[i];
                nextPos += 2;
            }
            else{
                answer[nextNeg] = nums[i];
                nextNeg += 2;
            }
        }
        return answer;
    }

    // 31. Next Permutation
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = nums.length - 2;
        while(i >= 0 && nums[i] >= nums[i+1]){
            i--;
        }
        if(i >= 0){
            int j = nums.length - 1;
            while(nums[j] <= nums[i]){
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i+1, n-1);
    }

    // Helper methods
    public void swap(int[] nums, int a, int b){
        int x = nums[a];
        nums[a] = nums[b];
        nums[b] = x;
    }

    private void reverse(int[] nums, int start, int end){
        while(start < end){
            int x = nums[start];
            nums[start] = nums[end];
            nums[end] = x;
            start++;
            end--;
        }
    }


    public static void main(String[] args) {
        MediumArrayProblems map = new MediumArrayProblems();
        int[] nums = {3, 4, 2};
        map.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

}
