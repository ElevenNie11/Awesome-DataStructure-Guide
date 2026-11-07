package Hash;
import java.util.HashMap;
import java.util.Map;
// 本题使用哈希表记录已经遍历过的数字及其下标，避免了对每个数字再从头搜索
public class hot100_two_sum {
    public static int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> indexMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int complement = target - nums[i];
            // 查找之前出现过的补数，避免重复使用同一个元素
            if(indexMap.containsKey(complement)){
                // 这是返回了一个数组，包含了两个数字的下标
                return new int[]{indexMap.get(complement), i};
            }
            indexMap.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args){
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);

        System.out.println("[" + result[0] + "," + result[1] + "]");
    }
}