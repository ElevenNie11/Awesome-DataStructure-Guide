package Graph.Path.ShortestPath.PriorityQueue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// 【注意】：本程序不实现迪杰斯特拉算法
// 只是让学习者体会【优先队列】和【普通队列】的不同
// - 普通队列按**进入顺序**取出：先进先出（FIFO）
// - 优先队列按**优先级**取出：优先级最高的先出来，不保证按进入顺序
// @Atuhor: Shiyi Nie(ElevenNie11)
// @Date: 2026-11-14

public class PriorityQueue_StudyCode {
    public static void main(String[] args) {
        // 普通队列：先进先出
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(3);     // 把元素加入队尾
        queue.offer(1);
        queue.offer(2);
        System.out.print("普通队列: ");  // 示例输出：3 1 2
        while(!queue.isEmpty()){
            System.out.print(queue.poll() + " ");  // 取出队头元素
        }

        // 优先队列：假设优先级为“每次先取出队列中数值最小的元素”
        // 【Java的PriorityQueue默认按元素的自然顺序排列】
        // 按优先级取出
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(2);

        System.out.print("\n优先队列: ");  // 示例输出：1 2 3
        while(!priorityQueue.isEmpty()){
            System.out.print(priorityQueue.poll() + " ");
        }
    }
}
