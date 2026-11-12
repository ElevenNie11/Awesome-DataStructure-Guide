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
