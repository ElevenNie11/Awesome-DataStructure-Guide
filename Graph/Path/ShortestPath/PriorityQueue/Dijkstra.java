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
        // 优先队列中的元素为{顶点编号, 起点到该顶点的当前距离}
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        priorityQueue.offer(new int[]{startVertex, 0});  // 从起点开始搜索

        // 每次取出当前距离起点最近的顶点
        while(!priorityQueue.isEmpty()){
            int[] current = priorityQueue.poll();
            int currentVertex = current[0];
            int currentDistance = current[1];

            // 如果该记录已经过期，说明此顶点后来发现了更短的距离
            if(currentDistance != distance[currentVertex] || visited[currentVertex]){
                continue;
            }
            visited[currentVertex] = true;

            // 尝试经过当前顶点到达其他顶点，更新更短的距离
            for(int i = 0; i < vertexCount; i++){
                if(visited[i] || weight[currentVertex][i] == inf){
                    continue;
                }
                if(currentDistance > inf - weight[currentVertex][i]){
                    continue;
                }
                int newDistance = currentDistance + weight[currentVertex][i];
                if(newDistance < distance[i]){
                    distance[i] = newDistance;
                    predecessor[i] = currentVertex;
                    priorityQueue.offer(new int[]{i, newDistance});
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

        // 调用dijFun方法
        dijkstra(vertices, weight, 0);
    }
}
