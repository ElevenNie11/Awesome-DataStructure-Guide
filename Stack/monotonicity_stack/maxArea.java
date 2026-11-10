package Stack.monotonicity_stack;
import java.util.ArrayDeque;
import java.util.Deque;

public class maxArea {
    public static int largestRectangleArea(int[] h){
        int n = h.length;
        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i <= n; i++){
            int cur = (i == n) ? 0 : h[i];       // 末尾哨兵高度 0，清空栈
            // 小于栈顶柱子的高度
            while(!stack.isEmpty() && cur < h[stack.peek()]){
                int height = h[stack.pop()];     // 以它为矩形的高
                int left = stack.isEmpty() ? -1 : stack.peek();
                int width = i - left - 1;        // 左右边界宽度之间
                max = Math.max(max, height * width);
            }
            stack.push(i);
        }
        return max;
    }

    public static void main(String[] args){
        int[] heights = {2, 1, 5, 6, 2, 3};
        int maxArea = largestRectangleArea(heights);
        System.out.println(maxArea);
    }
}
