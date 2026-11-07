package SequenceList;

// 数组重排
// 数组长度是2n，前半段是x：nums[0]到nums[n - 1]；后半段是y：nums[n]到nums[2n - 1]
public class array_reorder {
    public static int[] reorder(int[] nums, int n){
        int[] ans = new int[2 * n];
        for(int i = 0; i < n; i++){
            // 依次取前半段的 x 和后半段对应的 y
            ans[2 * i] = nums[i];         // 下标为偶数 
            ans[2 * i + 1] = nums[i + n]; // 下标为奇数
        }
        return ans;
    }

    public static void main(String[] args){
        int[] nums = {2, 5, 1, 3, 4, 7};
        int n = 3;
        int[] ans = reorder(nums, n);

        for(int i = 0; i < ans.length; i++){
            System.out.print(ans[i]);
            if(i < ans.length - 1){
                System.out.print(",");
            }
        }
    }
}
