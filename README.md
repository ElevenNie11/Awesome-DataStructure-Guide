# 数据结构 DataStructure
## 每个文件夹里含markdown格式的笔记和说明须知
### [BeforeStartYouBetterRead.md](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/blob/main/BeforeStartYouBetterRead.md)
### 从易到难，覆盖所有数据结构：顺序表/栈/队列/树/图/哈希...
- 递归思想：[阶乘](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/blob/main/Recursion/factorial.java) / [笔记](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/blob/main/Recursion/recursion-note.md)
- 树：[深度优先搜算（前中后序遍历）](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/tree/main/Tree/BinaryTree/AwesomeTraversal) / [二叉搜索树BST](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/blob/main/Tree/BinaryTree/Awesome01/checkBST.java) / [BinaryTreeNote知识笔记](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/blob/main/Tree/BinaryTree/BinaryTreeGuide.md)
- 图：[深度优先搜索（显式栈）](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/tree/main/Graph/DFS_stack) / [深度优先搜索（递归）](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/tree/main/Graph/DFS_recursive) / [广度优先搜索]() / [最小生成树（prime算法&kruskal算法）](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/tree/main/Graph/minimun_spanning_tree) / [拓扑排序（kahn算法）](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/tree/main/Graph/TopologicalSort) / [最短路径（Dijkstra算法-优先队列/邻接矩阵）](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/tree/main/Graph/Path/ShortestPath) / [关键路径](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/tree/main/Graph/Path/CriticalPath) / [GraphTheoryNote知识笔记](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/blob/main/Graph/GraphGuide.md)
- 栈：[单调栈](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/tree/main/Stack)
- 队列：[leetcode题目集合](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/tree/main/Queue)
- 顺序表：[leetcode题目集合](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/tree/main/SequenceList)

#### 正在更新ing

---

### Java里的源码根目录
<img width="721" height="236" alt="image" src="https://github.com/user-attachments/assets/b6704992-18f6-4c93-a38a-dd81ad014a40" />

如果在写`package Graph;`，而编辑器报错的时候，通常是由于Java Projects面板把Graph文件夹标成了源码根目录

因此`Graph/DepthFirstSearch.java`在Java看来位于源码根目录的顶层，属于默认包，预期包名就是 ""；但文件里声明了`package Graph;`，所以才会报错

#### ✅解决方案一：如果想保留`package Graph;`，应把Graph的上一级项目目录设为源码根目录，让Graph成为包目录
把项目根目录设为源码根目录，Graph就会作为包目录，**设置`java.project.sourcePaths为["."]`**即可，具体步骤如下：

直接在根目录里建`.vscode`文件夹，并且新增文件`.settings.json`

填入代码：
````
   {
     "java.project.sourcePaths": ["."]
   }
````

<img width="493" height="191" alt="image" src="https://github.com/user-attachments/assets/92d957d9-900a-4645-b36e-fda6ccd3b652" />

按`Ctrl+Shift+P`打开命令面板

输入`Java: Clean Java Language Server Workspace`，点击出现的同名命令

<img width="440" height="134" alt="image" src="https://github.com/user-attachments/assets/6b2d7df7-fbf5-42d6-9d18-8469c2152b8e" />

如果弹出确认提示，选择`Restart and delete`（重启并清理工作区缓存）

<img width="497" height="158" alt="image" src="https://github.com/user-attachments/assets/ec3b6e1e-9ab0-48f7-82d9-5d9c00b0bd65" />

如果没有自动重新加载，再按`Ctrl+Shift+P`，输入并运行`Developer: Reload Window`

#### ✅解决方案二：直接删除
- 在资源管理器中右键Recursion文件夹，运行`Java: Remove Folder from Java Source Path`
<img width="684" height="492" alt="image" src="https://github.com/user-attachments/assets/3a161b0f-24cd-4afb-ba75-69eabd6b02db" />

- 再右键项目根目录Awesome-DataStructure-Guide，运行`Java: Add Folder to Java Source Path`
