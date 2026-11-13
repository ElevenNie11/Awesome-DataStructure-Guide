# 最短路径：Dijkstra's Algorithm 迪杰斯特拉算法

## 1. 什么是最短路径

在**带权图**中，从一个指定起点到其他顶点，沿边权总和最小的路径称为最短路径

【注意】：这里的“距离”指的是边权之和，不一定代表几何上的直线距离

迪杰斯特拉（Dijkstra）算法解决的是**单源最短路径**问题：给定一个起点，一次计算它到图中其他顶点的最短距离

它适用于**边权非负**的有向图和无向图并且图中可以有无法从起点到达的顶点

---

## 2. 核心思路

算法维护每个顶点到起点的当前最短距离 `distance`，以及最短路径上的前驱顶点 `predecessor`

- step1: Mark all nodes as unvisited.
- step2: Assign to all nodes a tentative distance value.
- **step3: For the current node,calculate the distance to all unvisited neighbors + Update shortest distance if new distance is shorter than old distance.**
- step4: Mark current node as visited.
- **step5: Choose new current node from unvisited nodes with minimal distance.**

### 由于边权不能为负，一旦取出当前距离最小的顶点，就不可能再经由其他未确定顶点找到更短的路线，因此可以安全地确定它的最短距离

---

## 3. 代码实现迪杰斯特拉算法的两种方式
### AdjacentMatrix邻接矩阵
### PriorityQueue优先队列（最常用）

---

## 4. 迪杰斯特拉算法的限制

- **不能处理负权边**（存在负权边时，应考虑其他最短路径算法）
- `inf` 表示没有边或暂时不可达，**不要**把它当作一个真实边权
- **无向图的邻接矩阵是对称的**，即 `weight[u][v] == weight[v][u]`；有向图则按方向填写
- 单源最短路径与最小生成树是不同问题：前者最小化从起点出发的路径长度，后者最小化连接所有顶点的边权总和
