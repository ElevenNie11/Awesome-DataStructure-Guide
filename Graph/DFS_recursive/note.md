# 递归法
## 对于【递归】的学习可以看一看Recusion/recursion-note.md里关于递归的笔记

### DFS递归法
````
public static <T> void DFS(vertex<T> curr){
    // 递归的结束条件（base case）：顶点不存在，或已经访问过，就结束当前这次调用
    if(curr == null || curr.isVisited()){
        return;
    }

    // 先标记为true，避免无向图沿着边返回时陷入循环（无限递归）
    curr.setVisited(true);
    System.out.print(curr.getData() + " ");

    // 递归访问邻接点
    for(vertex<T> neighbor: curr.getNeighbors()){
        DFS(neighbor);
    }
}
````

````
    for(vertex<T> neighbor: curr.getNeighbors()){
        DFS(neighbor);
    }
````
#### 【深度优先】
- 依次递归访问当前顶点的所有邻居

- 递归会先沿着一个邻居继续深入：等那条路径上的顶点都处理完，才返回当前层，继续下一个邻居