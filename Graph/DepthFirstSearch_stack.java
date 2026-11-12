package Graph;
// ArrayList和List：用于顶点的邻接表，保存它相连的顶点
// ArrayDeque和Deque：用于DFS的栈，暂存待访问的顶点
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Deque;

class vertex<T>{
    private final T data;                                   // 保存顶点数据
    private boolean visited;                                // 标记是否已被visit
    private List<vertex<T>> neighbors = new ArrayList<>();  // 邻接点列表

    vertex(T data){
        this.data = data;
    }

    T getData(){
        return data;
    }

    // 添加无向边
    void connect(vertex<T> other){
        neighbors.add(other);
        other.neighbors.add(this);
    }

    boolean isVisited(){
        return visited;
    }

    void setVisited(boolean visited){
        this.visited = visited;
    }

    List<vertex<T>> getNeighbors(){
        return neighbors;
    }
}

public class DepthFirstSearch_stack {
    public static <T> void DFS(vertex<T> startVertex){
        if(startVertex == null){
            return ;
        }

        // 栈保存待访问的顶点
        Deque<vertex<T>> stack = new ArrayDeque<>();
        // 最初的起始顶点入栈
        stack.push(startVertex);

        // 最终栈空就代表着图的深度优先搜索（遍历traversal）结束
        while(!stack.isEmpty()){
            vertex<T> curr = stack.pop();    // 出栈加入结果
            // 每个顶点只能被访问一次
            // 同一个顶点可能被多个邻接点压入栈，只访问一次
            if(curr.isVisited()){
                continue;
            }
            curr.setVisited(true);
            System.out.print(curr.getData() + " ");

            // 邻接点列表
            List<vertex<T>> neighbors = curr.getNeighbors(); // 邻接点列表
            for(int i = neighbors.size() - 1; i >= 0; i--){
                vertex<T> neighbor = neighbors.get(i);
                if(!neighbor.isVisited()){
                    stack.push(neighbor);
                }
            }
        }
    }

    // 测试DFS
    public static void main(String[] args) {
        vertex<String> a = new vertex<>("A");
        vertex<String> b = new vertex<>("B");
        vertex<String> c = new vertex<>("C");
        vertex<String> d = new vertex<>("D");
        vertex<String> e = new vertex<>("E");
        vertex<String> f = new vertex<>("F");

        // 建立无向图
        a.connect(b);
        a.connect(c);
        b.connect(d);
        b.connect(e);
        c.connect(f);

        DFS(a);
    }
}