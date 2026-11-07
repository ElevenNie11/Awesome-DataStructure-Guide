package Stack;
import java.util.ArrayList;
import java.util.List;

public class createArray {
    public static List<String> buildArray(int[] target, int n){
        List<String> operations = new ArrayList<>();
        int targetIndex = 0;
        for(int value = 1; value <= n && targetIndex < target.length; value++){
            operations.add("Push");
            if(value == target[targetIndex]){
                targetIndex++;
            }else{
                operations.add("Pop");
            }
        }
        return operations;
    }

    public static void main(String[] args){
        int[] target = {1, 3};
        int n = 3;
        System.out.println(buildArray(target, n));
    }
}
