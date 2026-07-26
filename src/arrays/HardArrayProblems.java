package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HardArrayProblems {
    // 118. Pascal's Triangle
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        result.add(firstRow);

        for(int i = 1; i < numRows; i++){
            List<Integer> prevRow = result.get(i-1);
            List<Integer> newRow = new ArrayList<>();

            newRow.add(1);

            for (int j = 1; j < i; j++) {
                newRow.add(prevRow.get(j - 1) + prevRow.get(j));
            }

            newRow.add(1);

            result.add(newRow);
        }
        return result;
    }

    // 229. Majority Element II
    public List<Integer> majorityElement(int[] nums) {
        int candidate1 = 0, candidate2 = 1;
        int count1 = 0, count2 = 0;

        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }

        List<Integer> result = new ArrayList<>();

        if (count1 > nums.length / 3) {
            result.add(candidate1);
        }
        if (count2 > nums.length / 3) {
            result.add(candidate2);
        }

        return result;
    }

    // 15. 3Sum
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n - 2; i++){
            // duplicates
            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }

            // early exits
            if (nums[i] > 0) break;
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) continue;
            if (nums[i] + nums[n - 2] + nums[n - 1] < 0) continue;

            // the search
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }

        }

        return result;
    }

    // 18. 4Sum
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for(int i = 0; i < n; i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            for(int j = i+1; j < n; j++){
                if(j != i+1 && nums[j] == nums[j-1]) continue;
                int k = j + 1;
                int l = n - 1;
                while(k < l){
                    long sum = nums[i];
                    sum += nums[j];
                    sum += nums[k];
                    sum += nums[l];

                    if(sum == target){
                        List<Integer> temp = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        result.add(temp);
                        k++;
                        l--;
                        while(k < l && nums[k] == nums[k-1]) k++;
                        while(k < l && nums[l] == nums[l+1]) l--;
                    }
                    else if(sum < target){
                        k++;
                    }
                    else{
                        l--;
                    }
                }
            }
        }
        return result;
    }

    // 56. Merge Intervals
    public int[][] mergeIntervals(int[][] intervals) {
        int n = intervals.length;
        List<int[]> answer = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for (int[] interval : intervals) {
            if (answer.isEmpty() || interval[0] > answer.getLast()[1])
                answer.add(interval);
            else {
                answer.getLast()[1] = Math.max(answer.getLast()[1], interval[1]);
            }
        }
        return answer.toArray(new int[answer.size()][]);
    }

    // 88. Merge Sorted Array
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }

    // if no extra space is provided in array 1
    public void mergeSortedArrays(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n;
        int gap = (len / 2) + (len % 2);
        while(gap > 0){
            int left = 0;
            int right = left + gap;
            while(right < len){
                if(left < m && right >= m){
                    swapIfGreater(nums1, nums2, left, right - m);
                }
                else if(left >= m){
                    swapIfGreater(nums2, nums2, left - m, right - m);
                }
                else{
                    swapIfGreater(nums1, nums1, left, right);
                }
                left++;
                right++;

            }
            if(gap == 1) break;
            gap = (gap / 2) + (gap % 2);
        }
    }
    private void swapIfGreater(int[] nums1, int[] nums2, int a, int b){
        if(nums1[a] > nums2[b]){
            int temp = nums1[a];
            nums1[a] = nums2[b];
            nums2[b] = temp;
        }
    }

    // 645. Set Mismatch & (A2Z) Find the repeating and missing number
    // Can be done by XOR method as well
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        long N = n;
        long sN = N * (N + 1) / 2;
        long s2N = N * (N + 1) * (2 * N + 1) / 6;
        long S = 0, S2 = 0;

        for (int num : nums) {
            S += num;
            S2 += (long) num * num;
        }

        long val1 = S - sN;       // x - y
        long val2 = S2 - s2N;     // x² - y²

        val2 /= val1;             // x + y

        long x = (val1 + val2) / 2;
        long y = x - val1;

        return new int[] { (int) x, (int) y };
    }

    // 493. Reverse Pairs
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] arr, int low, int high){
        int count = 0;
        if(low >= high)
            return count;
        int mid = low + (high - low) / 2;
        count += mergeSort(arr, low, mid);
        count += mergeSort(arr, mid+1, high);
        count += countPairs(arr, low, mid, high);
        merge(arr, low, mid, high);

        return count;
    }

    private int countPairs(int[] arr, int low, int mid, int high){
        int right = mid+1;
        int count = 0;
        for(int i = low; i <= mid; i++){
            while(right <= high && (long) arr[i] > 2L * arr[right]) right++;
            count += (right - (mid + 1));
        }
        return count;
    }

    private void merge(int[] arr, int low, int mid, int high){
        int[] temp = new int[high - low + 1];
        int i = 0;
        int left = low;
        int right = mid+1;
        while(left <= mid && right <= high){
            if(arr[left] <= arr[right]){
                temp[i++] = arr[left];
                left++;
            }
            else{
                temp[i++] = arr[right];
                right++;
            }
        }
        while(left <= mid){
            temp[i++] = arr[left];
            left++;
        }
        while(right <= high){
            temp[i++] = arr[right];
            right++;
        }

        for (int j = low; j <= high; j++) {
            arr[j] = temp[j - low];
        }
    }

    // 152. Maximum Product Subarray
    public int maxProduct(int[] nums) {
        int prefix = 1, suffix = 1;
        int answer = Integer.MIN_VALUE;

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if(prefix == 0) prefix = 1;
            if(suffix == 0) suffix = 1;

            prefix = prefix * nums[i];
            suffix = suffix * nums[n-i-1];
            answer = Math.max(prefix, suffix);
        }
        return answer;
    }


    public static void main(String[] args) {
        System.out.println(generate(5));
    }
}
