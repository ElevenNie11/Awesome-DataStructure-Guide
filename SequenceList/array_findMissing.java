package SequenceList;
import java.util.ArrayList;
import java.util.List;
// 使用原地负数标记法
// List有序集合
public class array_findMissing {
    public static List<Integer> findDisappearedNumbers(int[] nums){
        for(int i = 0; i < nums.length; i++){
            int index = nums[i] - 1;
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
