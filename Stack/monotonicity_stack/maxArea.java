package Stack.monotonicity_stack;

public class maxArea {
    public static int largestRectangleArea(int[] heights){
        int n = heights.length;
        int[] stack = new int[n];
        int top = -1;
        int maxArea = 0;

        for(int i = 0; i <= n; i++){
            // i == n 时用高度 0 的虚拟柱子，结算栈中剩余的柱子
            int currentHeight = i == n ? 0 : heights[i];

            while(top >= 0 && currentHeight < heights[stack[top]]){
                int height = heights[stack[top--]];
                int leftBoundary = top >= 0 ? stack[top] : -1;
                int width = i - leftBoundary - 1;
                int area = height * width;
                if(area > maxArea){
                    maxArea = area;
                }
            }

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
