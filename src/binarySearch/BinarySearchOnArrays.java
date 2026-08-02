package binarySearch;

import java.util.ArrayList;

public class BinarySearchOnArrays {

    // Overflow in case if the search space is INT_MAX
    // mid = low + ((high - low)/2)
    // initialize low, high and mid as long

    // 704. Binary Search
    public int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }
    // Recursive - TC: O(log N) | SC: O(log N) (Recursion Stack Space Only)
    public int binarySearch(int[] nums, int target, int begin, int end){
        if(begin > end){
            return -1;
        }
        int mid = begin + (end - begin) / 2;
        if(target == nums[mid])
            return mid;
        else if(target < nums[mid])
            return binarySearch(nums, target, begin, mid - 1);
        else
            return binarySearch(nums, target, mid+1, end);
    }

    // Iterative - TC: O(log N) | SC: O(1)
    public int binarySearch(int[] nums, int target){
        int low = 0, high = nums.length - 1;
        while(low <= high){
            int mid = low + ((high - low)/2);
            if(target == nums[mid]) return mid;
            else if(target > nums[mid]) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    // 35. Search Insert Position
    public int lowerBound(int[] nums, int x) {
        int low = 0;
        int high = nums.length - 1;
        int answer = nums.length;
        while(low <= high){
            int mid = low + ((high - low)/2);
            if(nums[mid] >= x){
                answer = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return answer;
    }

    // Upper Bound
    public int upperBound(int[] nums, int x) {
        int low = 0;
        int high = nums.length - 1;
        int answer = nums.length;
        while(low <= high){
            int mid = low + ((high - low)/2);
            if(nums[mid] > x){
                answer = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return answer;
    }

    // Floor and Ceil in Sorted Array
    public int[] getFloorAndCeil(int[] nums, int x) {
        int floor = -1;
        int ceil = -1;

        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == x) {
                floor = nums[mid];
                break;
            } else if (nums[mid] < x) {
                floor = nums[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        low = 0;
        high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == x) {
                ceil = nums[mid];
                break;
            } else if (nums[mid] > x) {
                ceil = nums[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return new int[]{floor, ceil};
    }

    // 34. Find First and Last Position of Element in Sorted Array
    public int[] searchRange(int[] nums, int target) {
        int lb = lowerBound(nums, target);
        if(lb == nums.length || nums[lb] != target)
            return new int[] {-1, -1};
        return new int[]{lb, upperBound(nums, target) - 1};
    }

    //
    public int[] firstAndLastOccurrence(int[] nums, int target) {
        int first = firstOccurrence(nums, target);
        if(first == -1) return new int[] {-1, -1};
        int last = lastOccurrence(nums, target);
        return new int[]{first, last};
    }

    public int firstOccurrence(int[] nums, int target){
        int first = -1;
        int low = 0, high = nums.length - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(nums[mid] == target){
                first = mid;
                high = mid - 1;
            }
            else if(nums[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return first;
    }

    public int lastOccurrence(int[] nums, int target){
        int last = -1;
        int low = 0, high = nums.length - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(nums[mid] == target){
                last = mid;
                low = mid + 1;
            }
            else if(nums[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return last;
    }

    public int countOccurrences(int[] arr, int target) {
        int first = firstOccurrence(arr, target);
        if(first == -1) return 0;
        int last = lastOccurrence(arr, target);
        return last - first + 1;
    }

    // 33. Search in Rotated Sorted Array
    public int searchRotatedSortedArray(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(nums[mid] == target) return mid;
            // left half is sorted
            if(nums[low] <= nums[mid]){
                if(nums[low] <= target && target <= nums[mid]){
                    high = mid - 1;
                }
                else
                    low = mid + 1;
            }
            // right half is sorted
            else{
                if(nums[mid] <= target && target <= nums[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return -1;
    }

    // 81. Search in Rotated Sorted Array II - includes duplicates - Worst Case TC: O(N/2)
    public boolean searchRotatedSortedArrayII(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(nums[mid] == target) return true;
            if(nums[low] == nums[mid] && nums[mid] == nums[high]){
                low++;
                high--;
                continue;
            }
            // left half is sorted
            if(nums[low] <= nums[mid]){
                if(nums[low] <= target && target <= nums[mid]){
                    high = mid - 1;
                }
                else
                    low = mid + 1;
            }
            // right half is sorted
            else{
                if(nums[mid] <= target && target <= nums[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return false;
    }

    // 153. Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        int min = Integer.MAX_VALUE;

        while(low <= high){
            int mid = (low + high) / 2;
            if(nums[low] <= nums[mid]){
                min = Math.min(min, nums[low]);
                low = mid + 1;
            }
            else{
                min = Math.min(min, nums[mid]);
                high = mid - 1;
            }
        }
        return min;
    }

    // Find out how many times the array is rotated
    public int findKRotation(ArrayList<Integer> nums) {
        int n = nums.size();
        int low = 0, high = n - 1;
        int min = Integer.MAX_VALUE;
        int minIdx = 0;

        while (low <= high) {
            int mid = (high - low) / 2;
            if (nums.get(low) <= nums.get(mid)) {
                min = Math.min(min, nums.get(low));
                minIdx = nums.indexOf(min);
                low = mid + 1;
            } else {
                min = Math.min(min, nums.get(mid));
                minIdx = nums.indexOf(min);
                high = mid - 1;
            }
        }
        return minIdx;
    }

    // 540. Single Element in a Sorted Array
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        if (nums[0] != nums[1]) return nums[0];
        if (nums[n-1] != nums[n-2]) return nums[n-1];

        int low = 1, high = n-2;

        while(low <= high){
            int mid = (low + high) / 2;
            if(nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1]){
                return nums[mid];
            }
            if(((mid % 2 == 1) && nums[mid] == nums[mid-1]) || ((mid % 2 == 0) && nums[mid] == nums[mid+1])){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return -1;
    }

    // 162. Find Peak Element
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if(n==1) return 0;
        if(nums[0] > nums[1]) return 0;
        if(nums[n-1] > nums[n-2]) return n-1;

        int low = 1, high = n - 2;
        while(low <= high){
            int mid = (low + high) / 2;
            if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]){
                return mid;
            }
            else if(nums[mid] > nums[mid-1]) low = mid + 1;
            else high = mid - 1;

        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
