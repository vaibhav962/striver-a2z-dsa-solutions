package arrays;

public class CountInversions {
    // Count Inversions
    public int numberOfInversions(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] arr, int low, int high) {
        if (low >= high) {
            return 0;
        }

        int mid = low + (high - low) / 2;

        int leftInv = mergeSort(arr, low, mid);
        int rightInv = mergeSort(arr, mid + 1, high);
        int mergeInv = merge(arr, low, mid, high);

        return leftInv + rightInv + mergeInv;
    }

    private int merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];

        int left = low;
        int right = mid + 1;
        int i = 0;
        int inversions = 0;

        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp[i++] = arr[left++];
            } else {
                temp[i++] = arr[right++];
                inversions += (mid - left + 1);
            }
        }

        while (left <= mid) {
            temp[i++] = arr[left++];
        }

        while (right <= high) {
            temp[i++] = arr[right++];
        }

        for (i = 0; i < temp.length; i++) {
            arr[low + i] = temp[i];
        }

        return inversions;
    }
}
