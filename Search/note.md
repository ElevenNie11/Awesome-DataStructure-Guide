# 数据结构 - 查找Search 【静态查找 + 动态查找】

## #Chapter01.【静态查找】-顺序查找-LinearSearch
遍历数组，找到目标值，返回下标

---

## #Chapter02.【静态查找】-二分查找-BinarySearch
### 时间复杂度-TimeComplexity
![alt 二分查找的时间复杂度](BinaryTreeTimeComplexity.png)
### 二分查找需要具备的【条件】-Conditions
- RandomAccess.(Array✅ | LinkedList❌)
- The elements must be sorted.
  - `1 10 15 21 34 52 60 71 83` ✅
  - `1 15 10 83 34 52 71 50 21` ❌
### Q：中间位置的下标是怎么计算的？数组中有奇数个元素时下标怎么计算？有偶数个元素时下标又是怎么计算的？
````java
int mid = left + (right - left) / 2;
// 中间位置通常写成：
int mid = (left + right) / 2;
// 为了避免 left + right 相加得到的整数太大而溢出，把它改写成等价形式：
int mid = left + (right - left) / 2; ✅
// 推演过程：
(left + right) / 2
= (left + right - left + left) / 2
= (2 * left + right - left) / 2
= left + (right - left) / 2
// 当然如果确保 left + right 不会溢出，也可以用 mid = (left + right) / 2;
````
#### Java的整数除法会舍去小数部分，所以当查找范围有偶数个元素时，【取中间偏左】的那个位置
- **奇数**个元素：例如下标 0 到 4，共有 5 个元素，`mid = 0 + (4 - 0) / 2 = 2`，取正中间的下标 2
- **偶数**个元素：例如下标 0 到 3，共有 4 个元素，`mid = 0 + (3 - 0) / 2 = 1`，取偏左的中间下标 1

---

## #Chapter03.【动态查找】-二叉搜索树-BinarySearchTree
### BST可用操作：查找Search / 插入Insert / 删除Delete
#### 【删除】操作的详细笔记可见👉[delete-note](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/blob/main/Search/BinarySearchTree/delete-note.md)
也叫“二叉查找树”
![alt 二叉查找树](BinarySearchTree.jpg)

---

## #Chapter04.【动态查找】-平衡二叉树-BalancedBinaryTree
- AVL树（查找/插入/删除）👉 [AVL树](https://github.com/ElevenNie11/Awesome-DataStructure-Guide/tree/main/Search/BalancedBinaryTree/AVLTree)
- 红黑树（查找/插入/删除）👉[红黑树]()
![alt AVL树vs红黑树](AVL树vs红黑树.png)

## ✅旋转Rotation-AVL树和红黑树的辅助函数的差别
### 01.AVL树
````java
    // 辅助函数04：左旋
    public static TreeNode leftRotate(TreeNode root){
        TreeNode newRoot = root.right;
        TreeNode rotateNode = newRoot.left;     

        newRoot.left = root;
        root.right = rotateNode;    

        updateHeight(root);         // 先更新原根节点的高度
        updateHeight(newRoot);      // 再更新新根节点的高度

        return newRoot;
    }
````
---
### 02.红黑树
````java
    // 辅助函数02：左旋
    public static TreeNode leftRotate(TreeNode root, TreeNode node){
        TreeNode newRoot = node.right;  // newRoot是我们需要旋转的节点的右孩子
        // 因为newRoot的左孩子一定大于我们需要旋转的节点但是小于newRoot，所以要到以newRoot为根的左子树去
        TreeNode rotateNode = newRoot.left;

        node.right = rotateNode;
        if(rotateNode != null){
            rotateNode.parent = node;
        }
        newRoot.parent = node.parent;
        if(node.parent == null){
            root = newRoot;
        }else if(node == node.parent.left){
            node.parent.left = newRoot;
        }else{
            node.parent.right = newRoot;
        }
        newRoot.left = node;
        node.parent = newRoot;
        return root;
    }
````