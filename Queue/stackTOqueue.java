package Queue;
import java.util.Stack;

class Dueue{
    private Stack<Integer> inStack;  // inStack 接收新元素
    private Stack<Integer> outStack; // outStack 负责取出队首元素

    public Dueue(){
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x){
        inStack.push(x);
    }

    public int pop(){
        // 只有取队首的栈为空时，才把元素从输入栈倒过来
        if(outStack.isEmpty()){
            while(!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

    public int peek(){
        if(outStack.isEmpty()){
            while(!inStack.isEmpty()){
                outStack.push(inStack.pop());
            }
        }
        return outStack.peek();
    }

    public boolean isEmpty(){
        return inStack.isEmpty() && outStack.isEmpty();
    }
}