package Queue;

// 买到第 k 个人所需的时间
public class ticketTime {
    public static int timeRequiredToBuy(int[] tickets, int k){
        int time = 0;
        int index = 0;
        // 按照队列顺序依次买票，买完后回到队尾
        while(tickets[k] > 0){
            if(tickets[index] > 0){
                tickets[index]--;
                time++;
            }
            // 轮到目标位置时，购买完成后直接返回
            if(index == k && tickets[k] == 0){
                return time;
            }
            index = (index + 1) % tickets.length;  // 循环队列
        }
        return time;
    }

    public static void main(String[] args){
        int[] tickets = {2, 3, 2};
        int k = 2;
        int result = timeRequiredToBuy(tickets, k);
        System.out.println(result);
    }
}
