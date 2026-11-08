package Stack.monotonicity_stack;
// 数组模拟单调栈，为每件商品寻找右侧第一个价格小于或等于它的商品，并计算折后价
public class goods_final_price {
    public static int[] finalPrices(int[] prices){
        for(int i = 0; i < prices.length; i++){
            prices[i] = getPrice(i, prices[i], prices);
        }
        return prices;
    }

    public static int getPrice(int i, int price, int[] prices){
        for(int j = i + 1; j < prices.length; j++){
            if(prices[j] <= prices[i]){
                return price - prices[j];
            }
        }
        return price;
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
