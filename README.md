# 数据结构
## 每个文件夹里含markdown格式的笔记和说明须知
### [BeforeStartYouBetterRead.md](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/blob/main/BeforeStartYouBetterRead.md)
### 从易到难，覆盖所有数据结构：顺序表/栈/队列/树/图/哈希...
#### 正在更新ing

---

### Java里的源码根目录
<img width="721" height="236" alt="image" src="https://github.com/user-attachments/assets/b6704992-18f6-4c93-a38a-dd81ad014a40" />

如果在写`package Graph;`，而编辑器报错的时候，通常是由于Java Projects面板把Graph文件夹标成了源码根目录

因此`Graph/DepthFirstSearch.java`在Java看来位于源码根目录的顶层，属于默认包，预期包名就是 ""；但文件里声明了`package Graph;`，所以才会报错

#### 解决方案：如果想保留`package Graph;`，应把Graph的上一级项目目录设为源码根目录，让Graph成为包目录
把项目根目录设为源码根目录，Graph就会作为包目录，**设置`java.project.sourcePaths为["."]`**即可，具体步骤如下：
1. `Ctrl+Shift+P`，运行`Preferences: Open Workspace Settings (JSON)`
2. 在`.vscode/settings.json`中加入：
   ````
   {
     "java.project.sourcePaths": ["."]
   }
   ````
   如果文件里已有其他设置，把这一项加进去，并保留原有内容
3. 保存后按`Ctrl+Shift+P`，运行`Java: Clean Java Language Server Workspace`，再选择重载窗口
4. 这样一来，项目根目录下的Graph文件夹会被识别为包目录，文件中的`package Graph;`就和路径对应了
