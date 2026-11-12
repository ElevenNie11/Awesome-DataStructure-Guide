package Graph;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
// import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

// 2026-11-12 Shiyi Nie
// 【须知】
// 拓扑排序只适用于有向无环图（DAG），所以这里用addEdge方法添加单向边

// 声明并创建一个泛型类
class vertex<T>{
    final T data;
    final List<vertex<T>> neighbors = new ArrayList<>();

    vertex(T data){
        this.data = data;
    }

    // 添加有向边
    void addEdge(vertex<T> v){
        neighbors.add(v);
    }
}

public class TopologicaSort {
    // 拓扑排序kahn算法
    // 接收参数是？
    public static <T> List<vertex<T>> TopoSort(List<vertex<T>> vertices){
        // 统计【每个顶点的入度】indegree
        Map<vertex<T>, Integer> indegree = new HashMap<>();
        for(vertex<T> vertex: vertices){
            indegree.put(vertex, 0);
        }
        
        for(vertex<T> vertex: vertices){
            for(vertex<T> neighbor: vertex.neighbors){
                indegree.put(neighbor, indegree.get(neighbor) + 1);
            }
        }

        // 把所有入度为0的顶点放进队列queue
        Queue<vertex<T>> queue = new ArrayDeque<>();
        for(vertex<T> vertex: vertices){
            if(indegree.get(vertex) == 0){
                queue.offer(vertex);
            }
        }

        List<vertex<T>> result = new ArrayList<>();

        while(!queue.isEmpty()){
            vertex<T> curr = queue.poll();
            result.add(curr);
            // 【重要】：删除curr发出的边，并更新邻接点的入度
            for(vertex<T> neighbor: curr.neighbors){
                int newIndegree = indegree.get(neighbor) - 1;
                indegree.put(neighbor, newIndegree);
                // 入度减到0，说明现在可以处理它了
                if(newIndegree == 0){
                    // 入队
                    queue.offer(neighbor);
                }
            }
        }

        // 如果没有处理完全部顶点，就说明这不是无向图，图中存在环
        if(result.size() != vertices.size()){
            // 抛出参数异常
            throw new IllegalArgumentException("NOT DAG! CAN NOT BE TOPOLOGICAL SORT.");
        }

        // 返回最终排序结果给调用者
        return result;
    }

    // 测试拓扑排序
    public static void main(String[] args){
        vertex<String> a = new vertex<>("A");
        vertex<String> b = new vertex<>("B");
        vertex<String> c = new vertex<>("C");
        vertex<String> d = new vertex<>("D");
        
        // A、B必须先于C，C必须先于D
        a.addEdge(c);
        b.addEdge(c);
        c.addEdge(d);

        // 创建一个包含a, b, c, d的列表
        // 必须创建，因为要传参给TopoSort方法
        List<vertex<String>> vertices = List.of(a, b, c, d);

        // 调用TopoSort方法
        // resultSort接收方法返还的结果
        List<vertex<String>> resultSort = TopoSort(vertices);

        // 打印输出
        for(vertex<String> vertex: resultSort){
            System.out.print(vertex.data + " ");
        }
        // 示例输出：A B C D
    }
}