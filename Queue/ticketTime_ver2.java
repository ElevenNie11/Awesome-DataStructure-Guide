package Queue;

public class ticketTime_ver2 {
    public static int timeRequiredToBuy(int[] tickets, int k) {
        int time = 0;
        int len = tickets.length;
        for(int i = 0; i < len; i++){
            if(i <= k){
                time += Math.min(tickets[i], tickets[k]);
            }else{
                // 排在第 k 个人之后的顾客只会贡献 tickets[k] - 1 次买票的时间，因为当第 k 个人买完最后一张票后，程序就得到了结果
                // 每轮排在第 k 个人之后。第 k 个人买完最后一张时，这些人还没轮到，所以最多只能买 tickets[k] - 1 张
                time += Math.min(tickets[i], tickets[k] - 1);
            }
        }
        return time;
    }

    public  static void main(String[] args){
        int[] tickets = {2, 3, 2};
        int k = 2;
        int result = timeRequiredToBuy(tickets, k);
        System.out.println(result);
    }
}
