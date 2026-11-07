package Stack.normal_stack;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Function_exe_time {
    public static int[] exclusiveTime(int n, List<String> logs){
        int[] result = new int[n];              // 用于存储每个函数的执行时间
        Stack<Integer> stack = new Stack<>();   // 保存函数调用顺序，栈顶就是当前正在执行的函数
        int previousTime = 0;

        for(int i = 0; i < logs.size(); i++){
            // 按照:分割字符串，parts[0]是函数id，parts[1]是start或end，parts[2]是时间戳
            String[] parts = logs.get(i).split(":");
            int functionId = Integer.parseInt(parts[0]);
            int timestamp = Integer.parseInt(parts[2]);

            if(parts[1].equals("start")){
                if(!stack.isEmpty()){
                    // 栈不为空，说明有新函数正在执行
                    // 计算当前函数的执行时间，减去上一个函数的结束时间
                    // peek()返回栈顶元素
                    result[stack.peek()] += timestamp - previousTime;
                }
                stack.push(functionId);   // 新函数入栈
                previousTime = timestamp;
            }else{
                // 弹出结束的函数（在栈顶）
                result[stack.pop()] += timestamp - previousTime + 1;
                previousTime = timestamp + 1;
            }
        }
        return result;
    }

// 因为这两个时刻对栈顶函数的处理不同：
// 遇到 start：当前栈顶函数只是暂时被新函数打断，之后还要继续执行，所以用 peek() 查看它并结算时间，但保留在栈中，然后把新函数 push 到栈顶
// 遇到 end：栈顶函数已经执行完了，不会继续执行，所以用 pop() 结算时间的同时将它移出栈，如果有调用它的外层函数，外层函数就重新成为栈顶


    public static void main(String[] args){
        List<String> logs = new ArrayList<>();
        logs.add("0:start:0");
        logs.add("1:start:2");
        logs.add("1:end:5");
        logs.add("0:end:6");
        int[] result = exclusiveTime(2, logs);
        System.out.println("[" + result[0] + "," + result[1] + "]");
    }
}
