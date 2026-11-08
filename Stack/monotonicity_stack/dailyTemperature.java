package Stack.monotonicity_stack;

public class dailyTemperature {
    public static int[] dailyTemperatures(int[] temperatures){
        int n = temperatures.length;
        int[] answer = new int[n];
        int[] stack = new int[n];   // 保存还没有遇到更高温度的日期下标
        int top = -1;

        for(int i = 0; i < n; i++){
            // 今天温度更高，为栈顶日期找到下一个更暖和的日子
            while(top >= 0 && temperatures[i] > temperatures[stack[top]]){
                int previousDay = stack[top--];
                answer[previousDay] = i - previousDay;
            }
            stack[++top] = i;
        }
        return answer;
    }

    public static void main(String[] args){
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] answer = dailyTemperatures(temperatures);

        System.out.print("[");
        for(int i = 0; i < answer.length; i++){
            System.out.print(answer[i]);
            if(i < answer.length - 1){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}
