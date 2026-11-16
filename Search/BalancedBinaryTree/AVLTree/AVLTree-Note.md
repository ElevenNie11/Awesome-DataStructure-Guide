# 平衡二叉树（又称：AVL树）
# 【Properties】: BST + Balance Factor ∈ [-1, 0, 1]
## AVL树的2个【必备概念】：树高TreeHeight + 平衡因子BalanceFactor
![alt AVL Tree](AVLTree.jpg)

---

## 4种【不平衡】情况以及解决方案
### If the tree becomes unbalanced,we fix it using rotation.
![alt fix it](Rotation.png)
**LL、RR、LR、RL** 描述的是新节点插入后，失衡节点到新节点的路径方向

第一个字母表示从失衡节点走向较高子树的方向，第二个字母表示继续向下的方向

- LL：左子树的左侧过高，做一次右旋
- RR：右子树的右侧过高，做一次左旋
- LR：左子树的右侧过高，先对左孩子左旋，再对失衡节点右旋
- RL：右子树的左侧过高，先对右孩子右旋，再对失衡节点左旋