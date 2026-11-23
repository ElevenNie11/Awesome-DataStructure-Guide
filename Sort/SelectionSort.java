package Sort;

// repeatelly find the smallest element in the unsorted portion of the array
// and move it to the end of the sorted portion
// two operations: scanning & swapping

public class SelectionSort {
    public static void selectionSort(int[] arr){
         int n = arr.length;
         // outer loop marks the beginning of the unsorted portion
         for(int i = 0; i < n - 1; i++){
            int minIndex = i;
            // innner loop scanning the unsorted portion and find the minimum value in the unsorted potion
            for(int j = i + 1; j < n; j++){
                if(arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            // swap: once the smallest value is found, swap it
            if(minIndex != i){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
         }
    }

    // 测试折半插入排序
    public static void main(String[] args) {
        int[] arr = {4, 7, 2, 3, 0, 11};
        selectionSort(arr);
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}

// Time Complexity: O(n^2)
// Space Complexity: O(1)