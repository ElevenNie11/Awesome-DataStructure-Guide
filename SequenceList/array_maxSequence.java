package SequenceList;

public class array_maxSequence {
    public static int findMaxConsecutiveOnes(int[] nums){
        int currentCount = 0;
        int maxCount = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1){
                currentCount++;
                maxCount = Math.max(maxCount, currentCount); // 关键
            }else{
                // 遇到 0 后，连续 1 的计数重新开始
                currentCount = 0;
            }
        }
        return maxCount;
    }

    public static void main(String[] args){
        int[] nums = {1, 1, 0, 1, 1, 1};
        System.out.println(findMaxConsecutiveOnes(nums));
    }
}
