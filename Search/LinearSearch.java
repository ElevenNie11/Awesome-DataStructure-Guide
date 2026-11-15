package Search;

// @Author: Shiyi Nie (ElevenNie11)
// @Date: 2026-11-15
// @Content：顺序查找（线性查找）

// 数组元素不用按升序或者降序排列
// 时间复杂度：O(n)
public class LinearSearch {
    public static int linearSearch(int[] array, int target){
    // 数组为空或为null时，目标值不存在
    if(array == null || array.length == 0){
        return -1;
    }
    // 遍历数组，找到目标值，返回下标
    for(int i = 0; i < array.length; i++){
        if(array[i] == target){
            return i;    
        }
    }
    return -1;    // 遍历完整个数组仍未找到
}
    //  测试线性查找
    public static void main(String[] args){
        int[] arr = {1, 10, 15, 21, 34, 52, 60, 71, 83};
        if(linearSearch(arr, 10) != -1){
            System.out.println("目标在数组中");
        }else{
            System.out.println("目标不在数组中");
        }
    }
}

