package Graph.Path.ShortestPath.PriorityQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// @Algorithm: 最短路径-迪杰斯特拉Dijkstra算法
// @Author: Shiyi Nie (ElevenNie11)
// @Date: 2026-11-13
// @Way: PriorityQueue（优先队列）

public class Dijkstra {
    private static final int inf = Integer.MAX_VALUE;  // 代表“无限”

    // 邻接表里的一条边：终点编号 + 边权
    private static class Edge {
        int to;
        int weight;

        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    // 优先队列里的节点：顶点编号 + 起点到它的当前距离
    private static class Node implements Comparable<Node> {
        int vertex;
        int distance;

        Node(int vertex, int distance){
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other){
            // 距离小的排在前面；不用 this.distance - other.distance，防止 inf 相减溢出
            return Integer.compare(this.distance, other.distance);
        }
    }

    // 用优先队列（小顶堆）计算最短路径方法
    public static void dijkstra(String[] vertices, int[][] weight, int startVertex){
        int vertexCount = vertices.length;                  // 顶点总个数
        int[] distance = new int[vertexCount];              // 起点到各顶点的当前最短距离
        int[] predecessor = new int[vertexCount];           // 前驱、前辈顶点

        // 初始化所有距离和前驱节点
        for(int i = 0; i < vertexCount; i++){
            distance[i] = inf;      // 最开始距离都在无穷远处
            predecessor[i] = -1;    // 最开始前驱都为空，用负数代表empty
        }
        distance[startVertex] = 0;  // 起始顶点距离为0

        // 1. 把邻接矩阵转成邻接表，之后只遍历真正存在的边，不用再扫整行
        List<List<Edge>> graph = new ArrayList<>();
        for(int i = 0; i < vertexCount; i++){
            graph.add(new ArrayList<>());
            for(int j = 0; j < vertexCount; j++){
                // 对角线上的0是“自己到自己”，inf是“没有边”，都不算真正的边
                if(i != j && weight[i][j] != inf){
                    graph.get(i).add(new Edge(j, weight[i][j]));
                }
            }
        }

        // 2. 小顶堆：堆顶永远是当前距离最小的顶点，替代了矩阵版里找最小值的那层 for
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(startVertex, 0));

        while(!queue.isEmpty()){
            Node node = queue.poll();
            int u = node.vertex;

            // 同一个顶点可能被压进堆好几次，弹出的距离比记录的大说明是过期数据，直接丢掉
            if(node.distance > distance[u]){
                continue;
            }

            // 3. 以u为中转点，松弛它的所有邻接点
            for(Edge edge : graph.get(u)){
                int newDistance = distance[u] + edge.weight;
                if(newDistance < distance[edge.to]){
                    distance[edge.to] = newDistance;
                    predecessor[edge.to] = u;                       // 记下前驱，方便回溯路径
                    queue.offer(new Node(edge.to, newDistance));    // 只有距离变短了才入堆
                }
            }
        }

        // 4. 输出起点到各个顶点的最短距离
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
