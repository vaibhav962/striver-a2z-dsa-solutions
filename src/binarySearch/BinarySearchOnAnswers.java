package binarySearch;

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

    public static void main(String[] args) {
//        int x = 24;
//        System.out.println(floorSqrt(x));
    }
}
