package Stack;

public class ReversePolishNotation_PRN_ver2 {
    public static int evalRPN(String[] tokens){
        // 因为最多就 tokens.length 个数字，所以可以直接开个纯 int 数组当栈
        int[] stack = new int[tokens.length];
        int top = 0;    // 栈顶指针（top = -1 表示栈空）
        for(int i = 0; i < tokens.length; i++){
            String token = tokens[i];
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
                int right = stack[--top];   // 先弹出右操作数
                int left = stack[--top];    // 再弹出左操作数
                if(token.equals("+")){
                    stack[top++] = left + right;
                }else if(token.equals("-")){
                    stack[top++] = left - right;
                }else if(token.equals("*")){
                    stack[top++] = left * right;
                }else{
                    stack[top++] = left / right;
                }
             }else{
                stack[top++] = Integer.parseInt(token);
            }
        }
            return stack[0];
    }

    public static void main(String[] args){
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(evalRPN(tokens));
    }
}
