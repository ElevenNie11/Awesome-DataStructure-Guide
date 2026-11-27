package Sort;

import java.util.Arrays;

// 希尔排序就是插入排序的优化版本
// 希尔排序：通过 gap /= 2 不断分组，然后对每一组进行插入排序
public class ShellSort {
    public static void shellSort(int[] arr){
        int n = arr.length;
        for(int gap = n / 2; gap > 0; gap /= 2){
            for(int i = gap; i < n; i++){
                int key = arr[i];
                int j = i - gap;
                while(j > 0 && arr[j] < key){
                    arr[j + gap] = arr[j];
                    j = j - gap;
                }
                arr[j + gap] = key;
            }
        }
    }

    // 测试希尔排序
    public static void main(String[] args) {
        int[] arr = {8, 3, 5, 1, 9, 2, 7};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
