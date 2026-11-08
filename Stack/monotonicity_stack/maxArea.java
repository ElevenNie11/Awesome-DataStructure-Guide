package Stack.monotonicity_stack;

public class maxArea {
    public static int largestRectangleArea(int[] heights){
        int n = heights.length;
        // 单调栈保存柱子的下标，栈中对应的高度从底到顶递增
        int[] stack = new int[n];
        int top = -1;
        int maxArea = 0;

        // 多扫描一次，用高度为0的虚拟柱子结算栈中剩余的柱子
        for(int i = 0; i <= n; i++){
            int currentHeight;
            if(i == n){
                currentHeight = 0;
            }else{
                currentHeight = heights[i];
            }

            // 当前柱子更矮，栈顶柱子无法再向右延伸，以当前下标作为右边界
            while(top >= 0 && currentHeight < heights[stack[top]]){
                int height = heights[stack[top--]];

                // 弹出后新的栈顶是左边界；栈为空时左边界设为-1
                int leftBoundary = -1;
                if(top >= 0){
                    leftBoundary = stack[top];
                }

                // 左右边界本身不属于矩形，宽度为右边界减左边界再减1
                int width = i - leftBoundary - 1;
                int area = height * width;
                if(area > maxArea){
                    maxArea = area;
                }
            }

            // 真实柱子入栈；虚拟柱子只用于触发出栈，不入栈
            if(i < n){
                stack[++top] = i;
            }
        }
        return maxArea;
    }

    public static void main(String[] args){
        int[] heights = {2, 1, 5, 6, 2, 3};
        int maxArea = largestRectangleArea(heights);
        System.out.println(maxArea);
    }
}
