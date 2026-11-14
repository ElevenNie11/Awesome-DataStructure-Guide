package Graph.Path.ShortestPath.PriorityQueue;
import java.util.PriorityQueue;

// @Algorithm: 最短路径-迪杰斯特拉Dijkstra算法
// @Author: Shiyi Nie (ElevenNie11)
// @Date: 2026-11-13
// @Way: PriorityQueue（优先队列）

public class Dijkstra {
    public static final int inf = Integer.MAX_VALUE;
    // 用优先队列实现迪杰斯特拉计算最短路径方法
    public static void dijkstra(String[] vertices, int[][] weight, int startVertex){
        int vertexCount = vertices.length;                  // 顶点总个数
        int[] distance = new int[vertexCount];              // 起点到各个顶点的当前最短距离
        int[] predecessor = new int[vertexCount];           // 前驱、前辈顶点
        boolean[] visited = new boolean[vertexCount];       // 记录某顶点是否已确定最短距离

        // 初始化所有距离和前驱节点
        for(int i = 0; i < vertexCount; i++){
            distance[i] = inf;      // 最开始距离都在无穷远处
            predecessor[i] = -1;    // 最开始前驱都为空，用负数代表empty
        }
        distance[startVertex] = 0;  // 起始顶点距离为0

        
        // 【重要逻辑】
        // 优先队列按当前距离从小到大取顶点，发现更短路线时更新距离并重新入队
        // 优先队列中的元素为{顶点编号, 起点到该顶点的当前距离}
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        priorityQueue.offer(new int[]{startVertex, 0});  // 起始时，把起点从队尾加入，距离为0

        // 每次取出当前距离起点最近的顶点
        while(!priorityQueue.isEmpty()){
            int[] current = priorityQueue.poll();// poll：从队头取出元素
            // 队列里存的每一个元素都是一个数组，然后这个数组内部又有两个元素current[0]是顶点，current[1]是距离
            int currentVertex = current[0];
            int currentDistance = current[1];    

            // 此时的最短距离不等于队头的距离或者已经被访问过，就continue
            // 优先队列的队头【永远是当前的最短距离】
            if(currentDistance != distance[currentVertex] || visited[currentVertex]){
                continue;
            }
            visited[currentVertex] = true;

            // 尝试经过当前顶点到达其他顶点，更新更短的距离
            for(int i = 0; i < vertexCount; i++){
                // 这个if语句是在找邻居
                if(visited[i] || weight[currentVertex][i] == inf){
                    continue;
                }
                // 保证不溢出
                if(currentDistance + weight[currentVertex][i] > inf){
                    continue;
                }
                // 计算新的距离
                int newDistance = currentDistance + weight[currentVertex][i];
                // 检查新距离是否比之前记录过的最短距离还短
                if(newDistance < distance[i]){
                    distance[i] = newDistance;      // 更新当前最短距离
                    predecessor[i] = currentVertex; // 更新前驱节点
                    priorityQueue.offer(new int[]{i, newDistance}); // 将更新后的距离放进优先队列，算法之后就能按距离从小到大继续处理它
                }
            }
        }


        // 输出起点到各个顶点的最短距离
        for(int i = 0; i < vertexCount; i++){
            if(distance[i] == inf){
                System.out.println(vertices[startVertex] + " -> " + vertices[i] + " : 不可达");
            }else{
                System.out.println(vertices[startVertex] + " -> " + vertices[i] + " : " + distance[i]);
            }
        }

    }

    // 测试Dijkstra
    public static void main(String[] args){
        // 图的表示方法之一：邻接矩阵
        String[] vertices = {"A", "B", "C", "D", "E", "F"};
        int[][] weight = {
            {0, 2, inf, 8, inf, inf},
            {2, 0, inf, 5, 6, inf},
            {inf, inf, 0, inf, 9, 3},
            {8, 5, inf, 0, 3, 2},
            {inf, 6, 9, 3, 0, 1},
            {inf, inf, 3, 2, 1, 0}   
        };

        
        dijkstra(vertices, weight, 0);
    }
}
