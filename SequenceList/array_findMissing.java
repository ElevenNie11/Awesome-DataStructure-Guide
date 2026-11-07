package SequenceList;
import java.util.ArrayList;
import java.util.List;
// 使用原地负数标记法
// List有序集合
public class array_findMissing {
    public static List<Integer> findDisappearedNumbers(int[] nums){
        for(int i = 0; i < nums.length; i++){
            // 修正：计算下标前先用 Math.abs() 还原数字的正值
            // int index = nums[i] - 1;  (错误)
            int value = Math.abs(nums[i]);
            int index = value - 1;
            if(nums[index] > 0){             // 用负数标记这个数字对应的位置已经出现
                nums[index] = -nums[index];
            }
        }

        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                result.add(i + 1);
            }
        }
        return result;
    }

    public static void main(String[] args){
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(findDisappearedNumbers(nums));
    }
}

// 假设有：nums = [2, 1, 2, 7, 8, 2, 3, 1]
// 那么有：index = nums[0] - 1 = 2 - 1 = 1;
// 然后有：nums[index] = nums[1] = -nums[1] = -1;
// 现在有：nums = [2, -1, 2, 7, 8, 2, 3, 1]
// 如果不使用 Math.abs()，那么在下一次循环中：
// 然后有：index = nums[1] - 1 = -1 - 1 = -2;  (错误: 负数下标不合法)