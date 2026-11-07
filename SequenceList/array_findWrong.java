package SequenceList;

public class array_findWrong {
    public static int[] findErrorNums(int[] nums){
        boolean[] seen = new boolean[nums.length + 1];  // java里布尔值默认为false
        int repeateNum = 0;
        int missingNum = 0;
        // 遍历数组，标记出现过的数字为true，并找出重复的数字
        for(int i = 0; i < nums.length; i++){
            if(seen[nums[i]]){
                repeateNum = nums[i];
            }else{
                seen[nums[i]] = true;
            }
        }
        // 遍历布尔数组，找出缺失的数字
        for(int i = 1; i < seen.length; i++){
            // !false
            if(!seen[i]){
                missingNum = i;
                break;
            }
        }
        return new int[]{repeateNum, missingNum};
    }

    public static void main(String[] args){
        int[] nums = {1, 2, 2, 4};
        int[] result = findErrorNums(nums);
        System.out.println("[" + result[0] + "," + result[1] + "]");
    }
}
