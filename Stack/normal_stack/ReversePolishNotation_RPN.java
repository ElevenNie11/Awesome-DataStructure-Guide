package Stack.normal_stack;
import java.util.Stack;
// 逆波兰表达式求值
// 程序从左到右读取表达式：数字入栈；遇到运算符时，弹出右操作数和左操作数
// 计算后将结果重新入栈
// 最后栈顶就是表达式的值
public class ReversePolishNotation_RPN {
    public static int evalRPN(String[] tokens){
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < tokens.length; i++){
            String token = tokens[i];
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
                int right = stack.pop();
                int left = stack.pop();
                if(token.equals("+")){
                    stack.push(left + right);
                }else if(token.equals("-")){
                    stack.push(left - right);
                }else if(token.equals("*")){
                    stack.push(left * right);
                }else{
                    stack.push(left / right);
                }
            }else{
                stack.push(Integer.parseInt(token));  // 遇到数字时，parseInt 把字符串转成整数，再用 push 压入栈
            }
        }
        return stack.pop();  // 弹出最终栈顶值
    }

    public static void main(String[] args){
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(evalRPN(tokens));
    }
}
