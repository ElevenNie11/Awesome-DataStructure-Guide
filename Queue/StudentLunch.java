package Queue;

// 无法吃午餐的学生数量
public class StudentLunch {
    public static int countStudents(int[] students, int[] sandwiches){
        int[] likeCnt = new int[2];    // 统计喜欢每种三明治的学生数量（只有两种三明治，所以数组可以只有两个元素）
        // 统计喜欢每种三明治的学生数量
        // 元素要么0 要么1
        // 喜欢方形三明治的有2人
        // 喜欢圆形三明治的有2人
        for(int i = 0; i < students.length; i++){
            likeCnt[students[i]]++;
        }
        // 按照三明治栈顶顺序分发
        for(int sandwich : sandwiches){
            if(likeCnt[sandwich] == 0){
                break;
            }
            likeCnt[sandwich]--;
        }
        return likeCnt[0] + likeCnt[1];
    }

    public static void main(String[] args){
        int[] students = {1, 1, 0, 0};
        int[] sandwiches = {0, 1, 0, 1};
        int result = countStudents(students, sandwiches);
        System.out.println(result);
    }
}

// 本题目虽然是队列的题目，只是队列的思想，但是没有队列的操作
// 队列里学生会轮流到队首，如果当前学生不喜欢栈顶三明治，他会回到队尾
// 继续轮转后，只要队伍里有人喜欢这个三明治，那个人最终就会轮到队首并拿走它
// 因此，拿走一份三明治只需把对应人数减一