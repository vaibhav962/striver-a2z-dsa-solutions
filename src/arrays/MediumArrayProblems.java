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
    public static void nextPermutation(int[] nums) {
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

    public static void setZeroes(int[][] matrix) {
        boolean firstColZero = false;
        int m = matrix.length;
        int n = matrix[0].length;

        for(int i = 0; i < m; i++){
            if(matrix[i][0] == 0){
                firstColZero = true;
            }

            for(int j = 1; j < n; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }

        if (matrix[0][0] == 0) {
            for (int j = 0; j < n; j++)
                matrix[0][j] = 0;
        }

        if (firstColZero) {
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
        }
    }

    // 48. Rotate Image
    public static void rotate90Degrees(int[][] matrix) {
        transpose(matrix);
        swapCols(matrix);
    }

    // One pass
    public static void rotate90(int[][] matrix) {
        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i];

                matrix[first][i] = matrix[last - offset][first];

                matrix[last - offset][first] = matrix[last][last - offset];

                matrix[last][last - offset] = matrix[i][last];

                matrix[i][last] = top;
            }
        }
    }

    // 54. Spiral Matrix
    public static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int top = 0;
        int left = 0;
        int bottom = m-1;
        int right = n-1;

        List<Integer> spiral = new ArrayList<>();

        while(top <= bottom && left <= right){
            for(int i = left; i <= right; i++){
                spiral.add(matrix[top][i]);
            }
            top++;

            for(int i = top; i <= bottom; i++){
                spiral.add(matrix[i][right]);
            }
            right--;

            if (top <= bottom){
                for(int i = right; i >= left; i--){
                    spiral.add(matrix[bottom][i]);
                }
                bottom--;
            }

            if (left <= right) {
                for(int i = bottom; i >= top; i--){
                    spiral.add(matrix[i][left]);
                }
                left++;
            }
        }
        return spiral;
    }

    // 560. Subarray Sum Equals K
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);

        int prefixSum  = 0;
        int count = 0;

        for(int num : nums){
            prefixSum += num;

            count += prefixCount.getOrDefault(prefixSum - k, 0);

            prefixCount.put(prefixSum, prefixCount.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    // Helper methods
    private static void swap(int[] nums, int a, int b){
        int x = nums[a];
        nums[a] = nums[b];
        nums[b] = x;
    }
    private static void swap(int[][] nums, int a, int b){
        int x = nums[a][b];
        nums[a][b] = nums[b][a];
        nums[b][a] = x;
    }

    public static void reverse(int[] nums, int start, int end){
        while(start < end){
            int x = nums[start];
            nums[start] = nums[end];
            nums[end] = x;
            start++;
            end--;
        }
    }

    public static void transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < i; j++){
                swap(matrix, i, j);
            }
        }
    }

    public static void swapCols(int[][] matrix){
        for(int[] row : matrix){
            swap(row,0,row.length-1);
        }
    }


    public static void main(String[] args) {
//        int[] nums = {3, 4, 2};
//        nextPermutation(nums);
//        System.out.println(Arrays.toString(nums));

//        int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
//        int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};

//        setZeroes(matrix);

//        for (int i = 0; i < matrix.length; i++) {
//            System.out.println(Arrays.toString(matrix[i]));
//        }

        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
//        rotate90(matrix);
//        for(int[] row : matrix){
//            System.out.println(Arrays.toString(row));
//        }


        System.out.println(spiralOrder(matrix));

    }

}
