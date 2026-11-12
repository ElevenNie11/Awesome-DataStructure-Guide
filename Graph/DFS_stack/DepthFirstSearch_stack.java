package Graph.DFS_stack;
// ArrayList和List：用于顶点的邻接表，保存它相连的顶点
// ArrayDeque和Deque：用于DFS的栈，暂存待访问的顶点
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Deque;

class vertex<T>{
    private final T data;                                   // 保存顶点数据
    private boolean visited;                                // 标记是否已被visit
    private List<vertex<T>> neighbors = new ArrayList<>();  // 顶点的邻接点列表

    // setter函数
    vertex(T data){
        this.data = data;
    }

    // getter函数（返回值类型是T，泛型）
    T getData(){
        return data;
    }

    // 添加无向边（如果是有向图，就直接 neighbors.add(other); 即可）
    // vertex<String> a =  new vertex<>("A");
    // a.connect(b);
    // 这里的other参数接收的是b
    // this字段指的就是a
    void connect(vertex<T> other){
        neighbors.add(other);       // a的邻居是b
        other.neighbors.add(this);  // b的邻居也是a
    }

    // 用于查看该顶点是否被访问过（DFS中，每个顶点只能被访问一次）
    // boolean默认值是false
    boolean isVisited(){
        return visited;
    }

    // 如果某一个顶点被访问过了，就做一个标记
    void setVisited(boolean visited){
        this.visited = visited;
    }

    // 取邻接点列表的方法
    // 把当前顶点的【neighbors邻接表】返回给调用者
    // 这个方法返回的是原来的邻接表，不是副本，意味着调用者拿到列表后也能修改它
    List<vertex<T>> getNeighbors(){
        return neighbors;
    }
}


public class DepthFirstSearch_stack {
    // 一个静态的DFS函数
    // 方法参数里面要用泛型T 就必须在方法签名中显式写<T>
    // 意思是DFS方法【可以接收任意数据类型】的参数
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

            // 把刚才出栈的顶点的【邻居们】都压入栈中
            List<vertex<T>> neighbors = curr.getNeighbors(); // 获取顶点的邻接点列表
            // 倒序压栈，这样可以保证邻接表中靠前的邻居会先出栈、先访问
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

        // 调用DFS深度优先搜索函数
        DFS(a);
    }
}