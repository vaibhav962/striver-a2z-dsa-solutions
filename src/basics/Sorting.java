package basics;

import java.util.Arrays;

public class Sorting {
    // TC: O(N^2) - Best, Average & Worst | SC: O(1)
    public static void selectionSort(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            int min = i;
            for (int j = i; j < n; j++) {
                if(arr[j] < arr[min])
                    min = j;
            }
            swap(arr, min, i);
        }
    }
    
    // TC: O(N^2) Average & Worst, O(N) - Best | SC: O(1)
    public static void bubbleSort(int[] arr){
        int n = arr.length;
        for (int i = n-1; i > 0; i--) {
            boolean swap = false;
            for (int j = 0; j < i; j++) {
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swap = true;
                }
            }
            // no swap in first pass, array is already sorted.
            if(swap)
                break;
        }
    }

    // TC: O(N^2) Average & Worst, O(N) - Best | SC: O(1)
    public static void insertionSort(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            while(j > 0 && arr[j-1] > arr[j]){
                int temp = arr[j-1];
                arr[j-1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }

    // TC: O(N log N) - Best, Average & Worst | SC: O(N)
    public static void mergeSort(int[] arr){
        mS(arr, 0, arr.length - 1);
    }

    private static void mS(int[] arr, int low, int high){
        if(low >= high)
            return;
        int mid = (low + high) / 2;
        mS(arr, low, mid);
        mS(arr, mid+1, high);
        merge(arr, low, mid, high);
    }

    private static void merge(int[] arr, int low, int mid, int high){
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

    // TC: O(N log N) - Best, Average & Worst | SC: O(1) (Recursion Stack Space Only)
    public static void quickSort(int[] arr, int low, int high){
        if(low < high){
            int pivotIndex = pivot(arr, low, high);
            quickSort(arr, low, pivotIndex-1);
            quickSort(arr, pivotIndex+1, high);
        }
    }

    private static int pivot(int[] arr, int low, int high){
        int pivot = arr[low];
        int i = low;
        int j = high;
        while(i < j){
            while(arr[i] <= pivot && i <= high - 1){
                i++;
            }
            while(arr[j] > pivot && j >= low + 1){
                j--;
            }
            if(i < j){
                swap(arr, i , j);
            }
        }
        swap(arr, low, j);

        return j;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {13, 46, 24, 52, 20, 9};
//        mergeSort(arr);
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
