package Recursion;

public class factorial {
    // 计算 n 个数的阶乘 n!
   public static int factorialFun(int n){
        if(n == 1) return 1;
        return n * factorialFun(n - 1);
   }

   // 测试阶乘
   public static void main(String[] args){
        System.out.print(factorialFun(3));
        System.out.print(factorialFun(6));
   }
}
