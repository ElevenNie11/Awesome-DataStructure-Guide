# 红黑树 Red-Black Tree
## 红黑树是一种 Weakly Binary Search Tree  |  AVL树是一种 Strictly Binary Search Tree
![alt 红黑树介绍](images/红黑树介绍.jpg)

---

## 🚨【关键】Rotation
![alt 旋转](images/Rotation.jpg)

---

## 🚀插入insert
### 【总结】插入的4种情况
![alt 总结插入的4种情况](images/总结插入的4种情况.png)

---

## 💻【修复平衡】代码
#### 在修复过程中我们会使用到失衡节点的【uncle parent grandparent】
#### 修复操作有两个：【recolor和rotate】
#### 场景1：uncle是红的                      -> 只变色（recolor），冲突上移到祖父
#### 场景2：uncle是黑的，且是"折线"(triangle) -> 先旋转parent，把折线掰成直线（再进行场景3）
#### 场景3：uncle是黑的，且是"直线"(line)     -> 先旋转祖父(rotate)，再变色(recolor)
#### 🚨【注意】：场景2和场景3是连招，掰直之后必须接着做场景3
````java
public class FixViolation {
    public static TreeNode fixViolation(TreeNode root, TreeNode node){
        while(node != root && getColor(node.parent) == COLOR.red){
            TreeNode parent = node.parent;
            TreeNode grandparent = parent.parent;
            TreeNode uncle;

            if(parent == grandparent.left){
                uncle = grandparent.right;
                if(getColor(uncle) == COLOR.red){
                    parent.color = COLOR.black;
                    uncle.color = COLOR.black;
                    grandparent.color = COLOR.red;
                    node = grandparent;
                }else{
                    if(node == parent.right){
                        node = parent;
                        root = leftRotate(node, root);
                        parent = node.parent;
                        grandparent = parent.parent;
                    }
                    root = rightRotate(grandparent, root);
                    parent.color = COLOR.black;
                    grandparent.color = COLOR.red;
                }
            }else{
                uncle = grandparent.left;
                if(getColor(uncle) == COLOR.red){
                    parent.color = COLOR.black;
                    uncle.color = COLOR.black;
                    grandparent.color = COLOR.red;
                    node = grandparent;
                }else{
                    if(node == parent.left){
                        node = parent;
                        root = rightRotate(node, root);
                        parent = node.parent;
                        grandparent = parent.parent;
                    }
                    root = leftRotate(grandparent, root);
                    parent.color = COLOR.black;
                    grandparent.color = COLOR.red;
                }
            }
        }
        return root;
    }
}


// 疑问：
        if(node == parent.right){
            node = parent;
            root = leftRotate(node, root);
            parent = node.parent;
            grandparent = parent.parent;
        }
// 可以改成这样吗？↓ （不可以）
        if(node == parent.right){
            root = leftRotate(parent, root);
            parent = node;
            grandparent = parent.parent;
        }
// 因为：
        while(node != root && getColor(node.parent) == COLOR.red)
        // 两种写法中node.parent都会随着旋转变化，但node指向的节点不同，所以循环检查的父节点也不同
````
````
        G                   G
       /                   /
      P          →        N  
       \                 /
        N               P
````
- `N.parent` 从 P 变成 G
- 正确写法先执行`node = parent`，旋转后`node.parent`即：`P.parent`是N
- 错误写法保留了`node = N`，`node.parent`即：`N.parent` 会变成 G 原来的父节点
于是下一轮`while`检查的对象不同：正确写法检查`P.parent`，错误写法检查`N.parent`