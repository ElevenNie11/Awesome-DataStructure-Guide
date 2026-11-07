package SequenceList;
// 数组串联
public class array_connection {
    public static int[] getConcatenation(int[] nums){
        int n = nums.length;
        int[] ans = new int[2 * n];
        for(int i = 0; i < n; i++){
            // 将原数组的每个元素复制到结果数组的前后两段
            ans[i] = nums[i];
            ans[i + n] = nums[i];
        }
        return ans;
    }

    public static void main(String[] args){
        int[] nums = {1, 2, 1};
        int[] ans = getConcatenation(nums);
        for(int i = 0; i < ans.length; i++){
            System.out.print(ans[i]);
            if(i < ans.length - 1){
                System.out.print(",");
            }
        }
    }
}
