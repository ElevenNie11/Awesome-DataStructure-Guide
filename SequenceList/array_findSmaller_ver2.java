package SequenceList;
// 更高效的算法（1ms）
public class array_findSmaller_ver2 {
    public static int[] smallerNumbersThanCurrent(int[] nums){
        int[] frequency = new int[101];            // 频次数组，初始值默认为0
        for(int i = 0; i < nums.length; i++){
            frequency[nums[i]]++;                  // 统计每个元素出现的次数（下标为元素值，数组值为元素出现的次数）
        }
        for(int i = 1; i < frequency.length; i++){ // 累加频次，frequency[i]表示小于或等于i的数字的个数
            frequency[i] += frequency[i - 1];
        }

        int[] result = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            // 如果当前是nums[i]，那么小于nums[i]的个数就是小于或等于nums[i] - 1的个数，所以查frequency[nums[i] - 1]即可
            if(nums[i] == 0){
                result[i] = 0;
            }else{
                result[i] = frequency[nums[i] - 1];
            }
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