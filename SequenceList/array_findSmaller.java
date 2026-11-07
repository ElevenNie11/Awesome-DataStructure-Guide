package SequenceList;
// 执行时间10ms
public class array_findSmaller {
    public static int[] smallerNumbersThanCurrent(int[] nums){
        int[] result = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            int count = 0;
            for(int j = 0; j < nums.length; j++){
                if(nums[j] < nums[i]){
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }

    public static void main(String[] args){
        int[] nums = {8, 1, 2, 2, 3};
        int[] result = smallerNumbersThanCurrent(nums);
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i]);
            if(i < result.length - 1){
                System.out.print(",");
            }
        }
    }
}
