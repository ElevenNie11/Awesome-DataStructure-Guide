package Search;

// @Author: Shiyi Nie (ElevenNie11)
// @Date: 2026-11-15
// @Content：二分查找

// 【注意】：数组需要按升序排列后才能使用二分查找
// 【目的】：【升序】数组中查找目标值，返回目标值的下标；未找到则返回-1
public class BinarySearch {
    public static int binarySearch(int[] arr, int target){
        // 数组为空或为null时，目标值不存在
        if(arr == null || arr.length == 0){
            return -1;
        }
        int left = 0;                // 当前查找范围的左边界
        int right = arr.length - 1;  // 当前查找范围的右边界

        // 【重要逻辑】：每次都比较中间元素，将查找范围缩小一半
        while(left <= right){
            int mid = (left + right) / 2;             // 计算中间位置
            // int mid = left + (right - left) / 2;   // 计算中间位置（更保险的计算方法）
            if(arr[mid] == target){
                return mid;           // 找到目标值，返回下标
            }
            if(arr[mid] < target){
                left = mid + 1;       // 目标值在右半部分
            }
            if(arr[mid] > target){
                right = mid - 1;      // 目标值在左半部分
            }
        }
        return -1;                    // 查找范围为空，目标值不存在
    }

    //  测试二分查找
    public static void main(String[] args){
        int[] arr = {1, 10, 15, 21, 34, 52, 60, 71, 83};
        if(binarySearch(arr, 10) != -1){
            System.out.println("目标在数组中");
        }else{
            System.out.println("目标不在数组中");
        }
    }
}
