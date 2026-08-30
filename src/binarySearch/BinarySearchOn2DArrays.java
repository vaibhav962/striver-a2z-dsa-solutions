package binarySearch;

public class BinarySearchOn2DArrays {
    // Find the row with maximum number of 1's - TC: O(N log M)
    public int rowWithMax1s(int[][] mat) {
        int max = 0;
        int index = -1;
        int n = mat.length;
        int m = mat[0].length;
        for (int i = 0; i < n; i++) {
            int ones = m - count1s(mat[i], m, 1);
            if(ones > max){
                max = ones;
                index = i;
            }
        }
        return index;
    }

    private int count1s(int[] arr, int m, int x){
        int low = 0;
        int high = m - 1;
        int ans = m;
        while(low <= high){
            int mid = (low + high) / 2;
            if(arr[mid] == x){
                ans = mid;
                high = mid - 1;
            }
            else low = mid + 1;
        }
        return ans;
    }

    // 74. Search a 2D Matrix - TC: O(log(rows * cols))
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int low = 0;
        int high = (rows * cols) - 1;

        while(low <= high){
            int mid = (low + high) / 2;
            int row = mid / cols;
            int column = mid % cols;

            if(matrix[row][column] == target) return true;
            else if(matrix[row][column] < target) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }

    // 240. Search a 2D Matrix II
    public boolean searchMatrixII(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int row = 0, col = m-1;

        while(row < n && col >= 0){
            if(matrix[row][col] == target){
                return true;
            }
            else if(matrix[row][col] < target)
                row++;
            else
                col--;
        }
        return false;
    }

    // 1901. Find a Peak Element II
    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length, m = mat[0].length - 1;
        int low = 0, high = m;
        while(low <= high){
            int mid = (low + high) / 2;
            int row = findMaxElement(mat, n, mid);
            int left = mid - 1 >= 0 ? mat[row][mid-1] : -1;
            int right = mid + 1 <= m ? mat[row][mid + 1] : -1;

            if(mat[row][mid] > left && mat[row][mid] > right) return new int[] {row, mid};

            else if(mat[row][mid] < left) high = mid - 1;
            else low = mid + 1;
        }
        return new int[] {-1, -1};
    }

    private int findMaxElement(int[][] mat, int n, int col){
        int maxValue  = -1;
        int index = -1;
        for(int i = 0; i < n; i++){
            if(mat[i][col] > maxValue){
                maxValue = mat[i][col];
                index = i;
            }
        }
        return index;
    }

    // Matrix Median
    public int findMedian(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;
        for(int[] x : matrix){
            low = Math.min(low, x[0]);
            high = Math.max(high, x[m-1]);
        }

        int req = (n * m) / 2;
        while(low <= high){
            int mid = low + (high - low) / 2;
            int nums = numOfElementsSmaller(matrix, mid);
            if(nums <= req) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }

    private int numOfElementsSmaller(int[][] mat, int x){
        int count = 0;
        for(int[] arr : mat){
            count += upperBound(arr, x);
        }
        return count;
    }

    private int upperBound(int[] nums, int x) {
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

    public static void main(String[] args) {

    }
}
