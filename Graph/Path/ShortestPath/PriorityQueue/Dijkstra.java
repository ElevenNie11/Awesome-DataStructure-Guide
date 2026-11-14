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

        // 优先队列按距离从小到大取出顶点，队列元素为{顶点编号, 当前距离}
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        priorityQueue.offer(new int[]{startVertex, 0});     // 将起点加入优先队列

        // 每次取出当前距离起点最近且尚未确定的顶点
        while(!priorityQueue.isEmpty()){
            int[] current = priorityQueue.poll();
            int currentVertex = current[0];                 // 当前顶点编号
            int currentDistance = current[1];                // 入队时记录的距离

            // 距离已被更新时，队列中的旧记录作废，跳过
            if(currentDistance != distance[currentVertex] || visited[currentVertex]){
                continue;
            }
            // 取出后标记为已访问，该顶点的最短距离已经确定
            visited[currentVertex] = true;

            // 又以该顶点为基点，更新其所有邻接点的距离
            for(int j = 0; j < vertexCount; j++){
                // 已确定的点跳过；没有边的点也跳过，避免 inf 相加溢出
                if(visited[j] || weight[currentVertex][j] == inf){
                    continue;
                }
                // 避免距离相加超过int可表示范围
                if(currentDistance > inf - weight[currentVertex][j]){
                    continue;
                }
                int newDistance = currentDistance + weight[currentVertex][j];
                if(newDistance < distance[j]){
                    distance[j] = newDistance;
                    predecessor[j] = currentVertex;        // 记下j的前驱是currentVertex，方便回溯路径
                    priorityQueue.offer(new int[]{j, newDistance}); // 距离变小后重新加入优先队列
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
        String[] vertices = {"A", "B", "C", "D", "E"};
        int[][] weight = {
            {0, 2, inf, 6, inf},
            {2, 0, 3, 8, 5},
            {inf, 3, 0, inf, 7},
            {6, 8, inf, 0, 9},
            {inf, 5, 7, 9, 0}
        };

        // 调用dijkstra方法
        dijkstra(vertices, weight, 0);
    }
}
