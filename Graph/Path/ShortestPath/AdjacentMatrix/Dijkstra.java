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


        // 【重要逻辑】：建议搭配 Dijkstra-note.md 中的步骤来，结合着看会更易懂些
        for(int i = 0; i < vertexCount; i++){
            int minIndex = -1;
            int minDistance = inf;
            for(int j = 0; j < vertexCount; j++){
                if(!visited[j] && distance[j] < minDistance){
                    minIndex = j;
                    minDistance = distance[j];
                }
            }
            if(minIndex == -1) break;
            visited[minIndex] = true;
            for(int k = 0; k < vertexCount; k++){
                if(visited[k] || weight[minIndex][k] == inf){
                    continue;
                }
                if(distance[minIndex] + weight[minIndex][k] < distance[k]){
                    distance[k] = distance[minIndex] + weight[minIndex][k];
                    predecessor[k] = minIndex;
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
