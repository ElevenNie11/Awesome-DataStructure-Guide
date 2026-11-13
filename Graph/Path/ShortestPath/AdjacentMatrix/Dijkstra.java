package Graph.Path.ShortestPath.AdjacentMatrix;

// @Algorithm: 最短路径-迪杰斯特拉Dijkstra算法
// @Author: Shiyi Nie (ElevenNie11)
// @Date: 2026-11-13
// @Way: AdjacentMatrix（邻接矩阵）

public class Dijkstra {
    private static final int inf = Integer.MAX_VALUE;  // 代表“无限”
    // 用迪杰斯特拉计算最短路径方法
    public static void dijkstra(String[] vertices, int[][]weight, int startVertex){
        int vertexCount = vertices.length;                  // 顶点总个数
        int[] distance = new int[vertexCount];              // 顶点与其邻接点之间的距离
        int[] predecessor = new int[vertexCount];           // 前驱、前辈顶点
        boolean[] visited = new boolean[vertexCount];       // 记录某顶点是否被访问过

        // 初始化所有距离和前驱节点
        for(int i = 0; i < vertexCount; i++){
            distance[i] = inf;      // 最开始距离都在无穷远处 
            predecessor[i] = -1;    // 最开始前驱都为空，用负数代表empty
        }
        distance[startVertex] = 0;  // 起始顶点距离为0

        // 【重要】：每次选出距离起点最近且尚未确定的顶点
        for(int i = 0; i < vertexCount; i++){
            // 在尚未确定的顶点里，找到距离起点最近的那个顶点
            int minIndex = -1;
            int minDistance = inf;
            for(int j = 0; j < vertexCount; j++){
                if(!visited[j] && distance[j] < minDistance){
                    minIndex = j;               // 记下这个顶点，别写成 j = minIndex
                    minDistance = distance[j];  // 顺便更新当前的最小值，下一轮才好比较
                }
            }
            // 选不出来就说明剩下的顶点是不可到达的
            if(minIndex == -1) break;
            // 选出来了，标记为已访问，该顶点的最短距离以后将不再被更新
            visited[minIndex] = true;

            // 又以该顶点为基点，更新其所有邻接点的距离
            for(int j = 0; j < vertexCount; j++){
                // 已确定的点跳过；没有边的点也跳过，避免 inf 相加溢出
                if(visited[j] || weight[minIndex][j] == inf){
                    continue;
                }
                if(distance[minIndex] + weight[minIndex][j] < distance[j]){
                    distance[j] = distance[minIndex] + weight[minIndex][j];
                    predecessor[j] = minIndex;      // 记下j的前驱是minIndex，方便回溯路径
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

        // 调用dijFun方法
        dijkstra(vertices, weight, 0);
    }
}
