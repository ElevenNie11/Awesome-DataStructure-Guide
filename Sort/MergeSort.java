package Sort;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] arr){
        if(arr.length > 1){
            // Divide the array into two halves.
            int mid = arr.length / 2;
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);
            // Recursively sort the left and right halves.
            mergeSort(left);
            mergeSort(right);
            // Merge two sorted halves.
            Merge(arr, left, right);
        }
    }

    public static void Merge(int[] arr, int[] left, int[] right){
        int i = 0, j = 0, k = 0;
        // Merge two sorted arrays.
        while(i < left.length && j < right.length){
            if(left[i] < right[j]){
                arr[k] = left[i];
                i++;
            }else{
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        // If there are ramaining elements in the left half
        // Copy them directly to the final array
        while(i < left.length){
            arr[k] = left[i];
            k++;
            i++;
        }

        // If there are ramaining elements in the right half
        // Copy them directly to the final array
        while(j < right.length){
            arr[k] = right[j];
            k++;
            j++;
        }
    }

    // 测试归并排序
    public static void main(String[] args) {
        int[] arr = {4, 7, 2, 3, 0, 11};
        mergeSort(arr);        
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
