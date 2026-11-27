package Sort;
import java.util.Arrays;
// Introduction:
// Heap: Complete Binary Tree with specific rules.
// MaxHeap: Parent >= Child (ASC)
// MinHeap: Parent <= Child (DESC)

// 【先构建最大堆，再不断将堆顶最大值移到数组末尾】
// Asc algorithm steps as follow:
// Step1: Convert the unsorted array into a MAX heap.
// Step2: Swap the root element with the last element, reduce the heap size by 1, and rebuild the max heap fore the remaining elements.
// Step3: Repeat util the heap size equal to 1. -> The array is fully sorted.

// Time Complexity:
// 1.) Building heap: O(n)
// 2.) Sorting: O(nlogn)

public class HeapSort {
    // helper01
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // helper02
    public static void heapify(int[] arr, int heapSize, int parent){
        int max = parent;
        int left = 2 * parent + 1;   // Left child index
        int right = 2 * parent + 2;  // Right child index
        // Find the largest element among root and its left & right children
        if(left < heapSize && arr[left] > arr[max]){
            max = left;
        }
        if(right < heapSize && arr[right] > arr[max]){
            max = right;
        }
        // If child is larger, swap it with its parent and continue
        if(max != parent){
            swap(arr, parent, max);
            heapify(arr, heapSize, max);       // Continue heapify.
        }
    }

    // helper03
    public static void heapSort(int[] arr){
        if(arr == null || arr.length <= 1) return ;
        int heapSize = arr.length;
        // Build a max heap.
        // Why i = heapSize / 2 - 1 at start???
        for(int i = heapSize / 2 - 1; i >= 0; i--){
            heapify(arr, heapSize, i);
        }
        // Sort the heap: Move the max elements(root) to the end of the array.
        for(int i = heapSize - 1; i > 0; i--){
            swap(arr, 0, i);
            heapify(arr, i, 0); // Rebuild the max heap in the remaining elements.
        }
    }

    // 测试堆排序
     public static void main(String[] args){
        int[] arr = {8, 3, 5, 1, 9, 2, 7};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}