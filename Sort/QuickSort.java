package Sort;
import java.util.Arrays;

public class QuickSort {
    public static void quickSort(int[] arr, int low, int high){
        if(low < high){    
            int pi = partition(arr, low, high); // Partition the array and get the pivot position.
            quickSort(arr, low, pi - 1);        // Recursively sort the left side of the pivot.
            quickSort(arr, pi + 1, high);       // Recursively sort the right side of the pivot.
        }
    }

    private static int partition(int[] arr, int low, int high){
        int pivot = arr[high];      // Set the last element as the pivot.
        int i = low - 1;            // Initialize pointer i to low - 1.
        // Iterate from low to high - 1.
        for(int j = low; j < high; j++){
            // Move elements less than or equal to the pivot to the left.
            if(arr[j] <= pivot){
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);    // Place the pivot in its correct position.
        return i + 1;              // Return the pivot position.
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
        System.out.println(Arrays.toString(arr));   // 
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
