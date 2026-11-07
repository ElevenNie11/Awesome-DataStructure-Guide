package Stack.normal_stack;

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

// 为什么不能写成 top-- 呢？
// 因为 top-- 是先使用 top 的值再减 1，而我们需要的是先减 1 再使用 top 的值，所以要写成 --top 

// data[--top] = value;   //先自减,再使用
// data[++top] = value;   //先自增,再使用

// data[top--] = value;   //先使用,再自减
// data[top++] = value;   //先使用,再自增