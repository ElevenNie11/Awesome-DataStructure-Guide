package Sort;

// Steps:
// 1.) Divide the array into two parts, sorted and unsorted.
// 2.) One by one take an element from unsorted it into sorted.

public class InsertionSort {
    public static void insertionSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            int key = arr[i];               // the element we want to insert.
            int j = i - 1;
            while(j >= 0 && arr[j] > key){  // we use a while loop to compare this element with the element on its left.
                arr[j + 1] = arr[j];
                j--;                        // 再往左走
            }
            arr[j + 1] = key;
        }   
    }

    // 测试插入排序（升序）
    public static void main(String[] args) {
        int[] arr = {4, 7, 2, 3, 0, 11};
        insertionSort(arr);
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
