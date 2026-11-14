````java
        // 优先队列按距离从小到大取出顶点，队列元素为{顶点编号, 当前距离}
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        priorityQueue.offer(new int[]{startVertex, 0});     // 将起点加入优先队列

        // 每次取出当前距离起点最近且尚未确定的顶点
        while(!priorityQueue.isEmpty()){
            int[] current = priorityQueue.poll();
            int currentVertex = current[0];                 // 当前顶点编号
            int currentDistance = current[1];                // 入队时记录的距离

            // 距离已被更新时，队列中的旧记录作废，跳过
            if(currentDistance != distance[currentVertex] || visited[currentVertex]){
                continue;
            }
            // 取出后标记为已访问，该顶点的最短距离已经确定
            visited[currentVertex] = true;

            // 又以该顶点为基点，更新其所有邻接点的距离
            for(int j = 0; j < vertexCount; j++){
                // 已确定的点跳过；没有边的点也跳过，避免 inf 相加溢出
                if(visited[j] || weight[currentVertex][j] == inf){
                    continue;
                }
                // 避免距离相加超过int可表示范围
                if(currentDistance > inf - weight[currentVertex][j]){
                    continue;
                }
                int newDistance = currentDistance + weight[currentVertex][j];
                if(newDistance < distance[j]){
                    distance[j] = newDistance;
                    predecessor[j] = currentVertex;        // 记下j的前驱是currentVertex，方便回溯路径
                    priorityQueue.offer(new int[]{j, newDistance}); // 距离变小后重新加入优先队列
                }
            }
        }
````