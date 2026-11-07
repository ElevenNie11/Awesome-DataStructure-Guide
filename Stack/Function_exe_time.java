package Stack;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Function_exe_time {
    public static int[] exclusiveTime(int n, List<String> logs){
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        int previousTime = 0;
        for(int i = 0; i < logs.size(); i++){
            String[] parts = logs.get(i).split(":");
            int functionId = Integer.parseInt(parts[0]);
            int timestamp = Integer.parseInt(parts[2]);
            if(parts[1].equals("start")){
                if(!stack.isEmpty()){
                    result[stack.peek()] += timestamp - previousTime;
                }
                stack.push(functionId);
                previousTime = timestamp;
            }else{
                result[stack.pop()] += timestamp - previousTime + 1;
                previousTime = timestamp + 1;
            }
        }
        return result;
    }

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
