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

    public static void main(String[] args) {

    }
}
