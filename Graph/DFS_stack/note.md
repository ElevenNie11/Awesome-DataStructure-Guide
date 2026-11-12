# 用显式栈实现图的DFS笔记总结

## 1. 顶点类`vertex<T>`
````
class vertex<T> {
    private final T data;
    private boolean visited;
    private List<vertex<T>> neighbors = new ArrayList<>();
    ...
}
````
每个`vertex`对象代表图中的一个顶点：
- data：顶点保存的数据，例如 "A"，`final`表示构造后不能再给它赋新值
- visited：布尔值记录DFS是否访问过这个顶点，Java会把它默认初始化为false
- neighbors：保存这个顶点的邻接点，例如A与B、C相连，A的邻接表就是[B, C]

#### 构造方法和取值方法
````
vertex(T data){
    this.data = data;
}

T getData(){
    return data;
}
````
`vertex(T data)`创建顶点，并把传入的数据存进data

`getData()`返回顶点中保存的数据，**返回类型也是T**

#### 添加无向边
````
void connect(vertex<T> other){
    neighbors.add(other);
    other.neighbors.add(this);
}
````
调用`a.connect(b)`会同时建立：
- A的邻接表包含B
- B的邻接表包含A
因此这表示一条无向边
如果图是有向图，通常只添加`neighbors.add(other)`，不添加反向关系

---

## 2. 用栈实现**DFS**
```
public static <T> void DFS(vertex<T> startVertex){
    if (startVertex == null) {
        return;
    }

    Deque<vertex<T>> stack = new ArrayDeque<>();
    stack.push(startVertex);
    ...
}
```
如果起点是null，就直接返回；否则创建一个栈，并把起点压入栈中

虽然这里的类型叫**Deque**，但它支持push（入栈）和pop（出栈），所以可以按栈的方式使用，`ArrayDeque` 是它的**实现类**

````
while(!stack.isEmpty()){
    vertex<T> curr = stack.pop();

    if(curr.isVisited()){
        continue;
    }

    curr.setVisited(true);
    System.out.print(curr.getData() + " ");
    ...
}
````

只要栈不为空，就不断取出栈顶顶点：
- 如果它已经访问过，就跳过（`continue`）
- 否则标记为已访问，并打印它保存的数据
一个顶点可能因为图中的多条边而被重复压入栈，所以需要检查`visited`，避免重复处理

````
List<vertex<T>> neighbors = curr.getNeighbors();

for(int i = neighbors.size() - 1; i >= 0; i--){
    vertex<T> neighbor = neighbors.get(i);
    if (!neighbor.isVisited()) {
        stack.push(neighbor);
    }
}
````

##### 随后把当前顶点**尚未访问的邻居压入栈**，邻居从后往前压（i从`neighbors.size() - 1`开始，然后`i--`），是为了配合栈的“后进先出”：这样邻接表中靠前的邻居会先出栈、先访问

---

## 3. Java中的泛型 T 是什么
T是一个类型参数，可以理解为“稍后指定的数据类型”，这样就可以允许任何类型的object了

````
class vertex<T>
````

这声明了一个**泛型类**，创建对象时指定T的实际类型：
````
vertex<String> a = new vertex<>("A");
vertex<Integer> number = new vertex<>(42);
````
【**请注意**】：
- 泛型类型参数**不能**直接使用基本类型
- 例如不能写 vertex<int>；应使用包装类`Integer`、`String`、`Long`等

#### Q: 为什么**DFS方法**也要写<T>?
````
public static <T> void DFS(vertex<T> startVertex)
````
- 这里的T是这个静态方法自己声明的类型参数，意思是：DFS可以接收任意数据类型的顶点
- 它和`class vertex<T>`中的T不共享作用范围，**静态方法不能直接依赖某个对象的类型参数，因此要在方法签名里单独声明<T>**
- 调用时，Java会从参数推断T应该是什么数据类型：`DFS(a);`因为a是是`vertex<String>`，所以这次调用中T的类型就是String