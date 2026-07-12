package arrays;

import java.util.Arrays;

public class EasyArrayProblems {
    static int largestElement(int[] nums){
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > max)
                max = nums[i];
        }
        return max;
    }


    // Better than Bruteforce..
    static int secondLargest(int[] nums){
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > largest)
                largest = nums[i];
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > secondLargest && nums[i] < largest)
                secondLargest = nums[i];
        }
        return secondLargest;
    }

    static int secondSmallest(int[] nums){
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] < smallest){
                secondSmallest = smallest;
                smallest = nums[i];
            }
            else if(nums[i] < secondSmallest){
                secondSmallest = nums[i];
            }
        }
        return secondSmallest;
    }

    // 1752. Check if Array Is Sorted and Rotated
    public boolean check(int[] nums) {
        int n = nums.length;
        int count = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] > nums[(i+1)%n])
            {
                count++;
                if(count > 1) return false;
            }

        }
        return true;
    }

    // 26. Remove Duplicates from Sorted Array
    static int removeDuplicates(int[] nums) {
        if(nums.length == 0)
            return 0;

        int i = 0;
        for(int j = 1; j < nums.length; j++){
            if(nums[j] != nums[i]){
                nums[++i] = nums[j];
            }
        }
        return i+1;
    }

    // 189. Rotate Array
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int x = nums[left];
            nums[left] = nums[right];
            nums[right] = x;
            left++;
            right--;
        }

        left = 0;
        int mid = k-1;
        while(left < mid){
            int x = nums[left];
            nums[left] = nums[mid];
            nums[mid] = x;
            left++;
            mid--;
        }

        right = nums.length - 1;
        while(k < right){
            int x = nums[k];
            nums[k] = nums[right];
            nums[right] = x;
            k++;
            right--;
        }
    }

    // 189 with helper reverse(nums, start, end)
    public void rotateByK(int[] nums, int k){
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }


    // 283. Move Zeros
    public void moveZeroes(int[] nums) {
        int insertPos = 0;
        if(nums.length > 1){
            for(int i = 0; i < nums.length; i++){
                if(nums[i] != 0){
                    if (insertPos != i) {
                        nums[insertPos] = nums[i];
                        nums[i] = 0;
                    }
                    insertPos++;
                }
            }
        }

    }

    // 485. Maximum Consecutive Ones
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0, answer = 0, temp = 0;

        for (int i = 0; i < nums.length; i++) {
            // Do something with element nums[i].
            if(nums[i] == 0){
                count = 0;
            }
            else {
                count++;
                temp = count;
            }

            if(temp > answer)
                answer = temp;
        }
        return answer;
    }

    // 136. Single Number
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int i = 0; i < nums.length; i++){
            result ^= nums[i];
        }
        return result;
    }

    // Helper methods
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
        EasyArrayProblems eap = new EasyArrayProblems();

        int[] nums = {8, 10, 5, 7, 9};
        System.out.println(secondSmallest(nums));
    }
}
