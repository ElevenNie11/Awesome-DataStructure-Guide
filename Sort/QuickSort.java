package Sort;
import java.util.Arrays;

public class QuickSort {
    public static void quickSort(int[] arr, int low, int high){
        if(low < high){
            // Partition the array and get the pivot position.
            int pi = partition(arr, low, high);
            // Recursively sort the left side of the pivot.
            quickSort(arr, low, pi - 1);
            // Recursively sort the right side of the pivot.
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high){
        // Set the last element as the pivot.
        int pivot = arr[high];
        // Initialize pointer i to low - 1.
        int i = low - 1;
        // Iterate from low to high - 1.
        for(int j = low; j < high; j++){
            // Move elements less than or equal to the pivot to the left.
            if(arr[j] <= pivot){
                i++;
                swap(arr, i, j);
            }
        }
        // Place the pivot in its correct position.
        swap(arr, i + 1, high);
        // Return the pivot position.
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    // 测试快速排序
    public static void main(String[] args){
        int[] arr = {8, 3, 5, 1, 9, 2, 7};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
