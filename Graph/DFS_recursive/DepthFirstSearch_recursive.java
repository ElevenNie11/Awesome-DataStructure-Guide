package Graph.DFS_recursive;
import java.util.ArrayList;
import java.util.List;

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

public class DepthFirstSearch_recursive {
    // 用递归实现DFS
    // 【递归会先深入当前邻居能到达的路径，直到走不下去，再返回上一层继续检查其他邻居】
    public static <T> void DFS(vertex<T> curr){
    if(curr == null || curr.isVisited()){
        return;
    }

    // 先标记为true，避免无向图沿着边返回时陷入循环
    curr.setVisited(true);
    System.out.print(curr.getData() + " ");

    // 递归访问邻接点
    for(vertex<T> neighbor: curr.getNeighbors()){
        DFS(neighbor);
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
        // 示例输出：A B D E C F
    }
}
