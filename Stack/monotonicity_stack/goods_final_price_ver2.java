package Stack.monotonicity_stack;

public class goods_final_price_ver2 {
    public static int[] finalPrices(int[] prices){
        int[] result = new int[prices.length];    // 折扣价格
        int[] stack = new int[prices.length];     // 商品下标（非价格）
        int top = -1;                             // 栈空
        for(int i = 0; i < prices.length; i++){
            result[i] = prices[i];                // 先复制原价
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
