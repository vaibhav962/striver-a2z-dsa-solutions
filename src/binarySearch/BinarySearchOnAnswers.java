package binarySearch;

import java.util.Arrays;

public class BinarySearchOnAnswers {
    // Find square root of a number
    // 69. Sqrt(x)
    public int floorSqrt(int n) {
        int low = 1, high = n;
        while(low <= high){
            int mid = low + (high - low)/2;
            if(mid*mid <= n){
                low = mid + 1;
            }
            else high = mid - 1;
        }
        return high;
    }

    // Find Nth root of a number
    public int NthRoot(int N, int M) {
        int low = 1, high = M;
        while(low <= high){
            int mid = low + (high - low)/2;
            int midN = compare(mid, N, M);
            if(midN == 1) return mid;
            else if(midN == 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    private int compare(int mid, int N, int M){
        long ans = 1;
        for (int i = 1; i <= N; i++) {
            ans = ans * mid;
            if(ans > M) return 2;
        }
        if(ans == M) return 1;
        return 0;
    }

    // 875. Koko Eating Bananas - TC: O(N log maxPile)
    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = findMax(piles);
        int ans = high;
        while(low <= high){
            int mid = low + (high - low)/2;
            int totalHours = calculateTotalHours(piles, mid, h);
            if(totalHours <= h){
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return ans;
    }

    private int findMax(int[] arr){
        int maxValue = Integer.MIN_VALUE;
        for (int i : arr) {
            maxValue = Math.max(maxValue, i);
        }
        return maxValue;
    }

    private int calculateTotalHours(int[] piles, int hourly, int h){
        int totalHours = 0;
        for(int i = 0; i < piles.length; i++){
            // totalHours += Math.ceil((double) piles[i] / (double)hourly);
            // better
            totalHours += (piles[i] + hourly - 1) / hourly;
            if (totalHours > h) return totalHours;
        }
        return totalHours;
    }

    // 1482. Minimum Number of Days to Make m Bouquets - TC: O(N log (max - min + 1))
    public int minDays(int[] bloomDay, int m, int k) {
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        for (int day : bloomDay) {
            low = Math.min(low, day);
            high = Math.max(high, day);
        }

        int answer = -1;
        if ((long) m * k > bloomDay.length) return -1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            if(possibleToMakeBouquets(bloomDay, mid, m, k)){
                answer = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return answer;
    }

    private boolean possibleToMakeBouquets(int[] arr, int day, int m, int k){
        int n = arr.length;
        if(n < m * k) return false;
        int totalBouquets = 0;
        int counter = 0;
        for (int i = 0; i < n; i++) {
            if(arr[i] <= day){
                counter++;
            }
            else{
                totalBouquets += counter/k;
                counter = 0;
            }
        }
        totalBouquets += counter/k;
        return totalBouquets >= m;
    }

    // 1283. Find the Smallest Divisor Given a Threshold - TC: O(N log (max))
    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1;
        int high = nums[0];

        for (int num : nums) {
            high = Math.max(high, num);
        }

        while(low <= high){
            int mid = low + (high - low)/2;
            if(divisorSum(nums, mid, threshold) <= threshold){
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return low;
    }

    private int divisorSum(int[] nums, int divisor, int threshold){
        int totalSum = 0;
        int n = nums.length;
        for (int num : nums) {
            totalSum += (num + divisor - 1) / divisor; //find the ceiling for the division
            if (totalSum > threshold)
                return totalSum;
        }
        return totalSum;
    }

    // 1011. Capacity To Ship Packages Within D Days - TC: O(N log (sum - max + 1))
    public int shipWithinDays(int[] weights, int days) {
        int low = 0;
        int high = 0;
        for (int weight : weights) {
            low = Math.max(low, weight);
            high += weight;
        }

        while(low <= high){
            int mid = (low + high) / 2;
            int numOfDays = requiredDays(weights, mid);
            if(numOfDays <= days){
                high = mid - 1;
            }
            else low = mid + 1;
        }

        return low;
    }

    private int requiredDays(int[] weights, int capacity){
        int load = 0;
        int days = 0;
        for(int weight : weights){
            if(load + weight <= capacity){
                load += weight;
            }
            else{
                days++;
                load = weight;
            }
        }
        return days;
    }

    // 1539. Kth Missing Positive Number - TC: O(N)
    public int findKthPositiveBrute(int[] arr, int k) {
        int n = arr.length;
        for(int i = 0; i < n; i++){
            if(arr[i] <= k) k++;
            else break;
        }
        return k;
    }

    public int findKthPositive(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;
        while(low <= high){
            int mid = (low + high) / 2;
            int missing = arr[mid] - (mid + 1);
            if(missing <= k) low = mid + 1;
            else high = mid - 1;
        }
//        (1) currentMissingNumber = arr[high] - (high + 1)
//        (2) answer = arr[high] + k - currentMissingNumber
//            answer = arr[high] + k - (arr[high] - high - 1))
//                   = arr[high] + k - arr[high] + high + 1))
//                   = k + high + 1
//                   = k + low
        return k + low;
    }

    // Aggressive Cows
    public int aggressiveCows(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int low = 1;
        int high = nums[n-1] - nums[0];

        while(low <= high){
            int mid = low + (high - low) / 2;
            if(isPossibleToPlace(nums, mid, k))
                low = mid + 1;
            else high = mid - 1;
        }

        return high;
    }

    private boolean isPossibleToPlace(int[] arr, int dist, int cows){
        int cowsPlaced = 1;
        int lastCow = arr[0];
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            if(arr[i] - lastCow >= dist){
                cowsPlaced++;
                lastCow = arr[i];
            }
        }
        return cowsPlaced >= cows;
    }

    // 1552. Magnetic Force Between Two Balls
    public int maxDistance(int[] position, int m) {
        int n = position.length;
        Arrays.sort(position);
        int low = 1;
        int high = position[n-1] - position[0];

        while(low <= high){
            int mid = low + (high - low) / 2;
            if(isPossibleToPlace(position, mid, m))
                low = mid + 1;
            else high = mid - 1;
        }

        return high;
    }

    private boolean isPossibleToPlaceBalls(int[] arr, int dist, int balls){
        int ballsPlaced = 1;
        int lastBall = arr[0];
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            if(arr[i] - lastBall >= dist){
                ballsPlaced++;
                if (ballsPlaced == balls) {
                    return true;
                }
                lastBall = arr[i];
            }
        }
        return ballsPlaced >= balls;
    }

    // Book Allocation Problem
    public int findPages(int[] nums, int m) {
        int low = 0, high = 0;

        for(int i : nums){
            low = Math.max(low, i);
            high += i;
        }

        while(low <= high){
            int mid = low + (high - low)/2;
            if(possibleStudents(nums, mid) <= m)
                high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }

    private int possibleStudents(int[] arr, int maxPages){
        int page = 0;
        int students = 1;
        for(int i = 0; i < arr.length; i++){
            if(page + arr[i] <= maxPages){
                page += arr[i];
            }
            else{
                students++;
                page = arr[i];
            }
        }
        return students;
    }

    // 410. Split Array Largest Sum - TC: O(N log (sum - max + 1))
    public int splitArray(int[] nums, int k) {
        int low = 0, high = 0;
        for(int i : nums){
            high += i;
            low = Math.max(low, i);
        }

        while(low <= high){
            int mid = low + (high - low) / 2;
            if(possibleSplit(nums, mid) <= k){
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return low;
    }

    private int possibleSplit(int[] arr, int maxSum){
        int split = 1;
        int sum = 0;
        int n = arr.length;
        for(int i = 0; i < n; i++){
            if(sum + arr[i] <= maxSum){
                sum += arr[i];
            }
            else{
                split++;
                sum = arr[i];
            }
        }
        return split;
    }

    // Minimize Max Distance to Gas Station
    public double minimiseMaxDistance(int[] arr, int k) {
        int n = arr.length;
        double low = 0;
        double high = 0;

        for(int i = 0; i < n - 1; i++){
            high = Math.max(high,(double)arr[i+1] - arr[i]);
        }
        double diff = 1e-6;
        while(high - low > diff){
            double mid = (low + high) / (2.0);
            int counter = numberOfGasStationsRequired(mid, arr);
            if(counter > k) low = mid;
            else high = mid;
        }
        return high;
    }

    private int numberOfGasStationsRequired(double dist, int[] arr){
        int cnt = 0;
        for(int i = 1; i < arr.length; i++){
            double numberInBetween = (arr[i] - arr[i-1]) / dist;
            if((arr[i] - arr[i-1]) / dist == numberInBetween * dist){
                numberInBetween--;
            }
            cnt += (int) numberInBetween;
        }
        return cnt;
    }

    // 4. Median of Two Sorted Arrays - TC: O(log (min(n1, n2)))
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        if(n1>n2) return findMedianSortedArrays(nums2, nums1);
        int low = 0, high = n1;

        int n = n1 + n2;
        int left = (n + 1) / 2;

        while(low <= high){
            int mid1 = (low + high) >> 1;
            int mid2 = left - mid1;
            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            if(mid1 < n1) r1 = nums1[mid1];
            if(mid2 < n2) r2 = nums2[mid2];
            if(mid1 - 1 >= 0) l1 = nums1[mid1-1];
            if(mid2 - 1 >= 0) l2 = nums2[mid2-1];

            if(l1 <= r2 && l2 <= r1){
                if(n % 2 == 1) return Math.max(l1, l2);
                return (double) (Math.max(l1, l2) + Math.min(r1, r2)) / (2.0);
            }
            else if(l1 > r2) high = mid1 - 1;
            else low = mid1 + 1;
        }
        return 0;
    }

    // Kth element of 2 sorted arrays - TC: O(log (min(n1, n2)))
    public int kthElement(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        if(n1>n2) return kthElement(nums2, nums1, k);
        int low = Math.max(0, k-n2), high = Math.min(k, n1);

        int n = n1 + n2;
        int left = k;

        while(low <= high){
            int mid1 = (low + high) >> 1;
            int mid2 = left - mid1;
            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            if(mid1 < n1) r1 = nums1[mid1];
            if(mid2 < n2) r2 = nums2[mid2];
            if(mid1 - 1 >= 0) l1 = nums1[mid1-1];
            if(mid2 - 1 >= 0) l2 = nums2[mid2-1];

            if(l1 <= r2 && l2 <= r1){
                return Math.max(l1, l2);
            }
            else if(l1 > r2) high = mid1 - 1;
            else low = mid1 + 1;
        }
        return 0;
    }

    public static void main(String[] args) {
//        int x = 24;
//        System.out.println(floorSqrt(x));
    }
}
