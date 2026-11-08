package Stack.monotonicity_stack;
// 数组模拟单调栈，为每件商品寻找右侧第一个价格小于或等于它的商品，并计算折后价
public class goods_final_price {
    public static int[] finalPrices(int[] prices){
        int[] result = new int[prices.length];
        int[] stack = new int[prices.length];
        int top = -1;   // 栈空
        for(int i = 0; i < prices.length; i++){
            result[i] = prices[i];     // 先复制原价
            // 当前价格是栈顶商品右侧第一个小于或等于它的价格
            // 用 while 而不是 if
            while(top >= 0 && prices[i] <= prices[stack[top]]){
                int index = stack[top--];
                result[index] = prices[index] - prices[i];
            }
            stack[++top] = i;
        }
        return result;
    }

    public static void main(String[] args){
        int[] prices = {8, 4, 6, 2, 3};
        int[] result = finalPrices(prices);
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i]);
            if(i < result.length - 1){
                System.out.print(",");
            }
        }
    }
}
