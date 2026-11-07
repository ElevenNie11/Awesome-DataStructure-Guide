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

// 假设有：nums = [4, 3, 2, 7, 8, 2, 3, 1]
// 那么有：index = nums[1] - 1 = 3 - 1 = 2;
// 然后有：nums[2] = -nums[2];               把 2 改成 -2
// 现在有：nums = [4, 3, -2, 7, 8, 2, 3, 1]
// 而在新一轮循环里，如果不把nums[i]用Math.abs还原成正数，那么就会出现错误的下标计算（ArrayIndexOutOfBoundsException）