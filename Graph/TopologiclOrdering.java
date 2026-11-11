package Graph;
import java.util.*;;

// 拓扑排序
public class TopologiclOrdering {

    // 使用Kahn算法进行拓扑排序
    // 边from -> to表示from必须排在to前面
    public static List<Integer> topologicalSort(int vertexCount, int[][] edges){
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[vertexCount];

        // 初始化邻接表
        for(int i = 0; i < vertexCount; i++){
            graph.add(new ArrayList<>());
        }

        // 建立图并统计每个顶点的入度
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            indegree[to]++;
        }

        // 将所有入度为0的顶点加入队列
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < vertexCount; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        // 依次取出入度为0的顶点
        while(!queue.isEmpty()){
            int current = queue.poll();
            result.add(current);

            // 删除当前顶点的出边并更新相邻顶点的入度
            for(int next : graph.get(current)){
                indegree[next]--;
                if(indegree[next] == 0){
                    queue.offer(next);
                }
            }
        }

        // 如果结果数量不足，说明图中存在环
        if(result.size() != vertexCount){
            throw new IllegalArgumentException("图中存在环，无法进行拓扑排序");
        }
        return result;
    }

    // 测试拓扑排序
    public static void main(String[] args){
        // 课程依赖关系
        int[][] edges = {
                {0, 2}, {1, 2}, {1, 3}, {2, 4}, {3, 4}, {4, 5}
        };

        List<Integer> result = topologicalSort(6, edges);
        System.out.println("拓扑排序结果：" + result);
    }
}
